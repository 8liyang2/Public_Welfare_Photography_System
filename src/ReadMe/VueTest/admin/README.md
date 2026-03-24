## 管理员后台页（admin）

- **页面 URL**：`/main/admin/edit/{uid}`
- **静态页面文件**：`src/main/resources/static/pages/admin.html`
- **关联接口（待接入）**：
  - `GET /api/admin/activity/review`
  - `GET /api/admin/uphoto/review`
  - `POST /api/admin/user/ban`
  - `POST /api/admin/user/permission`
  - `POST /api/admin/comment/manage`

### 测试步骤

1. 使用管理员账号登录（permission=0）。
2. 进入 `/main/admin/edit/{uid}`。
3. 点击左侧菜单切换模块。

### 预期结果

- 非管理员进入时会被阻止并跳转回 `/main`。
- 管理员可以正常打开页面并切换模块（接口接入前为占位展示）。

