## Vue 页面与 URL 对应关系（测试用）

- **登录页面**
  - 组件文件：`test-docs/frontend/Login.vue`
  - 访问 URL：`/user/login`
  - 调用接口：`POST /api/user/login`

- **注册页面**
  - 组件文件：`test-docs/frontend/Register.vue`
  - 访问 URL：`/user/login/register`
  - 调用接口：`POST /api/user/register`

> 将上述组件拷贝到实际的 Vue 工程中后，请在路由配置中保持以上 URL，不然前后端文档会对不上。以后新增页面（主页、广场、个人主页、管理员后台等）也可以继续在本文件中追加说明。

