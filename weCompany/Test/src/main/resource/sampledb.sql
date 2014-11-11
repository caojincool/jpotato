/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : sampledb

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-11-11 19:26:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorities
-- ----------------------------

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `_id` char(36) NOT NULL,
  `_orderId` int(11) NOT NULL,
  `_name` varchar(32) DEFAULT NULL,
  `_nameEn` varchar(32) DEFAULT NULL,
  `_content` text,
  `_contentEn` text,
  `_keyWord` varchar(256) DEFAULT NULL,
  `_keyWordEn` varchar(256) DEFAULT NULL,
  `_description` varchar(512) DEFAULT NULL,
  `_descriptionEn` varchar(512) DEFAULT NULL,
  `_isEnable` bit(1) DEFAULT NULL,
  `_categoryType` varchar(12) DEFAULT NULL,
  `_forum_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `fk_category_forum_id` (`_forum_id`),
  CONSTRAINT `fk_category_forum_id` FOREIGN KEY (`_forum_id`) REFERENCES `t_forum` (`_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('2120f342-6978-11e4-9e75-003067974107', '0', '类别1', 'leibie1', '类别1内容', null, '类别1关键字', null, '类别1描述', null, '\0', 'PRODUCTLIST', '972cf6e1-696f-11e4-9e75-003067974107');
INSERT INTO `t_category` VALUES ('2c37ed25-6978-11e4-9e75-003067974107', '0', '类别1', 'leibie1', '类别1内容', null, '类别1关键字', null, '类别1描述', null, '\0', 'PRODUCTLIST', 'a46f7e21-696f-11e4-9e75-003067974107');
INSERT INTO `t_category` VALUES ('6016ceda-6982-11e4-9e75-003067974107', '0', '类别4', 'leibie4', '类别4内容', null, '类别4关键字', null, '类别4描述', null, '\0', 'ARTICLE', 'a46f7e21-696f-11e4-9e75-003067974107');
INSERT INTO `t_category` VALUES ('a57e262a-6977-11e4-9e75-003067974107', '0', '类别1', 'leibie1', '类别1内容', null, '类别1关键字', null, '类别1描述', null, '\0', 'PRODUCTLIST', '13c2c5b9-696f-11e4-9e75-003067974107');
INSERT INTO `t_category` VALUES ('bf267b51-697f-11e4-9e75-003067974107', '0', '类别3', 'leibie1', '类别3内容', null, '类别3关键字', null, '类别3描述', null, '\0', 'PRODUCTLIST', 'a46f7e21-696f-11e4-9e75-003067974107');
INSERT INTO `t_category` VALUES ('e6bbca31-6977-11e4-9e75-003067974107', '0', '类别1', 'leibie1', '类别1内容', null, '类别1关键字', null, '类别1描述', null, '\0', 'PRODUCTLIST', '8480ddbc-696f-11e4-9e75-003067974107');

-- ----------------------------
-- Table structure for t_forum
-- ----------------------------
DROP TABLE IF EXISTS `t_forum`;
CREATE TABLE `t_forum` (
  `_id` char(36) NOT NULL,
  `_name` varchar(128) DEFAULT NULL,
  `_nameEn` varchar(128) DEFAULT NULL,
  `_isEnabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_forum
-- ----------------------------
INSERT INTO `t_forum` VALUES ('13c2c5b9-696f-11e4-9e75-003067974107', '板块3', 'bankuaisan3', '');
INSERT INTO `t_forum` VALUES ('8480ddbc-696f-11e4-9e75-003067974107', '板块1', 'bankuaisan1', '\0');
INSERT INTO `t_forum` VALUES ('972cf6e1-696f-11e4-9e75-003067974107', '板块2', 'bankuaisan2', '');
INSERT INTO `t_forum` VALUES ('a46f7e21-696f-11e4-9e75-003067974107', '板块4', 'bankuaisan4', '');

-- ----------------------------
-- Table structure for t_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `login_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ip` varchar(23) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `_id` char(36) NOT NULL,
  `_showName` varchar(30) NOT NULL,
  `_password` varchar(32) NOT NULL,
  `_last_visit` datetime DEFAULT NULL,
  `_last_ip` varchar(23) DEFAULT NULL,
  `_loginAccount` varchar(64) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `loginAccount_Unique` (`_loginAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('87e8b39c-68b3-11e4-9e75-003067974107', '23', '23', null, null, '23');
INSERT INTO `t_user` VALUES ('c193fb1d-68b3-11e4-9e75-003067974107', 'dpyang', '123123', null, null, 'admin');
DROP TRIGGER IF EXISTS `tr_default_id`;
DELIMITER ;;
CREATE TRIGGER `tr_default_id` BEFORE INSERT ON `t_category` FOR EACH ROW if(new._id='' or new._id is null)
then set new._id=uuid();
end if
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_forum_default_id`;
DELIMITER ;;
CREATE TRIGGER `tr_forum_default_id` BEFORE INSERT ON `t_forum` FOR EACH ROW if(new._id is null or new._id='')
then set new._id=uuid();
end if
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tr_user_default_id`;
DELIMITER ;;
CREATE TRIGGER `tr_user_default_id` BEFORE INSERT ON `t_user` FOR EACH ROW if(new._id = '' or new._id is null)
      then set new._id=uuid();
  end if
;;
DELIMITER ;
