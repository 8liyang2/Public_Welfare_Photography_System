# PWPS API 接口文档

## 目录
1. [用户相关接口](#用户相关接口)
2. [作品相关接口](#作品相关接口)
3. [活动相关接口](#活动相关接口)
4. [互动相关接口](#互动相关接口)
5. [公告相关接口](#公告相关接口)
6. [管理员接口](#管理员接口)

---

## 用户相关接口

### 1. 用户注册
- **接口路径**: `POST /api/user/register`
- **功能说明**: 新用户注册账号
- **请求参数**:
  ```json
  {
    "loginname": "用户名",
    "password": "密码",
    "username": "昵称"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "uid": 12345
  }
  ```
- **测试方法**:
  ```bash
  curl -X POST http://localhost:8080/api/user/register \
    -H "Content-Type: application/json" \
    -d '{"loginname":"testuser","password":"123456","username":"测试用户"}'
  ```

### 2. 用户登录
- **接口路径**: `POST /api/user/login`
- **功能说明**: 用户登录系统
- **请求参数**:
  ```json
  {
    "loginname": "用户名",
    "password": "密码"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "uid": 12345,
    "permission": 1,
    "avatar": "头像路径",
    "loginname": "用户名",
    "username": "昵称"
  }
  ```
- **测试方法**:
  ```bash
  curl -X POST http://localhost:8080/api/user/login \
    -H "Content-Type: application/json" \
    -d '{"loginname":"testuser","password":"123456"}'
  ```

### 3. 用户信息更新
- **接口路径**: `PUT /api/user/update`
- **功能说明**: 更新用户个人信息
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "username": "新昵称",
    "avatar": "新头像路径",
    "personal_profile": "个人简介"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

### 4. 获取用户信息
- **接口路径**: `GET /api/user/info`
- **功能说明**: 获取指定用户的信息
- **请求参数**:
  - `uid`: 用户ID (必填)
- **响应格式**:
  ```json
  {
    "success": true,
    "uid": 12345,
    "loginname": "用户名",
    "username": "昵称",
    "avatar": "头像路径",
    "personal_profile": "个人简介",
    "point": 100,
    "permission": 1
  }
  ```
- **测试方法**:
  ```bash
  curl "http://localhost:8080/api/user/info?uid=10001"
  ```

### 5. 修改密码
- **接口路径**: `POST /api/user/password/change`
- **功能说明**: 修改用户密码
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "old_password": "旧密码",
    "new_password": "新密码"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

---

## 作品相关接口

### 1. 上传作品
- **接口路径**: `POST /api/user/{uid}/uphoto/upload`
- **功能说明**: 用户上传摄影作品
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "uphoto_title": "作品标题",
    "uphoto_description": "作品描述",
    "uphoto": "图片路径",
    "uphoto_label_0": 1,
    "uphoto_label_1": 0,
    "activity_id": 10001
  }
  ```
- **标签说明**:
  - 标签0-9: 分别对应 自然、人像、建筑、风景、动物、城市、艺术、抽象、黑白、其他
- **响应格式**:
  ```json
  {
    "success": true,
    "upthoto_id": 12345
  }
  ```

### 2. 搜索作品
- **接口路径**: `POST /api/main/uphoto/search`
- **功能说明**: 搜索公开作品
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "activity_id": null,
    "main_uphoto_search_1": 1,
    "main_uphoto_search_2": 0,
    "main_uphoto_search_3_earlydate": "2024-01-01",
    "main_uphoto_search_3_latedate": "2024-12-31"
  }
  ```
- **参数说明**:
  - `main_uphoto_search_1`: 1=按时间排序, 2=按点赞数排序
  - `main_uphoto_search_2`: 标签筛选 (0-9)
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "upthoto_id": 12345,
        "uid": 10001,
        "username": "用户名",
        "avatar": "头像路径",
        "uphoto": "图片路径",
        "uphoto_title": "作品标题",
        "uphoto_like_count": 10
      }
    ]
  }
  ```

### 3. 获取作品详情
- **接口路径**: `POST /api/main/uphoto/{uphotoId}/get`
- **功能说明**: 获取指定作品的详细信息
- **请求参数**:
  ```json
  {
    "uid": 12345
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "upthoto_id": 12345,
    "uid": 10001,
    "username": "用户名",
    "avatar": "头像路径",
    "uphoto": "图片路径",
    "uphoto_title": "作品标题",
    "uphoto_description": "作品描述",
    "uphoto_like_number": 10,
    "uphoto_comment_number": 5,
    "liked": true,
    "activity_id": 10001,
    "activity_name": "活动名称"
  }
  ```
- **测试方法**:
  ```bash
  curl -X POST http://localhost:8080/api/main/uphoto/12345/get \
    -H "Content-Type: application/json" \
    -d '{"uid":10001}'
  ```

### 4. 用户作品搜索
- **接口路径**: `POST /api/user/{uid}/uphoto/search`
- **功能说明**: 搜索指定用户的作品
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "ask_uid": 10001
  }
  ```
- **响应格式**: 同搜索作品接口

### 5. 管理作品
- **接口路径**: `POST /api/user/{uid}/uphoto/manage`
- **功能说明**: 编辑或管理用户自己的作品
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "upthoto_id": 12345,
    "uphoto_title": "新标题",
    "uphoto_description": "新描述"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

### 6. 删除作品
- **接口路径**: `POST /api/user/{uid}/uphoto/delete`
- **功能说明**: 删除用户自己的作品
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "upthoto_id": 12345
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

---

## 活动相关接口

### 1. 创建活动
- **接口路径**: `POST /api/user/activity/create`
- **功能说明**: 用户创建新活动
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "activity_name": "活动名称",
    "activity_description": "活动描述",
    "activity_category": 1,
    "activity_start_time": "2024-01-01",
    "activity_end_time": "2024-12-31",
    "activity_cover_image": "封面图片路径"
  }
  ```
- **分类说明**:
  - 1: 官方摄影征集活动
  - 2: 个人组织摄影活动
  - 3: 相册集
- **响应格式**:
  ```json
  {
    "success": true,
    "activity_id": 10001
  }
  ```

### 2. 搜索活动
- **接口路径**: `GET /api/main/activity/search`
- **功能说明**: 搜索公开活动
- **请求参数**:
  - `uid`: 当前用户ID (必填)
  - `category`: 分类筛选 (可选)
  - `start_date`: 开始日期 (可选, 格式: yyyy-MM-dd)
  - `end_date`: 结束日期 (可选, 格式: yyyy-MM-dd)
  - `sort`: 排序方式, latest=最新, popular=热门
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "activity_id": 10001,
        "activity_name": "活动名称",
        "activity_cover_image": "封面图片",
        "activity_category": 1,
        "activity_like_number": 50,
        "activity_review_status": 2,
        "username": "创建者用户名"
      }
    ]
  }
  ```
- **测试方法**:
  ```bash
  curl "http://localhost:8080/api/main/activity/search?uid=10001&sort=latest"
  ```

### 3. 获取活动详情
- **接口路径**: `POST /api/main/activity/{activityId}/get`
- **功能说明**: 获取指定活动的详细信息
- **请求参数**:
  ```json
  {
    "uid": 12345
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "activity_id": 10001,
    "activity_name": "活动名称",
    "activity_description": "活动描述",
    "activity_cover_image": "封面图片",
    "activity_category": 1,
    "activity_start_time": "2024-01-01",
    "activity_end_time": "2024-12-31",
    "activity_like_number": 50,
    "activity_comment_number": 20,
    "liked": true,
    "uid": 10001,
    "username": "创建者用户名",
    "avatar": "创建者头像"
  }
  ```

### 4. 编辑活动
- **接口路径**: `POST /api/user/activity/edit`
- **功能说明**: 编辑用户自己创建的活动
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "activity_id": 10001,
    "activity_name": "新活动名称",
    "activity_description": "新活动描述"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

### 5. 删除活动
- **接口路径**: `DELETE /api/user/activity/delete`
- **功能说明**: 删除用户自己创建的活动
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "activity_id": 10001
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

### 6. 查询用户活动
- **接口路径**: `GET /api/user/activity/query`
- **功能说明**: 查询指定用户创建的活动列表
- **请求参数**:
  - `uid`: 当前用户ID (必填)
  - `ask_uid`: 要查询的用户ID (必填)
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "activity_id": 10001,
        "activity_name": "活动名称"
      }
    ]
  }
  ```

---

## 互动相关接口

### 1. 点赞作品
- **接口路径**: `POST /api/main/uphoto/like`
- **功能说明**: 为作品点赞或取消点赞
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "upthoto_id": 12345,
    "like_change": 1
  }
  ```
