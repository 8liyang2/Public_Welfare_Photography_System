## 用户注册与登录接口 Postman 测试说明

### 1. 公共说明

- **服务地址**：`http://localhost:8080`
- **请求头**：`Content-Type: application/json`

---

### 2. 用户注册接口

- **接口描述**：普通用户注册
- **URL**：`/api/user/register`
- **Method**：`POST`

#### 2.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/register`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "loginname": "testuser1",
  "password1": "123456",
  "password": "123456",
  "avatar": "data:image/png;base64,这里填写头像PNG文件的Base64字符串"
}
```

说明：

- `avatar` 支持两种写法：
  - **Base64**：`data:image/png;base64,...`（推荐测试用）——后端会自动保存到 `src/main/picture/avatar/`，数据库只保存相对路径（避免 Base64 过长导致入库失败）。
  - **相对路径字符串**：例如 `picture/avatar/uid_10001.png`（当你已经手动放置文件时可用）。

#### 2.2 返回示例

```json
{
  "user_id": 10001,
  "success": true,
  "loginname_exists": 0,
  "password_exists": 1
}
```

- `loginname_exists`：1 表示用户名已存在，0 表示未重复
- `password_exists`：1 表示两次密码一致，0 表示不一致

---

### 3. 用户登录接口

- **接口描述**：用户登录
- **URL**：`/api/user/login`
- **Method**：`POST`

#### 3.1 Postman 设置

- 选择 `POST`
- URL 填写：`http://localhost:8080/api/user/login`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON：

```json
{
  "loginname": "testuser1",
  "password": "123456"
}
```

#### 3.2 返回示例

```json
{
  "uid": 10001,
  "permission": 1,
  "success": true
}
```

- `permission`：2 为游客、1 为普通用户、0 为管理员


---

### 4. 用户信息修改接口

- **接口描述**：修改用户昵称、邮箱、头像等信息
- **URL**：`/api/user/update`
- **Method**：`PUT`

#### 4.1 Postman 设置

- 选择 `PUT`
- URL：`http://localhost:8080/api/user/update`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`
- 填入如下示例 JSON（示例为 uid=10001 的用户）：

```json
{
  "uid": 10001,
  "loginname": "testuser1",
  "username": "新昵称10001",
  "avatar": "data:image/png;base64,这里填写头像PNG文件的Base64字符串",
  "email": "user10001@example.com",
  "personal_profile": "这是个人简介",
  "permission": 1,
  "birthday": "2000-01-01"
}
```

#### 4.2 返回示例

```json
{
  "updated_at": "2026-03-15T12:00:00",
  "success": true
}
```

说明：

- 若 `avatar` 提交为 Base64，后端会自动将图片保存到 `src/main/picture/avatar/uid_10001.png`，
  并在数据库的 `avatar` 字段中记录相对路径 `picture/avatar/uid_10001.png`。


---

### 5. 用户注销接口

- **接口描述**：注销普通用户账户（管理员不允许被注销）
- **URL**：`/api/user/delete`
- **Method**：`DELETE`

#### 5.1 普通用户注销测试

- 选择 `DELETE`
- URL：`http://localhost:8080/api/user/delete`
- 「Body」选项卡选择 `raw`，右侧下拉选择 `JSON`

```json
{
  "uid": 10001,
  "permission": 1
}
```

**预期返回示例（成功注销）**：

```json
{
  "updated_at": "2026-03-15T12:05:00",
  "success": true
}
```

#### 5.2 管理员注销失败测试

```json
{
  "uid": 10000,
  "permission": 0
}
```

**预期结果**：

- `success` 为 `false`，管理员账号不会被删除。

