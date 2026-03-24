package com.example.pwps.service;

import com.example.pwps.dto.ActivityDtos.ActivityCommentRequest;
import com.example.pwps.dto.ActivityDtos.ActivityCommentResponse;
import com.example.pwps.dto.ActivityDtos.ActivityCreateRequest;
import com.example.pwps.dto.ActivityDtos.ActivityCreateResponse;
import com.example.pwps.dto.ActivityDtos.ActivityDeleteRequest;
import com.example.pwps.dto.ActivityDtos.ActivityDeleteResponse;
import com.example.pwps.dto.ActivityDtos.ActivityEditRequest;
import com.example.pwps.dto.ActivityDtos.ActivityEditResponse;
import com.example.pwps.dto.ActivityDtos.ActivityGetRequest;
import com.example.pwps.dto.ActivityDtos.ActivityGetResponse;
import com.example.pwps.dto.ActivityDtos.ActivityLikeRequest;
import com.example.pwps.dto.ActivityDtos.ActivityLikeResponse;
import com.example.pwps.dto.ActivityDtos.ActivityQueryResponse;
import com.example.pwps.dto.ActivityDtos.AdminActivityReviewDecisionResponse;
import com.example.pwps.dto.ActivityDtos.AdminActivityReviewListResponse;
import com.example.pwps.dto.ActivityDtos.MainActivitySearchResponse;

import java.util.Date;

public interface ActivityService {

    ActivityCreateResponse create(ActivityCreateRequest request);

    ActivityEditResponse edit(ActivityEditRequest request);

    ActivityDeleteResponse delete(ActivityDeleteRequest request);

    ActivityQueryResponse query(Long uid, Long askUid);

    AdminActivityReviewListResponse adminReviewList(Long uid, Integer permission);

    AdminActivityReviewDecisionResponse adminReviewDecision(Long activityId, Long uid, Integer permission, Integer reviewStatus);

    MainActivitySearchResponse mainSearch(Long uid, Integer category, Date startDate, Date endDate, String sort);

    ActivityLikeResponse like(ActivityLikeRequest request);

    ActivityCommentResponse comment(ActivityCommentRequest request);

    ActivityGetResponse get(ActivityGetRequest request);
}