- **参数说明**:
  - `like_change`: 1=点赞, -1=取消点赞
- **响应格式**:
  ```json
  {
    "success": true,
    "liked": true,
    "like_count": 11
  }
  ```

### 2. 评论作品
- **接口路径**: `POST /api/main/uphoto/comment`
- **功能说明**: 为作品添加评论
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "upthoto_id": 12345,
    "uphoto_comment_detail": "评论内容"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "comment_count": 6
  }
  ```

### 3. 获取作品评论列表
- **接口路径**: `GET /api/main/uphoto/{upthotoId}/comments`
- **功能说明**: 获取指定作品的所有评论
- **响应格式**:
  ```json
  {
    "success": true,
    "comments": [
      {
        "id": 1,
        "uid": 10001,
        "username": "用户名",
        "avatar": "头像",
        "uphoto_comment_detail": "评论内容",
        "created_at": "2024-01-01 12:00:00"
      }
    ]
  }
  ```

### 4. 点赞活动
- **接口路径**: `POST /api/main/activity/like`
- **功能说明**: 为活动点赞或取消点赞
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "activity_id": 10001,
    "like_change": 1
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "liked": true,
    "like_count": 51
  }
  ```

### 5. 评论活动
- **接口路径**: `POST /api/main/activity/comment`
- **功能说明**: 为活动添加评论
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "activity_id": 10001,
    "activity_comment_detail": "评论内容"
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "comment_count": 21
  }
  ```

### 6. 获取活动评论列表
- **接口路径**: `GET /api/main/activity/{activityId}/comments`
- **功能说明**: 获取指定活动的所有评论
- **响应格式**:
  ```json
  {
    "success": true,
    "comments": [
      {
        "id": 1,
        "uid": 10001,
        "username": "用户名",
        "avatar": "头像",
        "activity_comment_detail": "评论内容",
        "created_at": "2024-01-01 12:00:00"
      }
    ]
  }
  ```

---

## 公告相关接口

### 1. 获取公告列表
- **接口路径**: `GET /api/main/announcement/list`
- **功能说明**: 获取所有生效的公告
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "id": 1,
        "title": "公告标题",
        "content": "公告内容",
        "status": 1
      }
    ]
  }
  ```
