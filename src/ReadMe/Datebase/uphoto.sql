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

 Date: 20/03/2026 16:00:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for uphoto
-- ----------------------------
DROP TABLE IF EXISTS `uphoto`;
CREATE TABLE `uphoto`  (
  `uphoto_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '摄影作品ID',
  `uid` bigint UNSIGNED NOT NULL COMMENT '作者uid',
  `uphoto_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作品标题',
  `uphoto_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作品描述',
  `uphoto_label_0` tinyint NOT NULL DEFAULT 0 COMMENT '标签:其他',
  `uphoto_label_1` tinyint NOT NULL DEFAULT 0 COMMENT '标签:记录',
  `uphoto_label_2` tinyint NOT NULL DEFAULT 0 COMMENT '标签:艺术',
  `uphoto_label_3` tinyint NOT NULL DEFAULT 0 COMMENT '标签:商业',
  `uphoto_label_4` tinyint NOT NULL DEFAULT 0 COMMENT '标签:人像',
  `uphoto_label_5` tinyint NOT NULL DEFAULT 0 COMMENT '标签:天文',
  `uphoto_label_6` tinyint NOT NULL DEFAULT 0 COMMENT '标签:航拍',
  `uphoto_label_7` tinyint NOT NULL DEFAULT 0 COMMENT '标签:城景',
  `uphoto_label_8` tinyint NOT NULL DEFAULT 0 COMMENT '标签:随手拍',
  `uphoto_label_9` tinyint NOT NULL DEFAULT 0 COMMENT '标签:旅拍',
  `created_at` date NOT NULL COMMENT '创建日期',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '作品状态 1待审/0不通过/2通过',
  `activity_id_1` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联活动1(0为无)',
  `activity_id_2` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联活动2(0为无)',
  `activity_id_3` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '关联活动3(0为无)',
  `uphoto_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作品文件路径或URL',
  `uphoto_status` tinyint NOT NULL DEFAULT 1 COMMENT '隐私度 1公开/2仅自己可见',
  `uphoto_like_number` int NOT NULL DEFAULT 0 COMMENT '点赞量',
  `uphoto_comment_number` int NOT NULL DEFAULT 0 COMMENT '评论数',
  `uphoto_review_status` tinyint NOT NULL DEFAULT 1 COMMENT '审核情况 1未审/0不通过/2通过',
  `activity_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动ID',
  PRIMARY KEY (`uphoto_id`) USING BTREE,
  INDEX `idx_uphoto_created_at`(`created_at`) USING BTREE,
  INDEX `idx_uphoto_status`(`status`) USING BTREE,
  INDEX `idx_uphoto_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '作品泛搜索表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of uphoto
-- ----------------------------
INSERT INTO `uphoto` VALUES (1, 10007, '4564564', '45345', 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, '2026-03-19', 1, 0, 0, 0, 'picture/uphoto/uphoto_1773891574074.png', 1, 0, 0, 1, '10001');
INSERT INTO `uphoto` VALUES (2, 1, '测试作品', '这是一个测试作品', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2026-03-19', 1, 0, 0, 0, 'picture/uphoto/test.png', 1, 0, 0, 2, '1');
INSERT INTO `uphoto` VALUES (3, 10007, '测试作品', '这是一个测试作品', 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, '2026-03-19', 1, 0, 0, 0, 'picture/uphoto/uphoto_1773915091419.png', 1, 0, 0, 1, '10003');
INSERT INTO `uphoto` VALUES (4, 10007, '7867685', '7867856786', 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, '2026-03-19', 1, 0, 0, 0, 'picture/uphoto/uphoto_1773915529130.png', 1, 0, 0, 1, '10001');
INSERT INTO `uphoto` VALUES (5, 10007, '453423', '45345374', 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, '2026-03-20', 1, 0, 0, 0, 'picture/uphoto/uphoto_1773985049876.png', 1, 0, 0, 1, '10110');
INSERT INTO `uphoto` VALUES (6, 10006, 'text1', '1455665', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2026-03-20', 1, 0, 0, 0, 'picture/uphoto/uphoto_1773993496123.png', 1, 0, 0, 1, '');

SET FOREIGN_KEY_CHECKS = 1;
