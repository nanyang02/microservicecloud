/***********************************************************************
  这个主要的问题是资源权限的控制的原理
	- 首先，我们的系统的操作和页面，都是需要自行定义资源的，每个按钮的控制和页面的控制必须要
	做资源权限的控制，如果没有权限的，拦截，并提示403无权限的界面。我们提供角色资源的资源
	分离的权限控制模式。

	- 热加载缓存，就是说，资源的问题需要进行先预热。加载到缓存中。
*************************************************************************/

create database mywf default character set utf8 collate utf8_general_ci;

use myfw;


/*
创建一个流程
*/
drop table wf_str_type;
create table wf_str_type
(
  `wf_id`   int                      not null AUTO_INCREMENT comment '流程类型id',
  `wf_key`  varchar(30)              default '' comment '类型关键字',
  `wf_name` varchar(30)              not null comment '类型名称',
  `sort`    int(11)      default '0' not null comment '排序',
  `desc`    varchar(100) default null comment '备注',
  `valid`   tinyint(4)   default '1' not null comment '是否有效：1 - 有效；0 - 无效',
  primary key (`wf_id`),
  key `ind_wfstr_type_search` (`sort`, `valid`)
) engine = innodb comment ='流程类型';

/*
流程方法
*/
drop table wf_str_method;
create table `wf_str_method`
(
  `m_id`     int                     not null AUTO_INCREMENT comment '方法主键',
  `m_name`   varchar(30)             not null comment '方法名称',
  `wf_id`    int                     not null comment '流程的id',
  `m_class`  varchar(100)            not null comment '类名',
  `m_method` varchar(30)             not null comment '方法名',
  `m_bean`   varchar(50) default null comment '实例名',
  `sort`     int(11)     default '0' not null comment '排序',
  `valid`    tinyint(4)  default '1' not null comment '是否有效：1 - 有效；0 - 无效',
  primary key (`m_id`),
  key `ind_wfstr_m_search` (`sort`, `valid`)
) engine = innodb comment ='流程方法';

/*
流程变量
*/
drop table wf_str_var;
create table `wf_str_var`
(
  `wf_id`    int                      not null comment '类型关键字',
  `var_key`  varchar(30)              not null comment '变量关键字',
  `var_name` varchar(30)              not null comment '变量名称',
  `desc`     varchar(100) default null comment '备注',
  `sort`     int(11)      default '0' not null comment '排序',
  `valid`    tinyint(4)   default '1' not null comment '是否有效：1 - 有效；0 - 无效',
  primary key (`wf_id`, `var_key`),
  key `ind_wfstr_var_search` (`sort`, `valid`)
) engine = innodb comment ='流程变量';

## 流程操作
drop table wf_str_operat;
create table `wf_str_operat`
(
  `wf_id`    int                      not null comment '类型关键字',
  `opt_key`  varchar(30)              not null comment '操作关键字',
  `opt_name` varchar(30)              not null comment '操作名称',
  `sort`     int          default '0' not null comment '排序',
  `desc`     varchar(100) default null comment '备注',
  `valid`    tinyint(4)   default '1' not null comment '是否有效：1 - 有效；0 - 无效',
  primary key (`wf_id`, `opt_key`),
  key `ind_wfstr_opt_search` (`sort`, `valid`)
) engine = innodb comment ='流程操作';

## 流程状态(独立部分)
drop table wf_tmp_status;
create table `wf_tmp_status`
(
  `s_id`   int AUTO_INCREMENT comment '状态关键字',
  `s_name` varchar(30)  default null comment '状态名称',
  `s_key`  varchar(30)  default null comment '状态关键字',
  `wf_id`  int                    not null comment '流程id',
  `sort`   int(11)      default 0 not null comment '排序',
  `desc`   varchar(100) default null comment '备注',
  primary key (`s_id`),
  key `ind_wftmp_status_key` (`s_key`),
  key `ind_wftmp_status_search` (`wf_id`, `sort`)
) engine = innodb comment ='流程状态';

