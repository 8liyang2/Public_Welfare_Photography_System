package com.example.pwps.controller;

import com.example.pwps.entity.Announcement;
import com.example.pwps.mapper.AnnouncementMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/announcement")
public class AdminAnnouncementController {

    private final AnnouncementMapper announcementMapper;

    public AdminAnnouncementController(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam("uid") Long uid, @RequestParam("permission") Integer permission) {
        Map<String, Object> resp = new HashMap<>();
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        List<Announcement> list = announcementMapper.findActive();
        resp.put("success", true);
        resp.put("data", list);
        return resp;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> resp = new HashMap<>();
        Integer permission = (Integer) request.get("permission");
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        String title = (String) request.get("title");
        String content = (String) request.get("content");
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            resp.put("success", false);
            resp.put("message", "标题和内容不能为空");
            return resp;
        }
        Announcement announcement = new Announcement();
        announcement.setTitle(title.trim());
        announcement.setContent(content.trim());
        announcement.setStatus(1);
        announcementMapper.insert(announcement);
        resp.put("success", true);
        resp.put("id", announcement.getId());
        return resp;
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> request) {
        Map<String, Object> resp = new HashMap<>();
        Integer permission = (Integer) request.get("permission");
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        Number idNum = (Number) request.get("id");
        if (idNum == null) {
            resp.put("success", false);
            resp.put("message", "ID不能为空");
            return resp;
        }
        Long id = idNum.longValue();
        Announcement announcement = announcementMapper.findById(id);
        if (announcement == null) {
            resp.put("success", false);
            resp.put("message", "公告不存在");
            return resp;
        }
        String title = (String) request.get("title");
        String content = (String) request.get("content");
        Number statusNum = (Number) request.get("status");
        if (title != null) announcement.setTitle(title.trim());
        if (content != null) announcement.setContent(content.trim());
        if (statusNum != null) announcement.setStatus(statusNum.intValue());
        announcementMapper.update(announcement);
        resp.put("success", true);
        return resp;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody Map<String, Object> request) {
        Map<String, Object> resp = new HashMap<>();
        Integer permission = (Integer) request.get("permission");
        if (permission == null || permission != 0) {
            resp.put("success", false);
            resp.put("message", "无权限");
            return resp;
        }
        Number idNum = (Number) request.get("id");
        if (idNum == null) {
            resp.put("success", false);
            resp.put("message", "ID不能为空");
            return resp;
        }
        announcementMapper.delete(idNum.longValue());
        resp.put("success", true);
        return resp;
    }
}
