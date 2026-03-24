## 主页（main）

- **页面 URL**：`/main`
- **静态页面文件**：`src/main/resources/static/pages/main.html`
- **关联接口**：后续接入 `GET /api/main/index`

### 测试步骤

1. 登录后自动跳转到 `/main`，或直接访问 `/main`。
2. 点击右上角头像区域：
   - 普通用户：跳转 `/main/user/edit/{uid}`
   - 管理员：跳转 `/main/admin/edit/{uid}`

### 预期结果

- 页面可正常打开，不出现 404。
- “快速入口”中的链接可跳转到对应页面。

