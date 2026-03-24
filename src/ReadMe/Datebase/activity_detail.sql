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

 Date: 20/03/2026 16:00:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_detail
-- ----------------------------
DROP TABLE IF EXISTS `activity_detail`;
CREATE TABLE `activity_detail`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_id` bigint UNSIGNED NOT NULL COMMENT '活动id',
  `uid` bigint UNSIGNED NOT NULL COMMENT '用户id',
  `activity_like` tinyint NOT NULL DEFAULT 0 COMMENT '是否点赞 1是 0否',
  `activity_comment` tinyint NOT NULL DEFAULT 0 COMMENT '是否评论 1是 0否',
  `activity_comment_detail` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_activity_user`(`activity_id`, `uid`) USING BTREE,
  INDEX `idx_activity_detail_activity`(`activity_id`) USING BTREE,
  INDEX `idx_activity_detail_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动点赞评论详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_detail
-- ----------------------------
INSERT INTO `activity_detail` VALUES (1, 10001, 10006, 1, 0, NULL);
INSERT INTO `activity_detail` VALUES (2, 10001, 10007, 0, 1, '这是一条评论');
INSERT INTO `activity_detail` VALUES (3, 10004, 10007, 0, 1, '1');

SET FOREIGN_KEY_CHECKS = 1;
