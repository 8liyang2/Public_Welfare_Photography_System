package com.example.pwps.controller;

import com.example.pwps.service.ImageCleanupService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/image")
public class AdminImageCleanupController {

    private final ImageCleanupService imageCleanupService;

    public AdminImageCleanupController(ImageCleanupService imageCleanupService) {
        this.imageCleanupService = imageCleanupService;
    }

    @GetMapping("/unused")
    public Map<String, Object> findUnusedImages(@RequestParam("uid") Long uid, @RequestParam("permission") Integer permission) {
        Map<String, Object> resp = new HashMap<>();
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        List<String> unusedImages = imageCleanupService.findUnusedImages();
        resp.put("success", true);
        resp.put("data", unusedImages);
        resp.put("count", unusedImages.size());
        return resp;
    }

    @PostMapping("/cleanup")
    public Map<String, Object> cleanupImages(@RequestBody Map<String, Object> request) {
        Map<String, Object> resp = new HashMap<>();
        Integer permission = (Integer) request.get("permission");
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        
        @SuppressWarnings("unchecked")
        List<String> images = (List<String>) request.get("images");
        if (images == null || images.isEmpty()) {
            resp.put("success", false);
            resp.put("message", "图片列表为空");
            return resp;
        }
        
        int deletedCount = imageCleanupService.deleteUnusedImages(images);
        resp.put("success", true);
        resp.put("deletedCount", deletedCount);
        return resp;
    }

    @PostMapping("/cleanup-all")
    public Map<String, Object> cleanupAllUnused(@RequestBody Map<String, Object> request) {
        Map<String, Object> resp = new HashMap<>();
        Integer permission = (Integer) request.get("permission");
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        
        List<String> unusedImages = imageCleanupService.findUnusedImages();
        if (unusedImages.isEmpty()) {
            resp.put("success", true);
            resp.put("deletedCount", 0);
            resp.put("message", "没有需要清理的图片");
            return resp;
        }
        
        int deletedCount = imageCleanupService.deleteUnusedImages(unusedImages);
        resp.put("success", true);
        resp.put("deletedCount", deletedCount);
        resp.put("message", "已删除 " + deletedCount + " 张未使用的图片");
        return resp;
    }
}
