package com.example.pwps.controller;

import com.example.pwps.entity.Announcement;
import com.example.pwps.mapper.AnnouncementMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/main/announcement")
public class MainAnnouncementController {

    private final AnnouncementMapper announcementMapper;

    public MainAnnouncementController(AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> resp = new HashMap<>();
        List<Announcement> list = announcementMapper.findActive();
        resp.put("success", true);
        resp.put("data", list);
        return resp;
    }
}