- **测试方法**:
  ```bash
  curl "http://localhost:8080/api/main/announcement/list"
  ```

---

## 管理员接口

### 1. 作品审核列表
- **接口路径**: `GET /api/admin/uphoto/review`
- **功能说明**: 获取待审核的作品列表 (仅管理员)
- **请求参数**:
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0 (管理员)
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "upthoto_id": 12345,
        "uid": 10001,
        "username": "用户名",
        "uphoto": "图片路径",
        "uphoto_title": "作品标题",
        "uphoto_description": "作品描述"
      }
    ]
  }
  ```

### 2. 作品审核决定
- **接口路径**: `GET /api/admin/uphoto/review/`
- **功能说明**: 审核作品 (仅管理员)
- **请求参数**:
  - `upthoto_id`: 作品ID
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
  - `uphoto_review_status`: 0=拒绝, 2=通过
- **响应格式**:
  ```json
  {
    "success": true,
    "upthoto_id": 12345
  }
  ```

### 3. 活动审核列表
- **接口路径**: `GET /api/admin/activity/review`
- **功能说明**: 获取待审核的活动列表 (仅管理员)
- **请求参数**:
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "activity_id": 10001,
        "uid": 10001,
        "username": "用户名",
        "activity_name": "活动名称",
        "activity_cover_image": "封面图片",
        "activity_description": "活动描述"
      }
    ]
  }
  ```

### 4. 活动审核决定
- **接口路径**: `GET /api/admin/activity/review/`
- **功能说明**: 审核活动 (仅管理员)
- **请求参数**:
  - `activity_id`: 活动ID
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
  - `activity_review_status`: 0=拒绝, 2=通过
- **响应格式**:
  ```json
  {
    "success": true,
    "activity_id": 10001
  }
  ```

### 5. 封禁用户
- **接口路径**: `POST /api/admin/user/ban`
- **功能说明**: 封禁用户 (将权限设为2=游客, 仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0,
    "ban_uid": 10001
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "ban_uid": 10001
  }
  ```

### 6. 修改用户权限
- **接口路径**: `POST /api/admin/user/permission`
- **功能说明**: 修改用户权限等级 (仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0,
    "target_uid": 10001,
    "target_perm": 1
  }
  ```
- **权限说明**:
  - 0: 管理员
  - 1: 普通用户
  - 2: 游客
- **响应格式**:
  ```json
  {
    "success": true,
    "target_uid": 10001
  }
  ```

### 7. 获取所有用户列表
- **接口路径**: `GET /api/admin/all_user/search`
- **功能说明**: 获取系统所有用户 (仅管理员)
- **请求参数**:
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "uid": 10001,
        "loginname": "用户名",
        "username": "昵称",
        "permission": 1,
        "point": 100,
        "created_at": "2024-01-01"
      }
    ]
  }
  ```

### 8. 获取活动评论列表 (管理员)
- **接口路径**: `GET /api/admin/comment/activity`
- **功能说明**: 获取所有活动评论 (支持搜索, 仅管理员)
- **请求参数**:
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
  - `page`: 页码 (默认1)
  - `pageSize`: 每页数量 (默认50)
  - `keyword`: 搜索关键字 (可选)
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      {
        "id": 1,
        "activity_id": 10001,
        "uid": 10001,
        "username": "用户名",
        "comment_detail": "评论内容"
      }
    ],
    "total": 100,
    "page": 1,
    "pageSize": 50
  }
  ```

