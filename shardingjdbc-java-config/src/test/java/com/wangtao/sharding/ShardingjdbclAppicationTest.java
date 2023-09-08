package com.wangtao.sharding;

import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.service.TrTradeInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangtao
 * Created at 2023/9/3 15:18
 */
@SpringBootTest
public class ShardingjdbclAppicationTest {

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

}
