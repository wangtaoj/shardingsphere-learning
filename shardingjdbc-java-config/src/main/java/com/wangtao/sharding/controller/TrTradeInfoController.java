package com.wangtao.sharding.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wangtao.sharding.entity.TrTradeInfo;
import com.wangtao.sharding.service.TrTradeInfoService;
import com.wangtao.sharding.vo.TrTradeInfoQueryVO;
import com.wangtao.sharding.vo.TrTradeInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
    public IPage<TrTradeInfoVO> queryByCondition(TrTradeInfoQueryVO trTradeInfoQuery, int pageNum, int pageSize) {
        return trTradeInfoService.selectByCondition(trTradeInfoQuery, pageNum, pageSize);
    }

    @PostMapping("/queryByTxnDt")
    public List<TrTradeInfo> queryByTxnDt(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                              @RequestParam LocalDate txnDt) {
        return trTradeInfoService.selectByTxnDt(txnDt);
    }

    @PostMapping("/detail/{txnId}")
    public TrTradeInfo queryByTxnId(@PathVariable Long txnId) {
        return trTradeInfoService.selectByTxnId(txnId);
    }
}
