# 评论管理 API Postman 测试说明

本文档提供了评论管理相关 API 的 Postman 测试方法，包括活动评论管理和作品评论管理。

## 测试环境设置

1. **Postman 版本**：推荐使用 Postman v9.0+ 版本
2. **基础 URL**：`http://localhost:8080/pwps`
3. **认证**：所有 API 需要在请求头中添加 `Authorization` 字段，值为 `Bearer {token}`，其中 `{token}` 是通过登录接口获取的 JWT token

## 1. 活动评论管理 API

### 1.1 获取活动评论列表（带分页和搜索）

- **请求方法**：GET
- **请求 URL**：`/api/admin/comments/activity?page={page}&size={size}&keyword={keyword}`
- **请求参数**：
  - `page`：页码（从1开始）
  - `size`：每页数量（默认50）
  - `keyword`：搜索关键词（可选）
- **请求头**：
  ```
  Authorization: Bearer {token}
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "获取活动评论列表成功",
    "data": {
      "records": [
        {
          "id": 1,
          "aid": 1,
          "uid": 1,
          "content": "这是一条活动评论",
          "createTime": "2026-03-18 12:00:00",
          "user": {
            "id": 1,
            "username": "admin"
          },
          "activity": {
            "id": 1,
            "title": "测试活动"
          }
        }
      ],
      "total": 100,
      "size": 50,
      "current": 1,
      "pages": 2
    }
  }
  ```

### 1.2 删除活动评论

- **请求方法**：DELETE
- **请求 URL**：`/api/admin/comments/activity/{id}`
- **请求参数**：
  - `id`：评论 ID
- **请求头**：
  ```
  Authorization: Bearer {token}
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "删除活动评论成功"
  }
  ```

## 2. 作品评论管理 API

### 2.1 获取作品评论列表（带分页和搜索）

- **请求方法**：GET
- **请求 URL**：`/api/admin/comments/photo?page={page}&size={size}&keyword={keyword}`
- **请求参数**：
  - `page`：页码（从1开始）
  - `size`：每页数量（默认50）
  - `keyword`：搜索关键词（可选）
- **请求头**：
  ```
  Authorization: Bearer {token}
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "获取作品评论列表成功",
    "data": {
      "records": [
        {
          "id": 1,
          "pid": 1,
          "uid": 1,
          "content": "这是一条作品评论",
          "createTime": "2026-03-18 12:00:00",
          "user": {
            "id": 1,
            "username": "admin"
          },
          "photo": {
            "id": 1,
            "title": "测试作品"
          }
        }
      ],
      "total": 50,
      "size": 50,
      "current": 1,
      "pages": 1
    }
  }
  ```

### 2.2 删除作品评论

- **请求方法**：DELETE
- **请求 URL**：`/api/admin/comments/photo/{id}`
- **请求参数**：
  - `id`：评论 ID
- **请求头**：
  ```
  Authorization: Bearer {token}
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "删除作品评论成功"
  }
  ```

## 3. 公共评论和点赞 API

### 3.1 活动点赞

- **请求方法**：POST
- **请求 URL**：`/api/activity/like`
- **请求头**：
  ```
  Authorization: Bearer {token}
  Content-Type: application/json
  ```
- **请求体**：
  ```json
  {
    "aid": 1
  }
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "点赞成功"
  }
  ```

### 3.2 活动评论

- **请求方法**：POST
- **请求 URL**：`/api/activity/comment`
- **请求头**：
  ```
  Authorization: Bearer {token}
  Content-Type: application/json
  ```
- **请求体**：
  ```json
  {
    "aid": 1,
    "content": "这是一条活动评论"
  }
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "评论成功"
  }
  ```

### 3.3 作品点赞

- **请求方法**：POST
- **请求 URL**：`/api/uphoto/like/{pid}`
- **请求参数**：
  - `pid`：作品 ID
- **请求头**：
  ```
  Authorization: Bearer {token}
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "点赞成功"
  }
  ```

