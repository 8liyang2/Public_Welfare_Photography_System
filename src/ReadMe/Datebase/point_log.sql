/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80045
 Source Host           : localhost:3306
 Source Schema         : pwps

 Target Server Type    : MySQL
 Target Server Version : 80045
 File Encoding         : 65001

 Date: 20/03/2026 16:01:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for point_log
-- ----------------------------
DROP TABLE IF EXISTS `point_log`;
CREATE TABLE `point_log`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `uid` bigint UNSIGNED NOT NULL COMMENT '用户id',
  `change_value` int NOT NULL COMMENT '积分变更值(可为负)',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '变更原因(如:上传作品/删除活动等)',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_point_log_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '积分变更日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of point_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
