create database if not exists tradedb_1 default character set utf8mb4;

use tradedb_1;

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

INSERT INTO user (user_id, login_name, passowrd, nickname, age, birthday, del_flg, create_time, update_time) VALUES (1, 'zhangsan', '123456', '张三', 25, '2023-09-03', 1, '2023-09-03 00:37:14', '2023-09-03 00:37:18');
INSERT INTO user (user_id, login_name, passowrd, nickname, age, birthday, del_flg, create_time, update_time) VALUES (2, 'lisi', '123456', '李四', 25, '2023-09-03', 1, '2023-09-03 00:37:14', '2023-09-03 00:37:18');
INSERT INTO user (user_id, login_name, passowrd, nickname, age, birthday, del_flg, create_time, update_time) VALUES (3, 'wangwu', '123456', '王五', 25, '2023-09-03', 1, '2023-09-03 00:37:14', '2023-09-03 00:37:18');

create table single_table
(
    id bigint auto_increment primary key comment '用户编号'
) default character set utf8mb4 comment '单表';

INSERT INTO single_table (id) VALUES (1);

create table tr_trade_info_0
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    txn_user_id bigint comment '交易员',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

INSERT INTO tr_trade_info_0 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2023010500001, '12345678', '000001', '2023-01-05', 100, 10000.00, 1, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_0 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2023030500001, '12345678', '000001', '2023-03-05', 100, 10000.00, 1, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_0 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2023053100001, '12345678', '000001', '2023-05-31', 300, 10000.00, 1, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');

create table tr_trade_info_1
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    txn_user_id bigint comment '交易员',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

INSERT INTO tr_trade_info_1 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2023070100001, '12345678', '000001', '2023-07-01', 500, 10000.00, 2, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_1 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2023090100001, '12345678', '000001', '2023-09-01', 100, 10000.00, 2, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_1 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2023123100001, '12345678', '000001', '2023-12-31', 200, 10000.00, 2, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');

create table tr_trade_info_2
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    txn_user_id bigint comment '交易员',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

INSERT INTO tr_trade_info_2 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2024010500001, '12345678', '000001', '2024-01-05', 400, 10000.00, 3, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_2 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2024030500001, '12345678', '000001', '2024-03-05', 100, 10000.00, 3, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_2 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2024053100001, '12345678', '000001', '2024-05-31', 100, 10000.00, 3, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');

create table tr_trade_info_3
(
    txn_id bigint primary key comment '交易编号',
    prod_cd varchar(50) comment '产品代码',
    asset_cd varchar(20) comment '资产代码',
    txn_dt date not null comment '交易日期',
    txn_cnt decimal(20,0) comment '交易数量',
    txn_amt decimal(20,2) comment '交易金额',
    txn_user_id bigint comment '交易员',
    del_flg tinyint not null comment '删除标志',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间'
) default character set utf8mb4 comment '交易表';

INSERT INTO tr_trade_info_3 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2024070100001, '12345678', '000001', '2024-07-01', 100, 10000.00, 2, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_3 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2024090100001, '12345678', '000001', '2024-09-01', 100, 10000.00, 2, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
INSERT INTO tr_trade_info_3 (txn_id, prod_cd, asset_cd, txn_dt, txn_cnt, txn_amt, txn_user_id, del_flg, create_time, update_time) VALUES (2024123100001, '12345678', '000001', '2024-12-31', 100, 10000.00, 2, 1, '2023-09-01 12:00:00', '2023-09-01 12:00:00');
