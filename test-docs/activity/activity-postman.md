## 活动模块接口 Postman 测试说明

### 1. 公共说明

- **服务地址**：`http://localhost:8080`
- **请求头**：`Content-Type: application/json`
- **注意**：活动表建议先执行 `src/ReadMe/Datebase/upgrade_activity_cover_image.sql` 增加 `activity_cover_image` 字段。

---

### 2. 创建活动

- **URL**：`/api/user/activity/create`
- **Method**：`POST`

```json
{
  "activity_name": "测试活动A",
  "activity_description": "活动描述",
  "activity_category": 2,
  "activity_location": "北京",
  "activity_time": "2026-03-17",
  "activity_status": 1,
  "activity_cover_image": "data:image/png;base64,这里填Base64",
  "uid": 10001,
  "permission": 1,
  "pointset": -100
}
```

---

### 3. 编辑活动

- **URL**：`/api/user/activity/edit`
- **Method**：`POST`

```json
{
  "activity_id": 10001,
  "activity_name": "测试活动A(已修改)",
  "activity_description": "更新描述",
  "activity_category": 2,
  "activity_time": "2026-03-18",
  "activity_status": 1,
  "activity_cover_image": "data:image/png;base64,这里填Base64(可选)",
  "uid": 10001,
  "permission": 1,
  "pointset": -5
}
```

---

### 4. 删除活动

- **URL**：`/api/user/activity/delete`
- **Method**：`DELETE`

```json
{
  "activity_id": 10001,
  "uid": 10001,
  "permission": 0,
  "pointset": -300
}
```

---

### 5. 个人活动查询

- **URL**：`/api/user/activity/query?uid=10001&ask_uid=10001`
- **Method**：`GET`

---

### 6. 常规查询活动

- **URL**：`/api/main/activity/search`
- **Method**：`GET`
- 示例：`/api/main/activity/search?uid=10001&created_at=2026-03-17%2000:00:00`

---

### 7. 活动详情

- **URL**：`/api/main/activity/{activityId}/get`
- **Method**：`POST`

```json
{
  "uid": 10001,
  "permission": 1
}
```

---

### 8. 活动点赞

- **URL**：`/api/main/activity/{activityId}/like`
- **Method**：`POST`

```json
{
  "uid": 10002,
  "like_change": 1
}
```

---

### 9. 活动评论

- **URL**：`/api/main/activity/{activityId}/comment`
- **Method**：`POST`

```json
{
  "uid": 10002,
  "activity_comment_detail": "这是一条评论"
}
```

---

### 10. 管理员待审活动列表

- **URL**：`/api/admin/activity/review?uid=10000&permission=0`
- **Method**：`GET`

---

### 11. 管理员审核通否

- **URL**：`/api/admin/activity/review/?activity_id=10001&uid=10000&permission=0&activity_review_status=2`
- **Method**：`GET`

