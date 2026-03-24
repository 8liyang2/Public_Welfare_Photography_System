# PWPS 项目部署指南

## 项目简介
PWPS（公益摄影交流平台）是一个基于 Spring Boot + Vue 3 + MyBatis + MySQL 的摄影作品分享和交流平台。

## 目录
1. [环境要求](#环境要求)
2. [数据库配置](#数据库配置)
3. [后端部署](#后端部署)
4. [访问应用](#访问应用)
5. [常见问题](#常见问题)

---

## 环境要求

### 必需软件
- **JDK 17** 或更高版本
- **Maven 3.6+** (项目已包含 Maven Wrapper，可跳过)
- **MySQL 8.0+** 或 MariaDB 10.5+
- **现代浏览器** (Chrome, Firefox, Edge, Safari)

### 可选软件
- **IDE**: IntelliJ IDEA, Eclipse 或 VS Code
- **数据库管理工具**: Navicat, DBeaver, phpMyAdmin 等

---

## 数据库配置

### 1. 安装 MySQL
如果还没有安装 MySQL，请先下载并安装：
- Windows: https://dev.mysql.com/downloads/mysql/
- Linux: 使用包管理器安装，如 `sudo apt install mysql-server`
- macOS: 使用 Homebrew 安装，如 `brew install mysql`

### 2. 创建数据库
登录 MySQL：
```bash
mysql -u root -p
```

创建数据库：
```sql
CREATE DATABASE pwps CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

创建数据库用户（可选，推荐）：
```sql
CREATE USER 'pwps_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON pwps.* TO 'pwps_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. 导入数据库表结构
项目已提供完整的 SQL 脚本，位于 `src/ReadMe/Datebase/` 目录下。

按以下顺序执行 SQL 脚本：

```bash
# 方式一：使用命令行
mysql -u root -p pwps < src/ReadMe/Datebase/user.sql
mysql -u root -p pwps < src/ReadMe/Datebase/uphoto.sql
mysql -u root -p pwps < src/ReadMe/Datebase/activity.sql
mysql -u root -p pwps < src/ReadMe/Datebase/activity_detail.sql
mysql -u root -p pwps < src/ReadMe/Datebase/uphoto_detail.sql
mysql -u root -p pwps < src/ReadMe/Datebase/announcement.sql
mysql -u root -p pwps < src/ReadMe/Datebase/point_log.sql
mysql -u root -p pwps < src/ReadMe/Datebase/activity_uphoto_stat.sql
mysql -u root -p pwps < src/ReadMe/Datebase/add_activity_id_to_uphoto.sql
mysql -u root -p pwps < src/ReadMe/Datebase/add_created_at.sql
mysql -u root -p pwps < src/ReadMe/Datebase/add_created_at_to_detail_tables.sql
mysql -u root -p pwps < src/ReadMe/Datebase/upgrade_activity_cover_image.sql
```

方式二：使用数据库管理工具
1. 打开 Navicat/DBeaver 等工具
2. 连接到 pwps 数据库
3. 依次打开并执行上述 SQL 文件

### 4. 验证数据库
执行以下 SQL 检查表是否创建成功：
```sql
USE pwps;
SHOW TABLES;
```

应该看到以下表：
- user
- uphoto
- uphoto_detail
- activity
- activity_detail
- announcement
- point_log
- activity_uphoto_stat

### 5. 插入初始管理员账号（可选）
如果需要，可以创建一个管理员账号：
```sql
INSERT INTO user (loginname, password, username, permission, point) 
VALUES ('admin', MD5('admin123'), '管理员', 0, 1000);
```
注意：实际项目中请使用更安全的密码！

---

## 后端部署

### 1. 修改数据库配置
编辑 `src/main/resources/application.properties` 文件，修改数据库连接信息：

```properties
# 数据源配置
spring.datasource.url=jdbc:mysql://localhost:3306/pwps?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

请将 `your_mysql_password` 替换为您的 MySQL 密码。

### 2. 创建图片存储目录
项目会在根目录下创建 `picture` 文件夹用于存储上传的图片。

请确保程序有写入权限：
- Windows: 通常不需要额外设置
- Linux/macOS: 确保项目目录有写权限

### 3. 编译项目

#### 方式一：使用 Maven Wrapper（推荐）
项目已包含 Maven Wrapper，无需单独安装 Maven。

**Windows:**
```cmd
mvnw.cmd clean install
```

**Linux/macOS:**
```bash
chmod +x mvnw
./mvnw clean install
```

#### 方式二：使用已安装的 Maven
```bash
mvn clean install
```

首次编译会下载依赖，可能需要几分钟时间，请耐心等待。

### 4. 运行项目

#### 方式一：使用 Maven 直接运行
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/macOS
./mvnw spring-boot:run
```

#### 方式二：运行打包后的 JAR 文件
首先打包项目：
```bash
# Windows
mvnw.cmd clean package

# Linux/macOS
./mvnw clean package
```

然后运行 JAR：
```bash
java -jar target/PWPS-0.0.1-SNAPSHOT.jar
```

#### 方式三：在 IDE 中运行
1. 用 IDEA/Eclipse 打开项目
2. 找到 `src/main/java/com/example/pwps/PwpsApplication.java`
3. 右键点击，选择 "Run" 或 "Debug"


## 访问应用

### 1. 前端页面
项目启动后，在浏览器中访问以下地址：

| 页面 | 地址 | 说明 |
|------|------|------|
| 首页 | http://localhost:8080/main | 平台首页 |
| 用户登录 | http://localhost:8080/user/login | 用户登录 |
| 用户注册 | http://localhost:8080/user/register | 新用户注册 |
| 作品广场 | http://localhost:8080/main/ground/search/s0 | 浏览摄影作品 |
| 活动列表 | http://localhost:8080/main/activity/search | 浏览活动 |
| 管理员后台 | http://localhost:8080/main/admin | 管理员功能（需管理员账号） |

### 2. API 接口
API 接口文档请参考：`src/ReadMe/ReadMe_For_API.md`

基础 API 路径：`http://localhost:8080/api/`

### 3. 测试账号
如果使用了上面的 SQL 插入了管理员账号：
- 用户名: `admin`
- 密码: `admin123`

---

## 常见问题

### 1. 端口被占用
如果 8080 端口被占用，可以在 `application.properties` 中修改端口：
```properties
server.port=8081
```

### 2. 数据库连接失败
- 检查 MySQL 服务是否启动
- 确认用户名和密码正确
- 确认数据库名称正确
- 检查防火墙设置

### 3. Maven 依赖下载失败
- 检查网络连接
- 配置 Maven 镜像源（如阿里云镜像）
- 删除 `~/.m2/repository` 后重新编译

### 4. 图片无法上传或显示
- 检查 `picture` 目录是否存在且有写权限
- 确认 `application.properties` 中的 `image.upload.path` 配置正确
- 检查静态资源配置

### 5. 中文乱码
确保数据库使用 `utf8mb4` 字符集：
```sql
ALTER DATABASE pwps CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
```

### 6. 如何修改默认管理员密码
```sql
UPDATE user SET password = MD5('new_password') WHERE loginname = 'admin';
```

---

## 开发建议

### 1. 热部署
开发时可以使用 Spring Boot DevTools 实现热部署，在 `pom.xml` 中添加：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

### 2. 日志配置
如需修改日志级别，在 `application.properties` 中添加：
```properties
logging.level.com.example.pwps=DEBUG
logging.level.org.springframework.web=INFO
```

### 3. 数据备份
定期备份数据库：
```bash
mysqldump -u root -p pwps > backup_$(date +%Y%m%d).sql
```

---

## 项目结构
```
PWPS/
├── src/
│   ├── main/
│   │   ├── java/com/example/pwps/
│   │   │   ├── controller/      # 控制器
│   │   │   ├── service/         # 服务层
│   │   │   ├── mapper/          # 数据访问层
│   │   │   ├── entity/          # 实体类
│   │   │   ├── dto/             # 数据传输对象
│   │   │   ├── config/          # 配置类
│   │   │   └── util/            # 工具类
│   │   └── resources/
│   │       ├── static/pages/    # 前端页面
│   │       ├── mapper/          # MyBatis XML 映射文件
│   │       └── application.properties  # 配置文件
│   └── ReadMe/                  # 文档
├── pom.xml                       # Maven 配置
└── mvnw / mvnw.cmd              # Maven Wrapper
```

---

## 技术栈
- **后端框架**: Spring Boot 4.0.3
- **ORM框架**: MyBatis 3.0.3
- **数据库**: MySQL 8.0+
- **前端框架**: Vue 3
- **构建工具**: Maven
- **JDK版本**: Java 17

---

## 获取帮助
如遇到问题，请检查：
1. 本文档的 [常见问题](#常见问题) 部分
2. 项目日志输出
3. API 接口文档：`src/ReadMe/ReadMe_For_API.md`

祝您部署顺利！
