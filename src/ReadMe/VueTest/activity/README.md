## 活动模块（接口联调测试）

本文件用于按接口文档验证“活动模块 10 个接口 + 积分效果”。

### 1. 相关接口

- 创建：`POST /api/user/activity/create`（-100 积分，不足则失败）
- 编辑：`POST /api/user/activity/edit`（-5 积分）
- 删除：`DELETE /api/user/activity/delete`（-300 积分，且按 category 权限限制）
- 个人活动查询：`GET /api/user/activity/query`
- 待审列表：`GET /api/admin/activity/review`
- 审核通否：`GET /api/admin/activity/review/`
- 常规搜索：`GET /api/main/activity/search`（仅返回审核通过）
- 详情：`POST /api/main/activity/{id}/get`（未审核仅管理员/作者可见）
- 点赞：`POST /api/main/activity/{id}/like`（给活动作者 +1/-1 积分，作者不能自赞）
- 评论：`POST /api/main/activity/{id}/comment`（评论者 +5 积分，每人每活动仅一次）

### 2. 页面功能

- **URL 格式**：`/main/activity/search/{mode}`
  - `s0`：最新活动（默认）
  - `s1`：最热活动
  - `date`：时间区间

- **筛选功能**：
  - 排序方式：最新活动、最热活动、指定时间区间
  - 活动类型：官方、个人、作品集
  - 时间区间：支持选择开始日期和结束日期

### 3. 推荐测试顺序

1. 打开活动页面：`/main/activity/search/s0`（默认显示最新活动）
2. 切换到最热活动：点击“最热活动”按钮
3. 切换到时间区间：点击“指定时间区间”按钮，选择日期范围并点击“应用”
4. 测试活动类型筛选：选择不同活动类型，查看筛选结果
5. 测试分页功能：浏览多页活动

### 4. 建库注意

活动封面字段依赖 `activity.activity_cover_image`，若你旧表没有该字段，请先执行：

- `src/ReadMe/Datebase/upgrade_activity_cover_image.sql`

### 3. 推荐测试顺序

1. 用普通用户创建活动（应扣 100）。
2. 管理员查询待审核列表（应能看到）。
3. 管理员审核通过（review_status=2）。
4. 主站搜索活动（应能搜到）。
5. 普通用户 B 对活动点赞（作者积分 +1），再取消点赞（作者 -1，不低于 0）。
6. 普通用户 B 发表评论（自己 +5）。

### 4. Postman 文档

详见：`test-docs/activity/activity-postman.md`

