package com.wangtao.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangtao
 * Created at 2023/9/6 20:13
 */
@TableName("single_table")
@Data
public class SingleTable {

    private Long id;
}
