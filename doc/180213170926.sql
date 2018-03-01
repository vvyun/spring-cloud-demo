/*
MySQL Backup
Source Server Version: 5.5.5
Source Database: springboot
Date: 18-3-1 20:20:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `table_per`
-- ----------------------------
DROP TABLE IF EXISTS `table_per`;
CREATE TABLE `table_per` (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=sjis;

-- ----------------------------
--  Table structure for `table_role`
-- ----------------------------
DROP TABLE IF EXISTS `table_role`;
CREATE TABLE `table_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `describtion` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `table_user`
-- ----------------------------
DROP TABLE IF EXISTS `table_user`;
CREATE TABLE `table_user` (
  `name` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_role_per`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_per`;
CREATE TABLE `t_role_per` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`rid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `table_per` VALUES ('1','个人信息','0','#','1',NULL), ('2','用户管理','0','#','2',NULL), ('3','权限管理','0','#','3',NULL), ('11','修改头像','0','reimg','1','1'), ('12','修改密码','0','repass','2','1'), ('21','添加用户','0','addUser','1','2'), ('22','删除用户','0','delUser','2','2'), ('23','修改用户','0','updateUser','3','2'), ('24','所用用户','0','getAll','4','2'), ('31','权限管理','0','#','1','3'), ('32','角色管理','0','role','2','3');
INSERT INTO `table_role` VALUES ('1','管理员','系统管理员'), ('2','用户','普通用户');
INSERT INTO `table_user` VALUES ('张三','1001','123456','1'), ('东方','1002','123','1'), ('渣渣辉','1003','123','1'), ('微笑','1004','123456','1'), ('新用户','1005','123','1');
INSERT INTO `t_role_per` VALUES ('1','1'), ('1','2'), ('1','3'), ('1','11'), ('1','12'), ('1','21'), ('1','22'), ('1','23'), ('1','24'), ('1','31'), ('1','32'), ('2','1'), ('2','11'), ('2','12');
INSERT INTO `t_user_role` VALUES ('1001','1'), ('1002','2'), ('1003','2'), ('1004','2'), ('1005','2');
