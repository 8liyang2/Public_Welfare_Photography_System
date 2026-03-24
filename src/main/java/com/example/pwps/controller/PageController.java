package com.example.pwps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    @GetMapping("/user/login")
    public String login() {
        return "forward:/pages/user-login.html";
    }

    @GetMapping("/user/login/register")
    public String register() {
        return "forward:/pages/user-register.html";
    }

    @GetMapping("/main")
    public String main() {
        return "forward:/pages/main.html";
    }

    @GetMapping("/main/user/{uid}/display")
    public String userDisplay(@PathVariable("uid") Long uid) {
        return "forward:/pages/user-display.html";
    }

    @GetMapping("/main/user/edit/{uid}")
    public String userEdit(@PathVariable("uid") Long uid) {
        return "forward:/pages/user-edit.html";
    }

    @GetMapping("/main/admin/edit/{uid}")
    public String adminEdit(@PathVariable("uid") Long uid) {
        return "forward:/pages/admin.html";
    }

    @GetMapping("/main/activity/search")
    public String activitySearch() {
        return "forward:/pages/activity-search.html";
    }

    @GetMapping("/main/activity/{activityId}")
    public String activityDetail(@PathVariable("activityId") Long activityId) {
        return "forward:/pages/activity.html";
    }

    // 广场页及其所有搜索路径统一交给同一个前端页面处理
    @GetMapping({"/main/ground/search", "/main/ground/search/**", "/main/ground/activity/{activityId}"})
    public String groundSearch() {
        return "forward:/pages/ground.html";
    }

    @GetMapping("/main/uphoto/{uphotoId}")
    public String uphotoDetail(@PathVariable("uphotoId") Long uphotoId) {
        return "forward:/pages/uphoto.html";
    }

    @GetMapping("/main/announcement")
    public String announcementList() {
        return "forward:/pages/announcement.html";
    }
}

