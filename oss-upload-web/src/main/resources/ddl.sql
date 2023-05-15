CREATE TABLE `tb_file_resource` (
                                    `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                    `f_name` varchar(255) NOT NULL COMMENT '文件名',
                                    `f_url` varchar(255) NOT NULL COMMENT '文件路径',
                                    `f_type` varchar(50) NOT NULL COMMENT '文件类型',
                                    `f_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `f_create_time` datetime NOT NULL COMMENT '创建时间',
                                    PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='文件资源路径表';

-- LTAI5t7Mb933vtv6r38uG86P
-- P5e8RozYwvew8mmRq12bQYb0AfqMBc