/***********************************************************************
   流程的界面这块，会涉及的东西有点多，最好是有一个页面资源的控制的功能。

   流程的构建的核心：
   node，path，resource

   node点，就是关联到一个资源（那些资源页面运行访问的问题）

*************************************************************************/
## 流程界面
drop table wf_str_view;
create table `wf_str_view`
(
  `wf_id`     int                      not null comment '流程id',
  `res_key`   varchar(30)              not null comment '关联资源',
  `view_name` varchar(30)              not null comment '界面名称',
  `sort`      int(11)      default '0' not null comment '排序',
  `desc`      varchar(100) default null comment '备注',
  `valid`     tinyint(4)   default '1' not null comment '是否有效：1 - 有效；0 - 无效',
  primary key (`wf_id`, `res_key`),
  key `ind_wfstr_viw_search` (`sort`, `valid`)
) engine = innodb comment ='流程界面';

## 流程界面变量(为什么需要记录资源和变量的关系？暂时没有看出关联关系)
drop table wf_str_view_variable;
create table `wf_str_view_variable`
(
  `wf_id`   int         not null comment '流程id',
  `res_key` varchar(30) not null comment '资源key',
  `var_key` varchar(30) not null comment '流程变量key',
  primary key (`wf_id`, `res_key`, `var_key`)
) engine = innodb comment ='流程界面变量';

## 流程界面操作
drop table wf_str_view_operat;
create table `wf_str_view_operat`
(
  `opt_key` varchar(30) not null comment '流程操作',
  `wf_id`   int         not null comment '类型关键字',
  `res_key` varchar(30) default null comment '资源编码',
  unique key `unq_wfstr_vo` (`wf_id`, `res_key`, `opt_key`)
) engine = innodb comment ='流程界面操作';

## 操作流向（与实例无关）
drop table wf_str_operat_view;
create table `wf_str_operat_view`
(
  `wf_id`      int         not null comment '类型关键字',
  `res_key`    varchar(30) default null comment '资源编码',
  `operat_key` varchar(30) not null comment '流程操作',
  unique key `unq_wfstr_ov` (`type_key`, `res_key`, `operat_key`)
) engine = innodb comment ='操作流向';


## 流程界面状态
drop table wf_tmp_view_status;
create table `wf_tmp_view_status`
(
  `wf_id`   int                    not null comment '流程',
  `res_key` varchar(30)            not null comment '资源编码',
  `s_id`    varchar(50)            not null comment '状态键字',
  `valid`   tinyint(4) default '1' not null comment '是否有效：1 - 有效；0 - 无效',
  primary key (`wf_id`, `res_key`, `s_id`)
) engine = innodb comment ='流程界面状态';

## 流程模版
drop table wf_tmp_template;
create table `wf_tmp_template`
(
  `tem_id`   int                      not null comment '模版关键字',
  `tem_name` varchar(30)  default null comment '模版名称',
  `wf_id`    int                      not null comment '类型关键字',
  `priority` tinyint(4)               not null comment '重要级别',
  `state`    tinyint(4)   default '1' not null comment '模版状态',
  `sort`     int(11)      default '0' not null comment '排序',
  `desc`     varchar(100) default null comment '备注',
  primary key (`tem_id`),
  key `ind_wftmp_tmp_search` (`wf_id`, `priority`, `state`, `sort`)
) engine = innodb comment ='流程模版';

## 流程节点
drop table wf_tmp_node;
create table `wf_tmp_node`
(
  `node_id`   int                      not null comment '节点关键字',
  `node_name` varchar(30)  default null comment '节点名称',
  `wf_id`     int                      not null comment '类型关键字',
  `tem_id`    int                      not null comment '流程模版',
  `res`       varchar(50)  default null comment '节点界面（这个是权限问题的）',
  `priority`  tinyint(4)               not null comment '优先级',
  `mainstay`  tinyint(4)   default '0' not null comment '是否主要节点：1 - 有效；0 - 无效',
  `sort`      int(11)      default '0' not null comment '排序',
  `desc`      varchar(100) default null comment '备注',
  primary key (`node_id`),
  key `ind_wftmp_node_search` (`wf_id`, `tem_id`, `priority`)
) engine = innodb comment ='流程节点';

