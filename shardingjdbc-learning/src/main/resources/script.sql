create database if not exists tradedb default character set utf8mb4;

use tradedb;

create table user
(
    user_id bigint auto_increment primary key comment '用户编号',
    login_name varchar(50) comment '用户名称',
    passowrd varchar(70) comment '密码',
    nickname varchar(100) comment '昵称',
    age integer comment '年龄',
    birthday date comment '生日',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '用户表';

create table tr_trade_info_0
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

create table tr_trade_info_1
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

create table tr_trade_info_2
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

create table tr_trade_info_3
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';
