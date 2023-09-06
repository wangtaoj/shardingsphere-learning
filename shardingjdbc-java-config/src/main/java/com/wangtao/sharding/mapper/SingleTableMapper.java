package com.wangtao.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangtao.sharding.entity.SingleTable;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangtao
 * Created at 2023/9/6 20:14
 */
@Mapper
public interface SingleTableMapper extends BaseMapper<SingleTable> {

}