## 动作
drop table wf_tmp_activity;
create table `wf_tmp_activity`
(
  `act_id`   int                      not null comment '动作关键字',
  `act_name` varchar(30)  default null comment '动作名称',
  `wf_id`    int                      not null comment '类型关键字',
  `tem_id`   int                      not null comment '流程模版',
  `s_node`   int          default null comment '源节点',
  `opt_id`   int                      not null comment '操作id',
  `opt_key`  varchar(30)              not null comment '操作关键字',
  `t_node`   int          default null comment '后一节点',
  `t_status` int                      not null comment '操作完成状态',
  `silence`  tinyint(4)   default '0' not null comment '是否静默：1 - 是；0 - 否',
  `desc`     varchar(100) default null comment '备注',
  primary key (`act_id`),
  unique key `unq_wftmp_act_operat` (`tem_id`, `s_node`, `opt_key`),
  key `ind_wftmp_act_search` (`wf_id`, `tem_id`, `s_node`, `opt_key`, `t_node`, `silence`)
) engine = innodb comment ='动作';

## 话说回来，这个的目的就是对操作的权限控制，为我们要竟可能的考虑这个问题
## 动作权限（这个预设的方式不好，因为这个就像需要进行额外的存储，比如一个用户是否有权限操作一个节点的数据）
-- drop table wf_tmp_act_permis;
-- create table `wf_tmp_act_permis` (
-- 	`activity_id` varchar(50) not null comment '动作关键字',
-- 	`refence_type` tinyint(4) not null comment '关联类型',
-- 	`refence_id` varchar(50) default null comment '关联关键字',
-- 	`permis_name` varchar(30) default null comment '权限名称',
-- 	`rule_type` tinyint(4) not null comment '规则类型',
-- 	`sort` int(11) default '0' not null comment '排序',
-- 	unique key `unq_wftmp_ap` (`activity_id`, `refence_type`, `refence_id`),
-- 	key `ind_wftmp_ap_search` (`rule_type`, `sort`)
-- ) engine=innodb comment='动作权限';

/*
流程
- 其实，这个创建者的信息都是可以自行约定，这里我们都采用string格式存储，也可以存ts或者用户名等，反正可以用于确定用户唯一性的key即可
*/
drop table wf_ins_process;
create table `wf_ins_process`
(
  `pro_id` bigint      not null comment '流程关键字',
  `wf_id`  int         not null comment '流程关键字',
  `tem_id` int         not null comment '模板关键字',
  `ts`     bigint      not null comment '创建时间',
  `u`      varchar(50) default null comment '创建用户',
  `o`      varchar(50) default null comment '创建机构',
  `p`      varchar(50) default null comment '创建人员',
  `cs`     varchar(50) not null comment '当前状态',
  primary key (`pro_id`),
  key `ind_wfins_pro_search` (`tem_id`, `cs`)
) engine = innodb comment ='流程';

## 流程参数（个数不定，所以单独拆分为表）
drop table wf_ins_parameter;
create table `wf_ins_parameter`
(
  `pro_id`  bigint      not null comment '流程实例的id',
  `var_key` varchar(50) not null comment '参数key',
  `var_val` varchar(100) default null comment '参数值',
    primary key (`pro_id`, `var_key`),
  key `ind_wfins_pam_search` (`var_val`)
) engine = innodb comment ='流程参数';

## 流程路径
drop table wf_ins_path;
create table `wf_ins_path`
(
  `path_id`   varchar(50)            not null comment '路径关键字',
  `pro_id`    bigint                 not null comment '流程关键字',
  `node_id`   int                    not null comment '节点关键字',
  `refreshed` datetime               not null comment '刷新时间',
  `priority`  int(11)    default '0' not null comment '优先级',
  `state`     tinyint(4) default '0' not null comment '路径状态',
  `ts`        datetime               not null comment '创建时间',
  primary key (`path_id`),
  key `ind_wfins_path_search` (`pro_id`, `node_id`, `refreshed`, `priority`, `state`)
) engine = innodb comment ='流程路径';

## 任务(在执行的任务极其多的情况下，再加上人员的数据量，则数据的量会极大，那么为了平均数据，推荐分表处理，不同的流程专门创建表)
-- drop table wf_ins_task;
-- create table `wf_ins_task` (
-- 	`path_id` varchar(50) not null comment '路径关键字',
-- 	`act_id` varchar(50) not null comment '动作关键字',
-- 	`usr_id` varchar(50) not null comment '用户关键字',
-- 	primary key (`path_id`, `activity_id`, `user_id`)
-- ) engine=innodb comment='任务';

-- 一个人员是否能够操作一个流程，就看这个筛选的条件检查一下就可以，而检查的依据是一些参数。
-- 怎么知道这个流程是否允许被操作？获取人员的基本属性：为了简化问题，我们考虑的方向是数值的问题

