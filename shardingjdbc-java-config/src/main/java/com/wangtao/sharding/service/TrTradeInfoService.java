package com.wangtao.sharding.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.mapper.TrTradeInfoMapper;
import com.wangtao.sharding.util.DateUtils;
import com.wangtao.sharding.vo.TrTradeInfoQueryVO;
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

    public List<TrTradeInfo> selectByCondition(TrTradeInfoQueryVO trTradeInfoQuery) {
        return trTradeInfoMapper.selectByCondition(trTradeInfoQuery);
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
