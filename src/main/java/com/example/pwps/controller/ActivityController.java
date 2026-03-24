package com.example.pwps.controller;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.dto.ActivityDtos.ActivityCreateRequest;
import com.example.pwps.dto.ActivityDtos.ActivityCreateResponse;
import com.example.pwps.service.ActivityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/create")
    public ActivityCreateResponse create(@RequestBody ActivityCreateRequest request) {
        return activityService.create(request);
    }

    @PostMapping("/edit")
    public ActivityDtos.ActivityEditResponse edit(@RequestBody ActivityDtos.ActivityEditRequest request) {
        return activityService.edit(request);
    }

    @DeleteMapping("/delete")
    public ActivityDtos.ActivityDeleteResponse delete(@RequestBody ActivityDtos.ActivityDeleteRequest request) {
        return activityService.delete(request);
    }

    @GetMapping("/query")
    public ActivityDtos.ActivityQueryResponse query(@RequestParam("uid") Long uid,
                                                   @RequestParam("ask_uid") Long askUid) {
        return activityService.query(uid, askUid);
    }
}

