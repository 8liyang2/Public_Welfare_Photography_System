## 个人主页展示页（user-display）

- **页面 URL**：`/main/user/{uid}/display`
- **静态页面文件**：`src/main/resources/static/pages/user-display.html`
- **关联接口（待接入）**：
  - `GET /api/user/activity/query`
  - `POST /api/user/uphoto/search`

### 测试步骤

1. 访问 `/main/user/{uid}/display`。

### 预期结果

- 页面可打开，不出现 404。
- 后续接入接口后，展示对应用户的活动列表与作品列表。

