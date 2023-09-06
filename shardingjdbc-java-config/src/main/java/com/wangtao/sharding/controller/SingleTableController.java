package com.wangtao.sharding.controller;

import com.wangtao.sharding.entity.SingleTable;
import com.wangtao.sharding.service.SingleTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangtao
 * Created at 2023/9/6 20:13
 */
@RequestMapping("/singleTable")
@RestController
public class SingleTableController {

    @Autowired
    private SingleTableService singleTableService;

    @GetMapping("/detail/{id}")
    public SingleTable detailById(@PathVariable Long id) {
        return singleTableService.detailById(id);
    }
}
