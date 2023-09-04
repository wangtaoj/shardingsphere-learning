package com.wangtao.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.vo.TrTradeInfoQueryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrTradeInfoMapper extends BaseMapper<TrTradeInfo> {

    List<TrTradeInfo> selectByCondition(TrTradeInfoQueryVO trTradeInfoQuery);
}




