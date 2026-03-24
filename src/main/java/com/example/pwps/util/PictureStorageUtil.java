package com.example.pwps.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PictureStorageUtil {

    public enum Category {
        AVATAR("avatar"),
        ACTIVITY("activity"),
        UPHOTO("uphoto");

        private final String dirName;

        Category(String dirName) {
            this.dirName = dirName;
        }
    }

    /**
     * 将 Base64 图片保存到 src/main/picture/{category}/ 下，并返回数据库可存储的相对路径：
     * picture/{category}/{fileName}
     *
     * 支持两种输入：
     * - "data:image/png;base64,...."
     * - "...."（纯 base64）
     */
    public static String saveBase64ToProjectPictureDir(Category category, String base64OrDataUrl, String fileName) {
        if (base64OrDataUrl == null || base64OrDataUrl.isBlank()) {
            return null;
        }
        String base64 = base64OrDataUrl;
        if (base64OrDataUrl.startsWith("data:image")) {
            int comma = base64OrDataUrl.indexOf(',');
            if (comma > 0) {
                base64 = base64OrDataUrl.substring(comma + 1);
            }
        }

        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            String projectDir = System.getProperty("user.dir");
            Path dir = Paths.get(projectDir, "src", "main", "picture", category.dirName);
            Files.createDirectories(dir);
            Path filePath = dir.resolve(fileName);
            Files.write(filePath, bytes);
            return "picture/" + category.dirName + "/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("save picture failed", e);
        }
    }

    /**
     * 从项目 picture 相对路径读取图片并转 Base64（用于测试校验）。
     * 例如：picture/avatar/uid_10001.png
     */
    public static String readProjectPictureAsBase64(String relativePath) {
        if (relativePath == null || relativePath.isBlank()) {
            return null;
        }
        try {
            String projectDir = System.getProperty("user.dir");
            Path filePath = Paths.get(projectDir, "src", "main").resolve(relativePath);
            byte[] bytes = Files.readAllBytes(filePath);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException("read picture failed", e);
        }
    }
}

