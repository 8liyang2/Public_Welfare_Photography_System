-- 为 uphoto_detail 表添加 created_at 字段
ALTER TABLE uphoto_detail ADD COLUMN created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评论/点赞时间';

-- 为 activity_detail 表添加 created_at 字段
ALTER TABLE activity_detail ADD COLUMN created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '评论/点赞时间';
