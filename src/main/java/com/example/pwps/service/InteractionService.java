package com.example.pwps.service;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.dto.UserDtos;

public interface InteractionService {
    UserDtos.UphotoLikeResponse likeUphoto(UserDtos.UphotoLikeRequest request);
    UserDtos.UphotoCommentResponse commentUphoto(UserDtos.UphotoCommentRequest request);
    UserDtos.UphotoCommentsResponse getUphotoComments(Long upthotoId);
    
    ActivityDtos.ActivityLikeResponse likeActivity(ActivityDtos.ActivityLikeRequest request);
    ActivityDtos.ActivityCommentResponse commentActivity(ActivityDtos.ActivityCommentRequest request);
    ActivityDtos.ActivityCommentsResponse getActivityComments(Long activityId);
}
