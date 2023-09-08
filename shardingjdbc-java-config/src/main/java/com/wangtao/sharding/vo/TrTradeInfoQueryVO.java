package com.wangtao.sharding.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author wangtao
 * Created at 2023/9/2 23:57
 */
@Data
public class TrTradeInfoQueryVO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate beginTxnDt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endTxnDt;

    private String prodCd;

    private String assetCd;
}
