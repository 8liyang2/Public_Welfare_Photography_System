## 广场模块（接口联调测试）

本文件用于按接口文档验证“广场模块”相关接口。

### 1. 相关接口

- 作品泛搜索：`POST /api/main/uphoto/search`（支持按最新、最热、标签、时间区间筛选）
- 作品点赞：`POST /api/main/uphoto/like`
- 作品评论：`POST /api/main/uphoto/comment`

### 2. 页面功能

- **URL 格式**：`/main/ground/search/{mode}`
  - `s0`：最新作品（默认）
  - `s1`：点赞最多
  - `date`：时间区间

- **筛选功能**：
  - 排序方式：最新作品、点赞最多、指定时间区间
  - 标签筛选：支持选择单个标签进行筛选
  - 时间区间：支持选择开始日期和结束日期

### 3. 推荐测试顺序

1. 打开广场页面：`/main/ground/search/s0`（默认显示最新作品）
2. 切换到点赞最多：点击“点赞最多”按钮
3. 切换到时间区间：点击“指定时间区间”按钮，选择日期范围并点击“应用”
4. 测试标签筛选：选择不同标签，查看筛选结果
5. 测试分页功能：浏览多页作品

### 4. 日期验证

- 开始日期不能晚于结束日期
- 若输入无效日期，会弹出提示信息

### 5. 接口测试

- 使用 Postman 测试 `POST /api/main/uphoto/search` 接口
- 测试不同参数组合：
  - 按最新排序：`{"uid": 10001, "main_uphoto_search_1": 1}`
  - 按最热排序：`{"uid": 10001, "main_uphoto_search_1": 2}`
  - 按标签筛选：`{"uid": 10001, "main_uphoto_search_1": 1, "main_uphoto_search_2": 1}`
  - 按时间区间：`{"uid": 10001, "main_uphoto_search_1": 1, "main_uphoto_search_3_earlydate": "2026-01-01", "main_uphoto_search_3_latedate": "2026-12-31"}`

### 6. 注意事项

- 若后端接口未完全实现，页面会使用假数据占位
- 作品详情页面暂未实现，点击作品会弹出提示
- 确保用户已登录，否则部分功能可能受限