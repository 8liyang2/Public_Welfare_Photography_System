## 作品管理接口 Postman 测试说明

### 1. 公共说明

- **服务地址**：`http://localhost:8080`
- **请求头**：`Content-Type: application/json`

---

### 2. 作品上传接口

- **接口描述**：上传摄影作品
- **URL**：`/api/user/{uid}/uphoto/upload`
- **Method**：`POST`

#### 2.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/10001/uphoto/upload`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "uphoto": "data:image/png;base64,这里填写图片的Base64字符串",
  "uphoto_title": "测试作品",
  "uphoto_description": "这是一个测试作品",
  "activity_id": "10001",
  "uphoto_status": 1,
  "uphoto_label_0": 0,
  "uphoto_label_1": 1,
  "uphoto_label_2": 1,
  "uphoto_label_3": 0,
  "uphoto_label_4": 0,
  "uphoto_label_5": 0,
  "uphoto_label_6": 0,
  "uphoto_label_7": 0,
  "uphoto_label_8": 0,
  "uphoto_label_9": 0,
  "pointset": -10
}
```

说明：

- `uphoto` 支持 Base64 格式的图片数据
- `uphoto_status`：1 为公开，2 为仅自己可见
- `uphoto_label_0` 到 `uphoto_label_9`：1 表示选中该标签，0 表示未选中，最多只能选择 3 个标签
- `pointset`：上传作品需要消耗 10 积分，所以设置为 -10

#### 2.2 返回示例

```json
{
  "upthoto_id": 1,
  "created_at": "2026-03-18T12:00:00",
  "status": 1,
  "success": true,
  "point_remain": 90
}
```

- `status`：1 表示待审核
- `point_remain`：剩余积分

---

### 3. 作品管理接口

- **接口描述**：修改作品信息
- **URL**：`/api/user/uphoto/manage`
- **Method**：`POST`

#### 3.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/uphoto/manage`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "uphoto": "data:image/png;base64,这里填写图片的Base64字符串",
  "uphoto_title": "修改后的测试作品",
  "uphoto_description": "这是修改后的测试作品",
  "activity_id": "10001",
  "uphoto_status": 1,
  "uphoto_label_0": 0,
  "uphoto_label_1": 1,
  "uphoto_label_2": 0,
  "uphoto_label_3": 1,
  "uphoto_label_4": 0,
  "uphoto_label_5": 0,
  "uphoto_label_6": 0,
  "uphoto_label_7": 0,
  "uphoto_label_8": 0,
  "uphoto_label_9": 0,
  "upthoto_id": 1,
  "pointset": -5
}
```

说明：

- `upthoto_id`：要修改的作品 ID
- `pointset`：修改作品需要消耗 5 积分，所以设置为 -5

#### 3.2 返回示例

```json
{
  "upthoto_id": 1,
  "uid": 10001,
  "success": true,
  "point_remain": 85
}
```

---

### 4. 作品搜索接口

- **接口描述**：搜索用户的作品
- **URL**：`/api/user/uphoto/search`
- **Method**：`POST`

#### 4.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/uphoto/search`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "ask_uid": 10001
}
```

说明：

- `uid`：当前用户 ID
- `ask_uid`：要查询的用户 ID

#### 4.2 返回示例

```json
{
  "data": [
    {
      "uid": 10001,
      "uphoto": "picture/picture/uphoto_1.png",
      "uphoto_title": "测试作品"
    }
  ],
  "success": true
}
```

---

### 5. 作品删除接口

- **接口描述**：删除作品
- **URL**：`/api/user/{uid}/uphoto/delete`
- **Method**：`POST`

#### 5.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/10001/uphoto/delete`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "upthoto_id": 1,
  "pointset": -10
}
```

说明：

- `pointset`：删除作品需要消耗 10 积分，所以设置为 -10

#### 5.2 返回示例

```json
{
  "uid": 10001,
  "upthoto_id": 1,
  "success": true,
  "point_remain": 75
}
```

---

### 6. 作品详情接口

- **接口描述**：获取作品详情
- **URL**：`/api/user/{uid}/uphoto/{upthoto_id}/get`
- **Method**：`POST`

#### 6.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/10001/uphoto/1/get`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001
}
```

#### 6.2 返回示例

```json
{
  "uid": 10001,
  "uphoto": "picture/picture/uphoto_1.png",
  "uphoto_title": "测试作品",
  "uphoto_description": "这是一个测试作品",
  "activity_id": "10001",
  "uphoto_status": 1,
  "uphoto_label_0": 0,
  "uphoto_label_1": 1,
  "uphoto_label_2": 1,
  "uphoto_label_3": 0,
  "uphoto_label_4": 0,
  "uphoto_label_5": 0,
  "uphoto_label_6": 0,
  "uphoto_label_7": 0,
  "uphoto_label_8": 0,
  "uphoto_label_9": 0,
  "success": true
}
```

---

### 7. 作品泛搜索接口

- **接口描述**：在广场页面搜索作品
- **URL**：`/api/main/uphoto/search`
- **Method**：`POST`

#### 7.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/main/uphoto/search`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "main_uphoto_search_1": 1,
  "main_uphoto_search_2": 1
}
```

说明：

- `main_uphoto_search_1`：1 表示显示最近上传的作品，2 表示显示点赞最多的作品
- `main_uphoto_search_2`：标签索引，0-9 对应不同标签

#### 7.2 返回示例

```json
{
  "data": [
    {
      "uid": 10001,
      "uphoto": "picture/picture/uphoto_1.png",
      "uphoto_title": "测试作品"
    }
  ],
  "success": true
}
```

---

### 8. 作品点赞接口

- **接口描述**：点赞或取消点赞作品
- **URL**：`/api/main/uphoto/like`
- **Method**：`POST`

#### 8.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/main/uphoto/like`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "upthoto_id": 1,
  "like_change": 1
}
```

说明：

- `like_change`：1 表示点赞，0 表示取消点赞

#### 8.2 返回示例

```json
{
  "upthoto_id": 1,
  "uid": 10001,
  "success": true
}
```

---

### 9. 作品评论接口

- **接口描述**：评论作品
- **URL**：`/api/main/uphoto/comment`
- **Method**：`POST`

#### 9.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/main/uphoto/comment`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "uid": 10001,
  "upthoto_id": 1,
  "uphoto_comment_detail": "这是一条评论"
}
```

#### 9.2 返回示例

```json
{
  "upthoto_id": 1,
  "uid": 10001,
  "success": true
}
```