### 9. 获取作品评论列表 (管理员)
- **接口路径**: `GET /api/admin/comment/uphoto`
- **功能说明**: 获取所有作品评论 (支持搜索, 仅管理员)
- **请求参数**: 同活动评论列表
- **响应格式**: 同活动评论列表

### 10. 删除活动评论
- **接口路径**: `POST /api/admin/comment/activity/delete`
- **功能说明**: 删除活动评论 (仅管理员)
- **请求参数**:
  ```json
  {
    "id": 1,
    "uid": 12345,
    "permission": 0
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "id": 1
  }
  ```

### 11. 删除作品评论
- **接口路径**: `POST /api/admin/comment/uphoto/delete`
- **功能说明**: 删除作品评论 (仅管理员)
- **请求参数**:
  ```json
  {
    "id": 1,
    "uid": 12345,
    "permission": 0
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "id": 1
  }
  ```

### 12. 获取公告列表 (管理员)
- **接口路径**: `GET /api/admin/announcement/list`
- **功能说明**: 获取公告列表 (仅管理员)
- **请求参数**:
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
- **响应格式**: 同主站公告列表

### 13. 创建公告
- **接口路径**: `POST /api/admin/announcement/create`
- **功能说明**: 创建新公告 (仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0,
    "title": "公告标题",
    "content": "公告内容",
    "status": 1
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "id": 1
  }
  ```

### 14. 更新公告
- **接口路径**: `POST /api/admin/announcement/update`
- **功能说明**: 更新公告 (仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0,
    "id": 1,
    "title": "新标题",
    "content": "新内容",
    "status": 1
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

### 15. 删除公告
- **接口路径**: `POST /api/admin/announcement/delete`
- **功能说明**: 删除公告 (仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0,
    "id": 1
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true
  }
  ```

### 16. 扫描未使用图片
- **接口路径**: `GET /api/admin/image/unused`
- **功能说明**: 扫描 picture 文件夹中未被数据库引用的图片 (仅管理员)
- **请求参数**:
  - `uid`: 管理员用户ID
  - `permission`: 必须为 0
- **响应格式**:
  ```json
  {
    "success": true,
    "data": [
      "picture/uphoto/unused1.png",
      "picture/avatar/unused2.png"
    ],
    "count": 2
  }
  ```

### 17. 删除指定未使用图片
- **接口路径**: `POST /api/admin/image/cleanup`
- **功能说明**: 删除指定的未使用图片 (仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0,
    "images": [
      "picture/uphoto/unused1.png"
    ]
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "deletedCount": 1
  }
  ```

### 18. 清理所有未使用图片
- **接口路径**: `POST /api/admin/image/cleanup-all`
- **功能说明**: 删除所有未使用的图片 (仅管理员)
- **请求参数**:
  ```json
  {
    "uid": 12345,
    "permission": 0
  }
  ```
- **响应格式**:
  ```json
  {
    "success": true,
    "deletedCount": 5,
    "message": "已删除 5 张未使用的图片"
  }
  ```

---

## 通用测试工具

### 使用 Postman 测试
1. 新建 Request
2. 设置请求方法 (GET/POST/PUT/DELETE)
3. 输入 URL (如 http://localhost:8080/api/user/login)
4. POST/PUT 请求需设置 Headers: `Content-Type: application/json`
5. 在 Body 中选择 raw -&gt; JSON, 填写请求参数
6. 点击 Send 发送请求

### 使用 curl 测试
示例:
```bash
# GET 请求
curl "http://localhost:8080/api/main/activity/search?uid=10001"

# POST 请求
curl -X POST http://localhost:8080/api/user/login \
  -H "Content-Type: application/json" \
  -d '{"loginname":"test","password":"123456"}'
```

## 注意事项
1. 所有需要权限验证的接口都需要在 localStorage 中保存用户登录信息:
   - `pwps_uid`: 用户ID
   - `pwps_permission`: 权限等级
   - `pwps_loginname`: 登录名
   - `pwps_avatar`: 头像路径
2. 管理员接口需要 `permission=0` 才能访问
3. 日期格式统一使用 `yyyy-MM-dd`
4. 图片路径返回时需要在前面加上 `/` 才能正确访问
5. 所有接口返回的 JSON 都包含 `success` 字段表示操作是否成功
