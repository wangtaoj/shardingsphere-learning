package com.wangtao.sharding.controller;

import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.service.TrTradeInfoService;
import com.wangtao.sharding.vo.TrTradeInfoQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author wangtao
 * Created at 2023/9/2 23:56
 */
@RequestMapping("/trTradeInfo")
@RestController
public class TrTradeInfoController {

    @Autowired
    private TrTradeInfoService trTradeInfoService;

    @PostMapping("/queryByCondition")
    public List<TrTradeInfo> queryByCondition(TrTradeInfoQueryVO trTradeInfoQuery) {
        return trTradeInfoService.selectByCondition(trTradeInfoQuery);
    }

    @PostMapping("/queryByTxnDt")
    public List<TrTradeInfo> queryByTxnDt(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                              @RequestParam LocalDate txnDt) {
        return trTradeInfoService.selectByTxnDt(txnDt);
    }
}
