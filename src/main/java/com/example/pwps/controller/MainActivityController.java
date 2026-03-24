package com.example.pwps.controller;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/main/activity")
public class MainActivityController {

    private final ActivityService activityService;

    public MainActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/search")
    public ActivityDtos.MainActivitySearchResponse search(@RequestParam("uid") Long uid,
                                                         @RequestParam(value = "category", required = false) Integer category,
                                                         @RequestParam(value = "start_date", required = false) String startDateStr,
                                                         @RequestParam(value = "end_date", required = false) String endDateStr,
                                                         @RequestParam(value = "sort", required = false, defaultValue = "latest") String sort) {
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = sdf.parse(startDateStr);
            }
        } catch (Exception e) {
        }
        try {
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = sdf.parse(endDateStr);
                endDate.setHours(23);
                endDate.setMinutes(59);
                endDate.setSeconds(59);
            }
        } catch (Exception e) {
        }
        return activityService.mainSearch(uid, category, startDate, endDate, sort);
    }

    @PostMapping("/{activityId}/like")
    public ActivityDtos.ActivityLikeResponse like(@PathVariable("activityId") Long activityId,
                                                 @RequestBody ActivityDtos.ActivityLikeRequest request) {
        request.activity_id = activityId;
        return activityService.like(request);
    }

    @PostMapping("/{activityId}/comment")
    public ActivityDtos.ActivityCommentResponse comment(@PathVariable("activityId") Long activityId,
                                                       @RequestBody ActivityDtos.ActivityCommentRequest request) {
        request.activity_id = activityId;
        return activityService.comment(request);
    }

    @PostMapping("/{activityId}/get")
    public ActivityDtos.ActivityGetResponse get(@PathVariable("activityId") Long activityId,
                                               @RequestBody ActivityDtos.ActivityGetRequest request) {
        request.activity_id = activityId;
        return activityService.get(request);
    }
}

