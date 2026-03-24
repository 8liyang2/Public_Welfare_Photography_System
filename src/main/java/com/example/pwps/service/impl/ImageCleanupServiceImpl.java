package com.example.pwps.service.impl;

import com.example.pwps.mapper.ActivityMapper;
import com.example.pwps.mapper.UphotoMapper;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.ImageCleanupService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImageCleanupServiceImpl implements ImageCleanupService {

    private final UserMapper userMapper;
    private final UphotoMapper uphotoMapper;
    private final ActivityMapper activityMapper;

    @Value("${image.upload.path:picture}")
    private String imageBasePath;

    public ImageCleanupServiceImpl(UserMapper userMapper, UphotoMapper uphotoMapper, ActivityMapper activityMapper) {
        this.userMapper = userMapper;
        this.uphotoMapper = uphotoMapper;
        this.activityMapper = activityMapper;
    }

    @Override
    public List<String> findUnusedImages() {
        Set<String> dbImagePaths = new HashSet<>();
        
        List<com.example.pwps.entity.User> users = userMapper.findAll();
        for (com.example.pwps.entity.User user : users) {
            if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
                dbImagePaths.add(normalizePath(user.getAvatar()));
            }
        }
        
        List<com.example.pwps.entity.Uphoto> photos = uphotoMapper.findAll();
        for (com.example.pwps.entity.Uphoto photo : photos) {
            if (photo.getUphotoPath() != null && !photo.getUphotoPath().isEmpty()) {
                dbImagePaths.add(normalizePath(photo.getUphotoPath()));
            }
        }
        
        List<com.example.pwps.entity.Activity> activities = activityMapper.findAll();
        for (com.example.pwps.entity.Activity activity : activities) {
            if (activity.getActivityCoverImage() != null && !activity.getActivityCoverImage().isEmpty()) {
                dbImagePaths.add(normalizePath(activity.getActivityCoverImage()));
            }
        }
        
        List<String> unusedImages = new ArrayList<>();
        
        try {
            Path basePath = Paths.get(imageBasePath);
            if (!Files.exists(basePath)) {
                return unusedImages;
            }
            
            List<Path> allFiles = Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .filter(p -> isImageFile(p.toString()))
                    .collect(Collectors.toList());
            
            for (Path filePath : allFiles) {
                String relativePath = imageBasePath + "/" + basePath.relativize(filePath).toString().replace("\\", "/");
                String normalizedPath = normalizePath(relativePath);
                
                if (!dbImagePaths.contains(normalizedPath)) {
                    unusedImages.add(filePath.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return unusedImages;
    }

    @Override
    public int deleteUnusedImages(List<String> images) {
        int deletedCount = 0;
        for (String imagePath : images) {
            try {
                File file = new File(imagePath);
                if (file.exists() && file.delete()) {
                    deletedCount++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedCount;
    }

    private String normalizePath(String path) {
        if (path == null) return "";
        return path.replace("\\", "/").replaceAll("^/+", "").replaceAll("/+$", "");
    }

    private boolean isImageFile(String fileName) {
        String lowerName = fileName.toLowerCase();
        return lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") 
                || lowerName.endsWith(".png") || lowerName.endsWith(".gif")
                || lowerName.endsWith(".bmp") || lowerName.endsWith(".webp");
    }
}
