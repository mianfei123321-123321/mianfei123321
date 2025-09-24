-- 创建数据库
create
database market CHARACTER SET utf8mb4;
use
market;

CREATE TABLE `manager`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    varchar(50) NOT NULL COMMENT '用户名',
    `real_name`   varchar(50) NOT NULL COMMENT '姓名',
    `pwd`         varchar(50) NOT NULL COMMENT '密码',
    `sex`         char(1)     NOT NULL COMMENT 'M:男F:女',
    `avatar_path` varchar(300) default NULL COMMENT '头像存放路径,格如:resources/type/20230523/123.jpg',
    `role_id`   int(10) not null comment '角色id,1:超级管理员2:仓库管理员3:销售4:财务',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB  COMMENT '管理员表';
insert into manager(username, real_name, pwd, sex,role_id)
values ('admin', '管理员', '123456', 'M',1);

create table `supplier`
(
    `id`   bigint not null auto_increment comment '主键',
    `name` varchar(200) not null comment '供应商名',
    primary key (`id`),
    UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB  COMMENT '供应商表';

create table `shelf`
(
    `id`   bigint not null auto_increment comment '主键',
    `shelf_no` varchar(200) not null comment '货架编号',
    primary key (`id`),
    UNIQUE KEY `uk_shelf_no` (`shelf_no`) USING BTREE
) ENGINE=InnoDB  COMMENT '货架';

create table `goods`
(
    `id`             bigint not null auto_increment comment '主键',
    `shelf_id`             bigint not null  comment '货架id',
    `name`           varchar(150)   not null comment '商品名',
    `born_place_codes`     varchar(50) default null comment '产地省市区编码,通过,分隔',
    `sale_price`     numeric(10, 2) not null comment '销售价,单位:元',
    `stock`          int(10) not null default 0 comment '库存',
    `unit`           varchar(30)    not null comment '单位',
    `specifications` varchar(150)   not null comment '规格',
    primary key (`id`),
    UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB  COMMENT '商品表';


create table `purchase`
(
    `id`            bigint not null auto_increment comment '主键',
    `purchase_no`   varchar(50) not null comment '进货批次号',
    `operator_id`   bigint not null comment '经办人id',
    status          tinyint(1) not null default 0 comment '0:待审核 1:审核通过 2:审核拒绝,3:已退货',
    `purchase_date` datetime   not null default current_timestamp comment '进货日期',
    primary key (`id`),
    UNIQUE KEY `uk_purchase_no` (`purchase_no`) USING BTREE
) ENGINE=InnoDB  COMMENT '进货表';

create table `purchase_detail`
(
    `id`          bigint not null auto_increment comment '主键',
    `purchase_id` bigint not null comment 'purchase表主键',
    `goods_id`    bigint not null comment '商品表的主键',
    `buy_price`   numeric(10, 2) not null comment '采购价,单位:元',
    `num`         int(10) not null comment '采购数量',
    `supplier_id` bigint not null comment '供应商表的主键',
    primary key (`id`)
) ENGINE=InnoDB  COMMENT '进货明细表';


create table `return_goods`
(
    `id`          bigint not null auto_increment comment '主键',
    `purchase_id` bigint not null comment 'purchase表主键',
    `ret_no`      varchar(50) not null comment '退货批次号',
    `operator_id` bigint not null comment '经办人id',
    status        tinyint(1) not null default 0 comment '0:待审核 1:审核通过 2:审核拒绝',
    `return_date` datetime   not null default current_timestamp comment '进货日期',
    primary key (`id`),
    UNIQUE KEY `uk_ret_no` (`ret_no`) USING BTREE
) ENGINE=InnoDB  COMMENT '退货表';


create table `return_goods_detail`
(
    `id`           bigint not null auto_increment comment '主键',
    `ret_id`       bigint not null comment 'return_goods表主键',
    `goods_id`     bigint not null comment '商品表的主键',
    `return_price` numeric(10, 2) not null comment '退货价格,单位:元',
    `num`          int(10) not null comment '采购数量',
    `supplier_id`  bigint not null comment '供应商表的主键',
    primary key (`id`)
) ENGINE=InnoDB  COMMENT '进货明细表';


create table `sale_order`
(
    `id`           bigint not null auto_increment comment '主键',
    `order_no`     varchar(50)    not null comment '订单号',
    `total_amount` numeric(10, 2) not null comment '商品总价,单位:元',
    status         tinyint(1) not null default 0 comment '0:已完成 1:已退款',
    `created_time`  datetime      not null default current_timestamp comment '创建日期',
    primary key (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB  COMMENT '销售订单表';

create table `sale_order_detail`
(
    `id`         bigint not null auto_increment comment '主键',
    `order_id`   bigint not null comment '订单表主键',
    `goods_id`   bigint not null comment '商品表的主键',
    `sale_price` numeric(10, 2) not null comment '销售价格,单位:元',
    `num`        int(10) not null comment '销售数量',
    primary key (`id`)
) ENGINE=InnoDB  COMMENT '销售订单明细表';


create table `refund`
(
    `id`           bigint not null auto_increment comment '主键',
    `order_id`   bigint not null comment '订单表主键',
    `refund_no`    varchar(50)    not null comment '退款编号',
    `total_amount` numeric(10, 2) not null comment '退款总金额,单位:元',
    `created_time`  datetime      not null default current_timestamp comment '创建日期',
    primary key (`id`),
    UNIQUE KEY `uk_refund_no` (`refund_no`) USING BTREE
) ENGINE=InnoDB  COMMENT '退款表';


create table `refund_detail`
(
    `id`           bigint not null auto_increment comment '主键',
    `refund_id`    bigint not null comment '退款表主键',
    `goods_id`     bigint not null comment '商品表的主键',
    `refund_price` numeric(10, 2) not null comment '退款价格,单位:元',
    `num`          int(10) not null comment '销售数量',
    primary key (`id`)
) ENGINE=InnoDB  COMMENT '退款明细表';
