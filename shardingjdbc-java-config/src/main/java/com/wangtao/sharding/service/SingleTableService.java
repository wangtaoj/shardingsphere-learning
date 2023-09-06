package com.wangtao.sharding.service;

import com.wangtao.sharding.entity.SingleTable;
import com.wangtao.sharding.mapper.SingleTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangtao
 * Created at 2023/9/6 20:17
 */
@Service
public class SingleTableService {

    @Autowired
    private SingleTableMapper singleTableMapper;

    public SingleTable detailById(Long id) {
        return singleTableMapper.selectById(id);
    }
}
