## 个人主页编辑页（user-edit）

- **页面 URL**：`/main/user/edit/{uid}`
- **静态页面文件**：`src/main/resources/static/pages/user-edit.html`
- **关联接口**：
  - `PUT /api/user/update`
  - `DELETE /api/user/delete`

### 测试步骤（信息修改）

1. 登录后进入 `/main/user/edit/{uid}`。
2. 在“个人基本信息管理”中修改昵称/邮箱/简介等。
3. 选择一张新头像图片（自动转 Base64）。
4. 点击“保存修改”。

### 预期结果（信息修改）

- 返回提示“保存成功”。
- 头像保存到：`src/main/picture/avatar/uid_{uid}.png`
- 数据库 `user.avatar` 更新为：`picture/avatar/uid_{uid}.png`
- 浏览器可直接访问：`/picture/avatar/uid_{uid}.png`

### 测试步骤（注销）

1. 切换到“注销账户”，点击“确认注销”。

### 预期结果（注销）

- 普通用户注销成功后跳转 `/user/login`。
- 若 permission=0（管理员），应提示注销失败且账号不被删除。

