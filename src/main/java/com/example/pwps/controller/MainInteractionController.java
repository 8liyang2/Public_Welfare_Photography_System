package com.example.pwps.controller;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.dto.UserDtos;
import com.example.pwps.service.InteractionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
public class MainInteractionController {

    private final InteractionService interactionService;

    public MainInteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @PostMapping("/uphoto/like")
    public UserDtos.UphotoLikeResponse likeUphoto(@RequestBody UserDtos.UphotoLikeRequest request) {
        return interactionService.likeUphoto(request);
    }

    @PostMapping("/uphoto/comment")
    public UserDtos.UphotoCommentResponse commentUphoto(@RequestBody UserDtos.UphotoCommentRequest request) {
        return interactionService.commentUphoto(request);
    }

    @GetMapping("/uphoto/{upthotoId}/comments")
    public UserDtos.UphotoCommentsResponse getUphotoComments(@PathVariable Long upthotoId) {
        return interactionService.getUphotoComments(upthotoId);
    }

    @PostMapping("/activity/like")
    public ActivityDtos.ActivityLikeResponse likeActivity(@RequestBody ActivityDtos.ActivityLikeRequest request) {
        return interactionService.likeActivity(request);
    }

    @PostMapping("/activity/comment")
    public ActivityDtos.ActivityCommentResponse commentActivity(@RequestBody ActivityDtos.ActivityCommentRequest request) {
        return interactionService.commentActivity(request);
    }

    @GetMapping("/activity/{activityId}/comments")
    public ActivityDtos.ActivityCommentsResponse getActivityComments(@PathVariable Long activityId) {
        return interactionService.getActivityComments(activityId);
    }
}
