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

 Date: 20/03/2026 16:02:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_uphoto_stat
-- ----------------------------
DROP TABLE IF EXISTS `activity_uphoto_stat`;
CREATE TABLE `activity_uphoto_stat`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_id` bigint UNSIGNED NOT NULL COMMENT '活动id',
  `uphoto_count` int NOT NULL DEFAULT 0 COMMENT '活动内作品数量',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_stat`(`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动作品统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_uphoto_stat
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
