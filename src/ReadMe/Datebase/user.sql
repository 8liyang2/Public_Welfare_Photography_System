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

 Date: 20/03/2026 16:01:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '账户id码，从10001开始',
  `loginname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'MD5 加密密码',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像URL',
  `personal_profile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人简介',
  `permission` tinyint NOT NULL DEFAULT 1 COMMENT '2游客/1普通/0管理员',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `activity_created` int NOT NULL DEFAULT 0 COMMENT '活动创建数',
  `uphoto_created` int NOT NULL DEFAULT 0 COMMENT '作品创建数',
  `point` int NOT NULL DEFAULT 0 COMMENT '积分',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `uk_user_loginname`(`loginname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10003, 'test500', 'e10adc3949ba59abbe56e057f20f883e', '用户1773588613798', NULL, 'https://example.com/a.png', NULL, 1, NULL, 0, 0, 0, '2026-03-15 23:30:13', '2026-03-18 12:01:56');
INSERT INTO `user` VALUES (10004, 'test_for_picture', 'e10adc3949ba59abbe56e057f20f883e', '新昵称10004', 'user10001@example.com', 'picture/avatar/uid_10004.png', '这是个人简介', 1, '2000-01-01', 0, 0, 0, '2026-03-17 09:30:20', '2026-03-17 09:58:17');
INSERT INTO `user` VALUES (10006, 'TaoYuLong', 'e10adc3949ba59abbe56e057f20f883e', '陶裕龙', 'TaoYuLong@qq.com', 'picture/avatar/uid_tmp_1773715239734.png', '这是简介', 1, '2003-12-12', 0, 0, 689, '2026-03-17 10:40:39', '2026-03-20 15:58:16');
INSERT INTO `user` VALUES (10007, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 'YoungLet@126.com', 'picture/avatar/uid_tmp_1773715496362.png', '这是简介', 0, '2026-02-28', 0, 0, 99415, '2026-03-17 10:44:56', '2026-03-20 15:03:14');

SET FOREIGN_KEY_CHECKS = 1;
