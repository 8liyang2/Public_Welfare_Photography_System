-- 为 activity 表增加 activity_cover_image 字段（用于本地封面图片相对路径）
-- 若你已执行过带该字段的建表脚本，可跳过此文件。

ALTER TABLE `activity`
ADD COLUMN `activity_cover_image` VARCHAR(255) NULL COMMENT '活动封面图相对路径（本地）'
AFTER `activity_picture_id`;

