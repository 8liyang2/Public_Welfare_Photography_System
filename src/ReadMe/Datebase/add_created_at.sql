-- 为 uphoto_detail 表添加 created_at 字段
ALTER TABLE `uphoto_detail` 
ADD COLUMN `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
AFTER `uphoto_comment_detail`;

-- 为 activity_detail 表添加 created_at 字段
ALTER TABLE `activity_detail` 
ADD COLUMN `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
AFTER `activity_comment_detail`;
