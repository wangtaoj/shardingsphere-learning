package com.wangtao.sharding;

import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.service.TrTradeInfoService;
import com.wangtao.sharding.util.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author wangtao
 * Created at 2023/9/3 15:18
 */
@SpringBootTest
public class ShardingjdbclAppicationTest {

    private static final String[] PROD_CD_ARR = new String[] {
            "12345678",
            "23456778",
            "34567890",
            "45678901",
            "56789012",
            "67890123",
            "78901234",
            "89012345",
            "90123456",
            "01234567"
    };

    private static final String[] ASSET_CD_LIST = new String[] {
            "000001",
            "000002",
            "000003",
            "000004",
            "000005",
            "000006",
            "000007",
            "000008",
            "000009",
            "000010",
            "000011",
    };

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TrTradeInfoService trTradeInfoService;

    @Test
    public void contextLoad() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testInsert() {
        TrTradeInfo trTradeInfo = new TrTradeInfo();
        trTradeInfo.setTxnId(2023090800001L);
        trTradeInfo.setProdCd("12345678");
        trTradeInfo.setAssetCd("000001");
        trTradeInfo.setTxnDt(LocalDate.of(2023, 9, 8));
        trTradeInfo.setTxnCnt(1000L);
        trTradeInfo.setTxnAmt(new BigDecimal("10000"));
        trTradeInfo.setTxnUserId(1L);
        trTradeInfo.setDelFlg(1);
        trTradeInfo.setCreateTime(LocalDateTime.now());
        trTradeInfo.setUpdateTime(LocalDateTime.now());
        trTradeInfoService.insert(trTradeInfo);
    }

    @Test
    public void testBatchInsert() {
        TrTradeInfo trTradeInfo1 = new TrTradeInfo();
        trTradeInfo1.setTxnId(2023030600001L);
        trTradeInfo1.setProdCd("12345678");
        trTradeInfo1.setAssetCd("000001");
        trTradeInfo1.setTxnDt(LocalDate.of(2023, 3, 6));
        trTradeInfo1.setTxnCnt(1000L);
        trTradeInfo1.setTxnAmt(new BigDecimal("10000"));
        trTradeInfo1.setTxnUserId(1L);
        trTradeInfo1.setDelFlg(1);
        trTradeInfo1.setCreateTime(LocalDateTime.now());
        trTradeInfo1.setUpdateTime(LocalDateTime.now());

        TrTradeInfo trTradeInfo2 = new TrTradeInfo();
        trTradeInfo2.setTxnId(2023090900001L);
        trTradeInfo2.setProdCd("12345678");
        trTradeInfo2.setAssetCd("000001");
        trTradeInfo2.setTxnDt(LocalDate.of(2023, 9, 9));
        trTradeInfo2.setTxnCnt(1000L);
        trTradeInfo2.setTxnAmt(new BigDecimal("10000"));
        trTradeInfo2.setTxnUserId(1L);
        trTradeInfo2.setDelFlg(1);
        trTradeInfo2.setCreateTime(LocalDateTime.now());
        trTradeInfo2.setUpdateTime(LocalDateTime.now());
        trTradeInfoService.batchInsert(List.of(trTradeInfo1, trTradeInfo2));
    }

    /**
     * 半年数据, 一天5000条
     * 插入耗时270s
     */
    public void createData() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("initData");
        List<TrTradeInfo> datas = initData();
        System.out.println(datas.size());
        stopWatch.stop();
        stopWatch.start("insertData");
        trTradeInfoService.batchInsert(datas);
        stopWatch.stop();
        System.out.println(stopWatch.getTaskInfo()[0].getTimeSeconds());
        System.out.println(stopWatch.getTaskInfo()[1].getTimeSeconds());
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    private List<TrTradeInfo> initData() {
        List<TrTradeInfo> trTradeInfos = new ArrayList<>();
        Random random = new Random();
        LocalDate curDate = LocalDate.of(2023, 7, 1);
        while (!curDate.isAfter(LocalDate.of(2023, 12, 31))) {
            for (int i = 1; i <= 50000; i++) {
                String prefix = DateUtils.formatInteger(curDate);
                Long txnId = Long.valueOf(prefix + String.format("%06d", i));
                TrTradeInfo trTradeInfo = new TrTradeInfo();
                trTradeInfo.setTxnId(txnId);
                trTradeInfo.setProdCd(PROD_CD_ARR[i % PROD_CD_ARR.length]);
                trTradeInfo.setAssetCd(ASSET_CD_LIST[i % ASSET_CD_LIST.length]);
                trTradeInfo.setTxnDt(curDate);
                trTradeInfo.setTxnCnt((long) (100 + random.nextInt(100000)));
                trTradeInfo.setTxnAmt(BigDecimal.valueOf(trTradeInfo.getTxnCnt()).multiply(BigDecimal.valueOf(100)));
                trTradeInfo.setTxnUserId(1L);
                trTradeInfo.setDelFlg(1);
                trTradeInfo.setCreateTime(LocalDateTime.now());
                trTradeInfo.setUpdateTime(LocalDateTime.now());
                trTradeInfos.add(trTradeInfo);
            }
            curDate = curDate.plusDays(1);
        }
        return trTradeInfos;
    }

}
