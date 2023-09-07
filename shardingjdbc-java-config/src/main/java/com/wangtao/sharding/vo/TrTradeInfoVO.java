package com.wangtao.sharding.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author wangtao
 * Created at 2023/9/7 21:07
 */
@Data
public class TrTradeInfoVO {

    /**
     * 交易编号
     */
    private Long txnId;

    /**
     * 产品代码
     */
    private String prodCd;

    /**
     * 资产代码
     */
    private String assetCd;

    /**
     * 交易日期
     */
    private LocalDate txnDt;

    /**
     * 交易数量
     */
    private Long txnCnt;

    /**
     * 交易金额
     */
    private BigDecimal txnAmt;

    /**
     * 交易员
     */
    private Long txnUserId;

    /**
     * 交易员名称
     */
    private String txnUserName;

    /**
     * 删除标志
     */
    private Integer delFlg;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
