/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50173
Source Host           : 127.0.0.1:3306
Source Database       : sampledb

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-11-12 23:00:49
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
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `_id` char(36) NOT NULL,
  `_categoryid` char(36) DEFAULT NULL,
  `_name` varchar(128) DEFAULT NULL,
  `_nameEn` varchar(128) DEFAULT NULL,
  `_content` text,
  `_contentEn` text,
  `_keyWord` varchar(256) DEFAULT NULL,
  `_keyWordEn` varchar(256) DEFAULT NULL,
  `_description` varchar(512) DEFAULT NULL,
  `_descriptionEn` varchar(512) DEFAULT NULL,
  `_isEnabled` bit(1) DEFAULT NULL,
  `_isFirst` bit(1) DEFAULT NULL,
  `_viewCount` smallint(6) DEFAULT NULL,
  `_createDate` datetime DEFAULT NULL,
  `_updateDate` datetime DEFAULT NULL,
  `_gurl` varchar(256) DEFAULT NULL,
  `_zpic` varchar(512) DEFAULT NULL,
  `_homePic` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `fk_article_category_id` (`_categoryid`),
  CONSTRAINT `fk_article_category_id` FOREIGN KEY (`_categoryid`) REFERENCES `t_category` (`_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('2b554d92-6a7a-11e4-acbe-ce6db74f650d', '6016ceda-6982-11e4-9e75-003067974107', '佛山举办比基尼推手大赛 俊男美女\"肉搏\"(图)', 'foshanjubanbijinituishoudasaijunnanmeinv\"rbbo\"(tu)', '北京时间11月11日，在全民欢度“双11”节日的时候，广东省佛山市正在庆祝它们获得被国家体育总局武术运动管理中心授予“中国武术之城”称号十周年。佛山市武术协会举办了一系列庆祝活动，“11.11光棍节比基尼”推手大赛正是此次纪念活动之一。\n', '北京时间11月11日，在全民欢度“双11”节日的时候，广东省佛山市正在庆祝它们获得被国家体育总局武术运动管理中心授予“中国武术之城”称号十周年。佛山市武术协会举办了一系列庆祝活动，“11.11光棍节比基尼”推手大赛正是此次纪念活动之一。\n', '佛山,比基尼,推手大赛', 'foshan,bijini,tuishoudasai', '广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n\n据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n', '广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n\n据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n', '', '\0', '0', '2014-11-12 00:00:00', '2014-11-12 00:00:00', null, null, null);
INSERT INTO `t_article` VALUES ('3139fb91-6a7a-11e4-acbe-ce6db74f650d', '6016ceda-6982-11e4-9e75-003067974107', '中方回应日本外相钓鱼岛言论 敦促日方谨言慎行', 'foshanjubanbijinituishoudasaijunnanmeinv\"rbbo\"(tu)', '中新网11月12日电 据中国驻日本大使馆网站消息，中国驻日使馆发言人12日回应日本外相岸田文雄此前关于钓鱼岛言论，再次强调钓鱼岛是中国固有领土，中方维护国家领土主权的决心和意志坚定不移，敦促日方在钓鱼岛问题上谨言慎行，停止一切损害中国领土主权的行为。\n\n据日本媒体报道，11月11日上午，日本外相岸田文雄在记者会上针对中日四点原则共识中涉及钓鱼岛内容应询称，日本政府在钓鱼岛问题上关于不存在领土问题的立场没有变化，并列举中方划设东海防空识别区和东海资源开发问题称东海处于紧张状态，日中双方主张不同。\n\n \n\n \n\n对此，中国驻日本使馆发言人表示，对日方有关言论表示严重关切和不满。钓鱼岛是中国固有领土。中日双方日前发表的四点原则共识的含义和精神是清楚的。近年来日方无视中方在钓鱼岛问题上的立场，执意采取单方面挑衅行动，是导致当前钓鱼岛紧张局势的根源。\n\n发言人称，中方维护国家领土主权的决心和意志坚定不移，同时始终致力于通过对话磋商管控和解决钓鱼岛问题。我们敦促日方正视历史和事实，信守承诺，按照原则共识精神同中方相向而行，在钓鱼岛问题上谨言慎行，停止一切损害中国领土主权的行为。\n', '北京时间11月11日，在全民欢度“双11”节日的时候，广东省佛山市正在庆祝它们获得被国家体育总局武术运动管理中心授予“中国武术之城”称号十周年。佛山市武术协会举办了一系列庆祝活动，“11.11光棍节比基尼”推手大赛正是此次纪念活动之一。\n', '佛山,比基尼,推手大赛', 'foshan,bijini,tuishoudasai', '广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n\n据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n', '广东省第十届运动会太极拳冠军、佛山市武术协会副秘书长黄伟忠介绍，今年“双十一”晚上18：00～21：00在佛山市禅城区普君新城设立比基尼推手擂台PK赛，来自高等院校、白领青年和未婚女青年参加了这场中泰武术高手的挑战。此外，还有来自泰国的拳击选手也报名参加“11.11光棍节比基尼”佛山推手大赛，与中国年轻人一道庆祝武术名城十周年纪念活动。\n\n据了解，不分男女老幼，只要是对推手功夫感兴趣，均可报名参加。参赛选手穿着比基尼进行一对一PK赛，在教练的指导下运用推手功夫制胜对手，最终胜利者将获得由大会提供的2000元奖金。\n', '', '\0', '3', '2014-11-12 00:00:00', '2014-11-12 00:00:00', null, null, null);

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
DROP TRIGGER IF EXISTS `tr_article_default_id`;
DELIMITER ;;
CREATE TRIGGER `tr_article_default_id` BEFORE INSERT ON `t_article` FOR EACH ROW if(new._id is null or new._id = '')
then set new._id=uuid(),new._viewCount=0;
end if
;;
DELIMITER ;