### 3.4 作品评论

- **请求方法**：POST
- **请求 URL**：`/api/uphoto/comment/{pid}`
- **请求参数**：
  - `pid`：作品 ID
- **请求头**：
  ```
  Authorization: Bearer {token}
  Content-Type: application/json
  ```
- **请求体**：
  ```json
  {
    "content": "这是一条作品评论"
  }
  ```
- **预期响应**：
  ```json
  {
    "code": 200,
    "msg": "评论成功"
  }
  ```

## 4. 测试步骤

1. **获取认证 Token**：
   - 先调用用户登录接口获取 JWT token
   - 将 token 保存为环境变量，方便后续测试使用

2. **测试评论管理 API**：
   - 测试活动评论列表获取（带分页和搜索）
   - 测试活动评论删除
   - 测试作品评论列表获取（带分页和搜索）
   - 测试作品评论删除

3. **测试公共评论和点赞 API**：
   - 测试活动点赞
   - 测试活动评论
   - 测试作品点赞
   - 测试作品评论

## 5. 测试注意事项

1. **权限验证**：确保使用管理员账号进行测试，因为评论管理 API 需要管理员权限
2. **数据准备**：测试前需要确保数据库中存在足够的活动和作品数据，以及相关的评论数据
3. **分页测试**：测试不同页码和每页数量的组合，确保分页功能正常
4. **搜索测试**：测试不同关键词的搜索功能，确保搜索结果正确
5. **错误处理**：测试无效参数、权限不足等情况的错误处理

## 6. 测试用例

| 测试用例 | API 端点 | 预期结果 |
|---------|---------|--------|
| 1. 获取活动评论列表 | GET /api/admin/comments/activity?page=1&size=50 | 返回活动评论列表，包含分页信息 |
| 2. 搜索活动评论 | GET /api/admin/comments/activity?page=1&size=50&keyword=测试 | 返回包含关键词"测试"的活动评论 |
| 3. 删除活动评论 | DELETE /api/admin/comments/activity/1 | 成功删除评论，返回成功消息 |
| 4. 获取作品评论列表 | GET /api/admin/comments/photo?page=1&size=50 | 返回作品评论列表，包含分页信息 |
| 5. 搜索作品评论 | GET /api/admin/comments/photo?page=1&size=50&keyword=测试 | 返回包含关键词"测试"的作品评论 |
| 6. 删除作品评论 | DELETE /api/admin/comments/photo/1 | 成功删除评论，返回成功消息 |
| 7. 活动点赞 | POST /api/activity/like | 成功点赞，返回成功消息 |
| 8. 活动评论 | POST /api/activity/comment | 成功评论，返回成功消息 |
| 9. 作品点赞 | POST /api/uphoto/like/1 | 成功点赞，返回成功消息 |
| 10. 作品评论 | POST /api/uphoto/comment/1 | 成功评论，返回成功消息 |

## 7. 环境变量设置

在 Postman 中设置以下环境变量：

| 变量名 | 值 | 描述 |
|-------|-----|------|
| base_url | http://localhost:8080/pwps | 基础 URL |
| token | {your_token} | 登录获取的 JWT token |
| activity_id | 1 | 测试用活动 ID |
| photo_id | 1 | 测试用作品 ID |
| comment_id | 1 | 测试用评论 ID |

## 8. 导入测试集合

可以将以上测试用例导出为 Postman 集合，方便团队成员共享和执行测试。

1. 在 Postman 中创建一个新的集合
2. 添加上述所有 API 测试用例
3. 设置环境变量
4. 运行测试集合

## 9. 测试结果验证

执行测试后，验证以下内容：

1. 所有 API 返回正确的 HTTP 状态码
2. 响应体格式正确，包含预期的字段
3. 分页功能正常工作
4. 搜索功能返回正确的结果
5. 删除操作能成功删除评论
6. 点赞和评论操作能成功执行

通过以上测试，可以确保评论管理功能的正常运行。