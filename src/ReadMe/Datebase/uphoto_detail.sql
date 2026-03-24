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

 Date: 20/03/2026 16:02:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for uphoto_detail
-- ----------------------------
DROP TABLE IF EXISTS `uphoto_detail`;
CREATE TABLE `uphoto_detail`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `uphoto_id` bigint UNSIGNED NOT NULL COMMENT '作品id',
  `uid` bigint UNSIGNED NOT NULL COMMENT '用户id',
  `uphoto_like` tinyint NOT NULL DEFAULT 0 COMMENT '是否点赞 1是 0否',
  `uphoto_comment` tinyint NOT NULL DEFAULT 0 COMMENT '是否评论 1是 0否',
  `uphoto_comment_detail` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uphoto_user`(`uphoto_id`, `uid`) USING BTREE,
  INDEX `idx_uphoto_detail_uphoto`(`uphoto_id`) USING BTREE,
  INDEX `idx_uphoto_detail_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '作品点赞评论详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uphoto_detail
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
