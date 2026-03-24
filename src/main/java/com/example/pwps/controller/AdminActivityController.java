package com.example.pwps.controller;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.service.ActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/activity")
public class AdminActivityController {

    private final ActivityService activityService;

    public AdminActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/review")
    public ActivityDtos.AdminActivityReviewListResponse reviewList(@RequestParam("uid") Long uid,
                                                                  @RequestParam("permission") Integer permission) {
        return activityService.adminReviewList(uid, permission);
    }

    @GetMapping("/review/")
    public ActivityDtos.AdminActivityReviewDecisionResponse reviewDecision(@RequestParam("activity_id") Long activityId,
                                                                          @RequestParam("uid") Long uid,
                                                                          @RequestParam("permission") Integer permission,
                                                                          @RequestParam("activity_review_status") Integer reviewStatus) {
        return activityService.adminReviewDecision(activityId, uid, permission, reviewStatus);
    }
}

