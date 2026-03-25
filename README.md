# Public_Welfare_Photography_System（PWPS） 基本实现方法

## 目录
1. [用户认证模块](#用户认证模块)
2. [作品管理模块](#作品管理模块)
3. [活动管理模块](#活动管理模块)
4. [互动功能模块](#互动功能模块)
5. [图片存储模块](#图片存储模块)
6. [数据库访问层](#数据库访问层)

---

## 用户认证模块

### 1. 用户注册实现

**核心逻辑** (`UserServiceImpl.java:34-92`):
```java
public UserRegisterResponse register(UserRegisterRequest request) {
    // 1. 参数校验
    if (request == null || !StringUtils.hasText(request.getLoginname())) {
        resp.setSuccess(false);
        return resp;
    }
    
    // 2. 检查用户名是否已存在
    User existing = userMapper.findByLoginname(request.getLoginname());
    resp.setLoginname_exists(existing != null ? 1 : 0);
    
    // 3. 检查两次密码是否一致
    boolean passwordSame = request.getPassword() != null 
            && request.getPassword().equals(request.getPassword1());
    resp.setPassword_exists(passwordSame ? 1 : 0);
    
    // 4. 密码加密（MD5）
    user.setPassword(Md5Utils.md5(request.getPassword()));
    
    // 5. 处理头像（Base64转文件存储）
    if (avatarStr.startsWith("data:image") || avatarStr.length() > 255) {
        String savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                PictureStorageUtil.Category.AVATAR,
                avatarStr,
                fileName
        );
        user.setAvatar(savedPath);
    }
    
    // 6. 插入数据库
    userMapper.insert(user);
}
```

**代码解释**:
- **参数校验**: 使用 `StringUtils.hasText()` 检查字符串是否为空
- **用户名查重**: 通过 `userMapper.findByLoginname()` 查询数据库
- **密码加密**: `Md5Utils.md5()` 对密码进行 MD5 哈希，防止明文存储
- **头像处理**: 检测 Base64 格式，调用工具类保存到文件系统，数据库只存相对路径
- **默认值设置**: 新用户权限设为 1（普通用户），积分为 0

### 2. 用户登录实现

**核心逻辑** (`UserServiceImpl.java:95-111`):
```java
public UserLoginResponse login(UserLoginRequest request) {
    // 1. 根据登录名查找用户
    User user = userMapper.findByLoginname(request.getLoginname());
    if (user == null) {
        resp.setSuccess(false);
        return resp;
    }
    
    // 2. 密码比对（MD5加密后比对）
    String encrypted = Md5Utils.md5(request.getPassword());
    if (!encrypted.equals(user.getPassword())) {
        resp.setSuccess(false);
        return resp;
    }
    
    // 3. 返回用户信息
    resp.setUid(user.getUid());
    resp.setPermission(user.getPermission().intValue());
    resp.setSuccess(true);
    return resp;
}
```

**代码解释**:
- **用户查询**: 使用登录名作为唯一标识查找用户
- **密码加密**: 密码将会以MD5加密格式保存至数据库
- **权限返回**: 将用户权限（0=管理员，1=普通用户，2=游客）返回给前端

### 3. MD5 加密工具

**实现类**: `Md5Utils.java`

```java
public static String md5(String input) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(messageDigest);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}
```

**代码解释**:
- 使用 Java 标准库 `MessageDigest` 实现 MD5 算法
- 将字符串转换为 UTF-8 字节数组进行哈希
- 返回 32 位十六进制字符串

---

## 作品管理模块

### 1. 作品上传实现

**核心逻辑** (`UphotoServiceImpl.java:38-123`):
```java
public UphotoUploadResponse upload(UphotoUploadRequest request) {
    // 1. 积分检查
    Integer currentPoint = userMapper.findPointByUid(request.getUid());
    if (currentPoint < Math.abs(request.getPointset())) {
        resp.setSuccess(false);
        return resp;
    }
    
    // 2. 标签数量校验（最多3个）
    int labelCount = 0;
    if (request.getUphoto_label_0() != null && request.getUphoto_label_0() == 1) labelCount++;
    // ... 检查其他9个标签
    if (labelCount > 3) {
        resp.setSuccess(false);
        return resp;
    }
    
    // 3. 图片存储（Base64转文件）
    String fileName = "uphoto_" + System.currentTimeMillis() + ".png";
    String savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
            PictureStorageUtil.Category.UPHOTO,
            request.getUphoto(),
            fileName
    );
    
    // 4. 创建作品实体
    Uphoto uphoto = new Uphoto();
    uphoto.setUid(request.getUid());
    uphoto.setUphotoPath(savedPath);
    uphoto.setUphotoReviewStatus(1); // 待审核状态
    uphoto.setCreatedAt(new Date());
    
    // 5. 保存到数据库
    uphotoMapper.insert(uphoto);
    
    // 6. 更新用户积分
    userMapper.updatePoint(request.getUid(), request.getPointset());
}
```

**代码解释**:
- **积分机制**: 上传作品可能消耗或获得积分，先检查积分是否足够
- **标签限制**: 每个作品最多选择 3 个标签
- **时间戳命名**: 使用 `System.currentTimeMillis()` 生成唯一文件名，避免冲突
- **审核流程**: 新作品默认状态为 1（待审核），需管理员审核后才能公开
- **原子操作**: 作品创建和积分更新在同一事务中执行

### 2. 作品查询实现

**核心逻辑** (`UphotoServiceImpl.java:295-364`):
```java
public UphotoGetResponse get(UphotoGetRequest request) {
    // 1. 查询作品
    Uphoto photo = uphotoMapper.findById(request.getUpthoto_id());
    if (photo == null) {
        resp.setSuccess(false);
        return resp;
    }
    
    // 2. 权限检查（非作者只能看已审核通过的作品）
    if (request.getUid() == null || !request.getUid().equals(photo.getUid())) {
        if (photo.getUphotoStatus() != 1 || photo.getUphotoReviewStatus() != 2) {
            resp.setSuccess(false);
            return resp;
        }
    }
    
    // 3. 关联查询作者信息
    User user = userMapper.findByUid(photo.getUid());
    resp.setUsername(user != null ? user.getUsername() : "未知用户");
    
    // 4. 统计点赞数和评论数
    int likeCount = uphotoDetailMapper.countLikesByUphotoId(request.getUpthoto_id());
    List<UphotoDetail> comments = uphotoDetailMapper.findCommentsByUphotoId(request.getUpthoto_id());
    resp.setUphoto_like_number(likeCount);
    resp.setUphoto_comment_number(comments.size());
    
    // 5. 检查当前用户是否已点赞
    if (request.getUid() != null && request.getUid() > 0) {
        UphotoDetail detail = uphotoDetailMapper.findByUphotoIdAndUid(
            request.getUpthoto_id(), request.getUid()
        );
        resp.setLiked(detail != null && detail.getUphotoLike() != null && detail.getUphotoLike() == 1);
    }
}
```

**代码解释**:
- **权限控制**: 双重检查 - 作品状态（status=1 公开）+ 审核状态（review_status=2 通过）
- **关联查询**: 通过 `uid` 外键关联查询用户表获取作者信息
- **聚合统计**: 使用 `countLikesByUphotoId()` 统计点赞数，避免 N+1 查询
- **状态检查**: 查询 `uphoto_detail` 表判断当前用户是否已点赞

---

## 活动管理模块

### 1. 活动创建实现

**核心逻辑** (`ActivityServiceImpl.java`):
```java
public ActivityCreateResponse create(ActivityCreateRequest request) {
    // 1. 创建活动实体
    Activity activity = new Activity();
    activity.setUid(request.getUid());
    activity.setActivityName(request.getActivity_name());
    activity.setActivityDescription(request.getActivity_description());
    activity.setActivityCategory(request.getActivity_category());
    activity.setActivityReviewStatus(1); // 待审核
    
    // 2. 处理封面图片
    if (StringUtils.hasText(request.getActivity_cover_image())) {
        String fileName = "activity_" + System.currentTimeMillis() + ".png";
        String savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                PictureStorageUtil.Category.ACTIVITY,
                request.getActivity_cover_image(),
                fileName
        );
        activity.setActivityCoverImage(savedPath);
    }
    
    // 3. 日期转换
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    activity.setActivityStartTime(sdf.parse(request.getActivity_start_time()));
    activity.setActivityEndTime(sdf.parse(request.getActivity_end_time()));
    
    // 4. 保存到数据库
    activityMapper.insert(activity);
    
    return resp;
}
```

**代码解释**:
- **分类管理**: 活动分为三类 - 1=官方活动，2=个人活动，3=相册集
- **日期处理**: 使用 `SimpleDateFormat` 将字符串转换为 `Date` 对象
- **审核流程**: 同作品，新活动需管理员审核通过

---

## 互动功能模块

### 1. 点赞功能实现

**核心逻辑** (`InteractionServiceImpl.java`):
```java
public UphotoLikeResponse likeUphoto(UphotoLikeRequest request) {
    // 1. 查询是否已点赞
    UphotoDetail existing = uphotoDetailMapper.findByUphotoIdAndUid(
        request.getUpthoto_id(), request.getUid()
    );
    
    if (existing != null) {
        // 2. 更新点赞状态
        existing.setUphotoLike(request.getLike_change() > 0 ? 1 : 0);
        uphotoDetailMapper.update(existing);
    } else {
        // 3. 创建新的点赞记录
        UphotoDetail detail = new UphotoDetail();
        detail.setUpthotoId(request.getUpthoto_id());
        detail.setUid(request.getUid());
        detail.setUphotoLike(1);
        uphotoDetailMapper.insert(detail);
    }
    
    // 4. 更新作品点赞数（可选，或通过统计查询）
    return resp;
}
```

**代码解释**:
- **幂等设计**: 先查询是否已存在记录，存在则更新，不存在则插入
- **状态切换**: `like_change` 为 1 表示点赞，-1 表示取消点赞
- **关联表设计**: 点赞和评论共用 `uphoto_detail` 表，通过字段区分

### 2. 评论功能实现

**核心逻辑** (`InteractionServiceImpl.java`):
```java
public UphotoCommentResponse commentUphoto(UphotoCommentRequest request) {
    // 1. 创建评论记录
    UphotoDetail detail = new UphotoDetail();
    detail.setUpthotoId(request.getUpthoto_id());
    detail.setUid(request.getUid());
    detail.setUphotoCommentDetail(request.getUphoto_comment_detail());
    detail.setCreatedAt(new Date());
    
    // 2. 保存到数据库
    uphotoDetailMapper.insert(detail);
    
    // 3. 返回最新评论数
    int commentCount = uphotoDetailMapper.countCommentsByUphotoId(request.getUpthoto_id());
    resp.setComment_count(commentCount);
    
    return resp;
}
```

**代码解释**:
- **时间戳记录**: 评论创建时自动记录当前时间
- **实时统计**: 评论后立即查询最新评论数返回给前端

---

## 图片存储模块

### 1. Base64 图片存储实现

**核心工具类** (`PictureStorageUtil.java`):
```java
public static String saveBase64ToProjectPictureDir(Category category, String base64Data, String fileName) {
    // 1. 获取项目根目录
    String projectRoot = System.getProperty("user.dir");
    
    // 2. 构建存储路径
    String categoryDir;
    switch (category) {
        case AVATAR: categoryDir = "picture/avatar"; break;
        case UPHOTO: categoryDir = "picture/uphoto"; break;
        case ACTIVITY: categoryDir = "picture/activity"; break;
        default: categoryDir = "picture";
    }
    
    // 3. 创建目录（如果不存在）
    File dir = new File(projectRoot, categoryDir);
    if (!dir.exists()) {
        dir.mkdirs();
    }
    
    // 4. 解析 Base64 数据（去掉 data:image/png;base64, 前缀）
    String base64Image = base64Data.split(",")[1];
    byte[] imageBytes = Base64.getDecoder().decode(base64Image);
    
    // 5. 写入文件
    File outputFile = new File(dir, fileName);
    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
        fos.write(imageBytes);
    }
    
    // 6. 返回相对路径（用于数据库存储）
    return categoryDir + "/" + fileName;
}
```

**代码解释**:
- **目录分类**: 图片按类型分目录存储 - avatar/uphoto/activity
- **相对路径**: 数据库只存储相对路径，便于部署和迁移
- **Base64 解析**: 使用 Java 8 `Base64` 类解码，去掉前缀后才是真实数据
- **自动创建目录**: 使用 `mkdirs()` 递归创建目录结构

### 2. 静态资源配置

**配置类** (`StaticResourceConfig.java`):
```java
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /picture/** 映射到项目根目录的 picture 文件夹
        registry.addResourceHandler("/picture/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "/picture/");
    }
}
```

**代码解释**:
- **外部资源映射**: 通过 `addResourceHandler` 将文件系统路径映射为 URL 路径
- **访问方式**: 数据库存储的 `picture/uphoto/xxx.png` 可通过 `http://localhost:8080/picture/uphoto/xxx.png` 访问

---

## 数据库访问层

### 1. MyBatis Mapper 接口设计

**示例** (`UserMapper.java`):
```java
@Mapper
public interface UserMapper {
    // 简单查询
    User findByLoginname(@Param("loginname") String loginname);
    User findByUid(@Param("uid") Long uid);
    
    // 增删改
    int insert(User user);
    int update(User user);
    int deleteByUid(@Param("uid") Long uid);
    
    // 自定义更新
    int updatePassword(@Param("uid") Long uid, @Param("password") String password);
    int updatePoint(@Param("uid") Long uid, @Param("pointChange") Integer pointChange);
    
    // 单值查询
    Integer findPointByUid(@Param("uid") Long uid);
    
    // 批量查询
    List<User> findAll();
}
```

**代码解释**:
- **@Param 注解**: 明确指定 SQL 参数名称，避免混淆
- **返回值设计**: 增删改返回 `int` 表示影响行数，查询返回实体或列表
- **方法命名**: 遵循 findByXxx / insert / update / delete 规范

### 2. MyBatis XML 映射文件

**示例** (`UserMapper.xml`):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.pwps.mapper.UserMapper">

    <!-- 结果映射 -->
    <resultMap id="BaseResultMap" type="com.example.pwps.entity.User">
        <id column="uid" property="uid"/>
        <result column="loginname" property="loginname"/>
        <result column="password" property="password"/>
        <result column="username" property="username"/>
        <result column="permission" property="permission"/>
        <result column="point" property="point"/>
    </resultMap>

    <!-- 根据登录名查询 -->
    <select id="findByLoginname" resultMap="BaseResultMap">
        SELECT * FROM user WHERE loginname = #{loginname}
    </select>

    <!-- 插入用户 -->
    <insert id="insert" useGeneratedKeys="true" keyProperty="uid">
        INSERT INTO user (loginname, password, username, permission, point)
        VALUES (#{loginname}, #{password}, #{username}, #{permission}, #{point})
    </insert>

    <!-- 积分更新（原子操作） -->
    <update id="updatePoint">
        UPDATE user SET point = point + #{pointChange} WHERE uid = #{uid}
    </update>

</mapper>
```

**代码解释**:
- **结果映射**: `resultMap` 将数据库列名映射到 Java 实体属性
- **自增主键**: `useGeneratedKeys="true"` 自动获取数据库生成的主键
- **原子更新**: `point = point + #{pointChange}` 在数据库层面完成，避免并发问题
- **工具类**: MD5、图片存储等通用功能封装为静态工具类
- **权限分级**: 三级权限体系（0=管理员，1=普通用户，2=游客）
