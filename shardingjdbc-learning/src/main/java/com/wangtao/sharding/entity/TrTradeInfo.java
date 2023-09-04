package com.wangtao.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 交易表
 */
@TableName(value ="tr_trade_info")
@Data
public class TrTradeInfo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    @TableId
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