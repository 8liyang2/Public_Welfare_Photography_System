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

 Date: 20/03/2026 16:01:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动id码',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `activity_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `activity_description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动描述',
  `uid` bigint UNSIGNED NOT NULL COMMENT '创建者uid',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者昵称快照',
  `permission` tinyint NOT NULL DEFAULT 1 COMMENT '创建者权限快照',
  `activity_category` tinyint NOT NULL COMMENT '1官方摄影征集/2个人活动/3相册集',
  `activity_status` tinyint NOT NULL COMMENT '0仅自己/1公开征集/2仅读/3条件加入(扩展)',
  `activity_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动地点',
  `activity_time_early` date NOT NULL COMMENT '活动开始日期',
  `activity_time_end` date NOT NULL COMMENT '活动结束日期',
  `activity_picture_id` bigint UNSIGNED NOT NULL COMMENT '封面图片id',
  `activity_cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动封面图相对路径（本地）',
  `activity_like_number` int NOT NULL DEFAULT 0 COMMENT '点赞量',
  `activity_comment_number` int NOT NULL DEFAULT 0 COMMENT '评论数',
  `activity_review_status` tinyint NOT NULL DEFAULT 1 COMMENT '1未审核/0不通过/2通过',
  PRIMARY KEY (`activity_id`) USING BTREE,
  INDEX `idx_activity_time`(`activity_time_early`, `activity_time_end`) USING BTREE,
  INDEX `idx_activity_category`(`activity_category`) USING BTREE,
  INDEX `idx_activity_review_status`(`activity_review_status`) USING BTREE,
  INDEX `idx_activity_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '活动泛搜索表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (10001, '2026-03-17 11:31:09', '测试活动A(已修改)', '', 10007, '用户1773715496362', 2, 2, 1, '75375', '2026-02-25', '2026-02-25', 20001, 'picture/activity/activity_10001.png', 1, 1, 2);
INSERT INTO `activity` VALUES (10003, '2026-03-17 12:32:54', '测试活动A', '活动描述', 10007, '用户1773715496362', 2, 2, 1, '北京', '2026-03-17', '2026-03-17', 20003, 'picture/activity/activity_10003.png', 0, 0, 2);
INSERT INTO `activity` VALUES (10004, '2026-03-18 11:53:53', '新活动1', '457425', 10007, '用户1773715496362', 0, 1, 1, '', '2026-03-20', '2026-03-20', 20004, 'picture/activity/activity_10004.png', 0, 1, 2);
INSERT INTO `activity` VALUES (10005, '2026-03-19 09:35:10', '7867687', '878567857832', 10007, '管理员', 0, 2, 1, '', '2026-03-28', '2026-03-28', 20005, 'picture/activity/activity_10005.png', 0, 0, 2);
INSERT INTO `activity` VALUES (10006, '2026-03-20 13:37:42', '35737', '75867537523752753752375237532', 10007, '管理员', 0, 2, 1, '', '2026-03-20', '2026-03-20', 20006, NULL, 0, 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
