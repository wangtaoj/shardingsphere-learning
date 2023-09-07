package com.wangtao.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.vo.TrTradeInfoQueryVO;
import com.wangtao.sharding.vo.TrTradeInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TrTradeInfoMapper extends BaseMapper<TrTradeInfo> {

    IPage<TrTradeInfoVO> selectByCondition(IPage<TrTradeInfoVO> page, @Param("trTradeInfo") TrTradeInfoQueryVO trTradeInfo);
}




