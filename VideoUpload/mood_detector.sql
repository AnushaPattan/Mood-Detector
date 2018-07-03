/*
MySQL Data Transfer
Source Host: localhost
Source Database: mood_detector
Target Host: localhost
Target Database: mood_detector
Date: 4/30/2018 10:30:18 AM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `file_name` varchar(80) NOT NULL,
  `catg` varchar(30) NOT NULL,
  PRIMARY KEY  (`file_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user_id` varchar(30) NOT NULL default '',
  `pass` varchar(30) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_reg
-- ----------------------------
DROP TABLE IF EXISTS `user_reg`;
CREATE TABLE `user_reg` (
  `name` varchar(50) default NULL,
  `email` varchar(50) NOT NULL default '',
  `pass` varchar(50) default NULL,
  PRIMARY KEY  (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `file_info` VALUES ('movie.mp4', 'Happy');
INSERT INTO `login` VALUES ('admin@gmail.com', '123456');
INSERT INTO `user_reg` VALUES ('aaa', 'aaa@gmail.com', '111');
INSERT INTO `user_reg` VALUES ('ameena', 'ameena@gmail.com', '123');
INSERT INTO `user_reg` VALUES ('Anusha', 'pattananusha0@gmail.com', '12345');
INSERT INTO `user_reg` VALUES ('Raj', 'raj@gmail.com', '12345');