## 操作记录
drop table wf_ins_history;
create table `wf_ins_history`
(
  `his_id` bigint not null comment '记录关键字',
  `pro_id` varchar(50) not null comment '流程关键字',
  `act_id` int not null comment '动作主键',
  `ts_rec` datetime    not null comment '记录时间',
  `usr_id` varchar(50) default null comment '用户关键字',
  `psn_id` varchar(50) default null comment '人员关键字',
  `org_id` varchar(50) default null comment '机构关键字',
  primary key (`his_id`),
  key `ind_wfins_his_search` (`pro_id`, `ts_rec`)
) engine = innodb comment ='操作记录';

/*
系统资源（admin 创建）
- 通过资源来控制权限
*/
drop table x_sys_res;
create table x_sys_res (
  `res_id` int auto_increment primary key comment '资源id',
  `res_key` varchar(40) NOT null comment '资源的key',
  `res_name` varchar(100) default '' comment '资源的名称',
  `res_action` varchar(100) not null default '' comment '资源的路由',
  `parent` varchar(50) default 'top' comment '资源上级',
  `en` tinyint(1) not null default 1 comment '资源是否启用',
  key `ind_x_sys_res_search` (`res_id`, `res_key`, `parent`,`en`)
) engine = innodb comment ='资源';

/* 用户表,有做用户的类型的控制 */
drop table x_sys_user;
create table x_sys_user (
  `user_id` int auto_increment primary key comment '用户的id',
  `username` varchar(30) not null comment '用户名',
  `user_phone` varchar(20) not null default '' comment '用户的手机号',
  `pwd` varchar(100) NOT NULL COMMENT '密码',
  `dispatch` tinyint(4) default 0 comment '分配的类型: 0-未分配;1-人员;2-机构;3-复用(当一个账号兼多个类型的时候,特殊)',
  `valid` tinyint(1) not null default 1 comment '账号是否失效',
  key `ind_x_sys_user_search` (`user_id`, `username`, `user_phone`,`dispatch`, `valid`)
) engine = innodb comment ='资源';

/*
记录用户关联类型
- 目标: 根据用户的信息就可以直接知道是属于那些类型的
- 根据类型就可以知道是什么类型的
 */
drop table x_sys_user_dispatch;
create table x_sys_user_dispatch (
   `user_id` int not null comment '用户的id',
   `ref_id` int not null comment '分配的类型id',
   `ref_type` tinyint(4) not null comment '分配的类型',
   key `ind_x_sys_user_dispatch_search` (`user_id`, `ref_id`,  `ref_type`)
) engine = innodb comment ='资源';

/* 既然做了关系映射的类型,则表示这个唯一性,这个在 x_sys_user_dispatch 表中即可获知关联关系*/
drop table x_base_person;
create table x_base_person (
  `pp_id` int auto_increment primary key comment '人员的id',
  `pp_name` varchar(20) default '' comment '人员的姓名',
  `pp_addr` varchar(100) default '' comment '人员的地址',
  `pp_ts_regist` bigint default null comment '注册时间(ts)'
) engine = innodb comment ='人员信息';

-- 设计表结构的时候的心得,当我们需要做字段的统计的时候,最好的是有索引,那样就只是查询一下索引,会很快的.
-- 所以,考虑到要做统计的字段,给其分配索引是有必要的
-- 排序最好的方式可以是通过页面上次查询结果的条数来自动填入排序号字段中即可,就可以减少页面的索引号的问题
-- 同理,也可以下沉到服务端来做,最好的方法就是,让在redis中记录每个统计的一条数据的id作为key,统计的数量作为一个值来记录(保留10分钟即可(当用户量大的时候才会体现出压力
-- ,这可以切面来实现,对所有的符合条件的接口,添加索引))
-- 封装一下,自动的记录数据在服务端即可
-- 还是做一下count来实现获取最大值比较好,数据量不大的情况下,是比较好的
-- 为什么我们的数据库不设置null的情况就是为了做优化


explain select * from x_sys_user u
LEFT join x_sys_user_dispatch d on d.user_id = u.user_id
LEFT join x_base_person p on p.pp_id = d.ref_id
where u.username = 'ppl1' and d.ref_type = 1