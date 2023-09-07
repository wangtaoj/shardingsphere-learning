package com.wangtao.sharding.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.mapper.TrTradeInfoMapper;
import com.wangtao.sharding.util.DateUtils;
import com.wangtao.sharding.vo.TrTradeInfoQueryVO;
import com.wangtao.sharding.vo.TrTradeInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author wangtao
 * Created at 2023/9/3 00:14
 */
@Service
public class TrTradeInfoService {

    @Autowired
    private TrTradeInfoMapper trTradeInfoMapper;

    /**
     * 联表 + 排序 + 分页
     * 跨表范围查询时改写SQL为union all或者多个SQL，然后内存排序
     * 内存排序需要把每个表的数据全部查出来，才能保证全局有序
     * 比如pageNum=2, pageSize=3
     * 改写SQL分页为limit 0, 6，而不是原本的limit 3,3
     */
    public IPage<TrTradeInfoVO> selectByCondition(TrTradeInfoQueryVO trTradeInfoQuery, int pageNum, int pageSize) {
        IPage<TrTradeInfoVO> page = new Page<>(pageNum, pageSize);
        return trTradeInfoMapper.selectByCondition(page, trTradeInfoQuery);
    }

    public List<TrTradeInfo> selectByTxnDt(LocalDate txnDt) {
        Wrapper<TrTradeInfo> queryWrapper = new LambdaQueryWrapper<TrTradeInfo>()
                .eq(TrTradeInfo::getTxnDt, txnDt)
                .eq(TrTradeInfo::getDelFlg, 1);
        return trTradeInfoMapper.selectList(queryWrapper);
    }

    /**
     * 基因法，交易编号携带分片键
     */
    public TrTradeInfo selectByTxnId(Long txnId) {
        String txnDt = String.valueOf(txnId).substring(0, 8);
        Wrapper<TrTradeInfo> queryWrapper = new LambdaQueryWrapper<TrTradeInfo>()
                .eq(TrTradeInfo::getTxnId, txnId)
                .eq(TrTradeInfo::getTxnDt, DateUtils.parseIntegerDate(txnDt))
                .eq(TrTradeInfo::getDelFlg, 1);
        return trTradeInfoMapper.selectOne(queryWrapper);
    }
}
