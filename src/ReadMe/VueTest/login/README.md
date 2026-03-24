## 登录页（user-login）

- **页面 URL**：`/user/login`
- **静态页面文件**：`src/main/resources/static/pages/user-login.html`
- **关联接口**：`POST /api/user/login`

### 测试步骤

1. 启动后端服务（Spring Boot）。
2. 浏览器访问 `/user/login`。
3. 输入已注册账号与密码，点击“登录”。

### 预期结果

- 弹窗提示登录成功。
- 浏览器跳转到 `/main`。
- LocalStorage 写入：
  - `pwps_uid`
  - `pwps_permission`
  - `pwps_loginname`

