/*
 Navicat Premium Data Transfer

 Source Server         : weibo
 Source Server Type    : MySQL
 Source Server Version : 50649 (5.6.49)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50649 (5.6.49)
 File Encoding         : 65001

 Date: 19/10/2026 06:02:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account`  (
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('wangjianhao', '601140', '3145357044@qq.com');
INSERT INTO `t_account` VALUES ('wjh', '123456', 'wjh@qq.com');
INSERT INTO `t_account` VALUES ('wzh', '123456', 'wzh@qq.com');

-- ----------------------------
-- Table structure for t_blah
-- ----------------------------
DROP TABLE IF EXISTS `t_blah`;
CREATE TABLE `t_blah`  (
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `txt` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `t_blah_ibfk_1` FOREIGN KEY (`name`) REFERENCES `t_account` (`name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blah
-- ----------------------------
INSERT INTO `t_blah` VALUES ('wjh', '2026-01-06 00:00:00', 'yr是我儿');
INSERT INTO `t_blah` VALUES ('wangjianhao', '2026-01-06 17:28:42', '213131313');
INSERT INTO `t_blah` VALUES ('wangjianhao', '2026-01-06 17:28:44', '31313131');
INSERT INTO `t_blah` VALUES ('wzh', '2026-02-16 03:45:14', 'sbwangzihao\r\n');

SET FOREIGN_KEY_CHECKS = 1;
