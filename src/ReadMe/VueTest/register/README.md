## 注册页（user-register）

- **页面 URL**：`/user/login/register`
- **静态页面文件**：`src/main/resources/static/pages/user-register.html`
- **关联接口**：`POST /api/user/register`

### 测试步骤

1. 浏览器访问 `/user/login/register`。
2. 填写账号、密码、重复密码。
3. 选择一张图片作为头像（页面会自动转为 Base64）。
4. 点击“注册”。

### 预期结果

- 注册成功后弹窗提示 `user_id`，并跳转回 `/user/login`。
- 后端会将头像保存到：`src/main/picture/avatar/uid_tmp_*.png`
- 数据库 `user.avatar` 保存为相对路径：`picture/avatar/uid_tmp_*.png`

