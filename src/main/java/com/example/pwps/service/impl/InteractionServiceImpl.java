package com.example.pwps.service.impl;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.dto.UserDtos;
import com.example.pwps.entity.ActivityDetail;
import com.example.pwps.entity.UphotoDetail;
import com.example.pwps.entity.User;
import com.example.pwps.mapper.ActivityDetailMapper;
import com.example.pwps.mapper.UphotoDetailMapper;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.InteractionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final UphotoDetailMapper uphotoDetailMapper;
    private final ActivityDetailMapper activityDetailMapper;
    private final UserMapper userMapper;

    public InteractionServiceImpl(UphotoDetailMapper uphotoDetailMapper,
                                   ActivityDetailMapper activityDetailMapper,
                                   UserMapper userMapper) {
        this.uphotoDetailMapper = uphotoDetailMapper;
        this.activityDetailMapper = activityDetailMapper;
        this.userMapper = userMapper;
    }

    @Override
    public UserDtos.UphotoLikeResponse likeUphoto(UserDtos.UphotoLikeRequest request) {
        UserDtos.UphotoLikeResponse response = new UserDtos.UphotoLikeResponse();
        response.setUpthoto_id(request.getUpthoto_id());
        
        try {
            UphotoDetail existing = uphotoDetailMapper.findByUphotoIdAndUid(request.getUpthoto_id(), request.getUid());
            
            if (existing == null) {
                UphotoDetail detail = new UphotoDetail();
                detail.setUpthotoId(request.getUpthoto_id());
                detail.setUid(request.getUid());
                detail.setUphotoLike(request.getLike_change());
                detail.setUphotoComment(0);
                detail.setUphotoCommentDetail(null);
                detail.setCreatedAt(new Date());
                uphotoDetailMapper.insert(detail);
                response.setLiked(request.getLike_change() == 1);
            } else {
                existing.setUphotoLike(request.getLike_change());
                uphotoDetailMapper.update(existing);
                response.setLiked(request.getLike_change() == 1);
            }
            
            int likeCount = uphotoDetailMapper.countLikesByUphotoId(request.getUpthoto_id());
            response.setLike_count(likeCount);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setLike_count(0);
            response.setLiked(false);
        }
        
        return response;
    }

    @Override
    public UserDtos.UphotoCommentResponse commentUphoto(UserDtos.UphotoCommentRequest request) {
        UserDtos.UphotoCommentResponse response = new UserDtos.UphotoCommentResponse();
        response.setUpthoto_id(request.getUpthoto_id());
        
        try {
            UphotoDetail existing = uphotoDetailMapper.findByUphotoIdAndUid(request.getUpthoto_id(), request.getUid());
            
            if (existing == null) {
                UphotoDetail detail = new UphotoDetail();
                detail.setUpthotoId(request.getUpthoto_id());
                detail.setUid(request.getUid());
                detail.setUphotoLike(0);
                detail.setUphotoComment(1);
                detail.setUphotoCommentDetail(request.getUphoto_comment_detail());
                detail.setCreatedAt(new Date());
                uphotoDetailMapper.insert(detail);
            } else {
                existing.setUphotoComment(1);
                existing.setUphotoCommentDetail(request.getUphoto_comment_detail());
                existing.setCreatedAt(new Date());
                uphotoDetailMapper.update(existing);
            }
            
            List<UphotoDetail> comments = uphotoDetailMapper.findCommentsByUphotoId(request.getUpthoto_id());
            response.setComment_count(comments.size());
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setComment_count(0);
        }
        
        return response;
    }

    @Override
    public UserDtos.UphotoCommentsResponse getUphotoComments(Long upthotoId) {
        UserDtos.UphotoCommentsResponse response = new UserDtos.UphotoCommentsResponse();
        
        try {
            List<UphotoDetail> details = uphotoDetailMapper.findCommentsByUphotoId(upthotoId);
            List<UserDtos.UphotoCommentItem> comments = new ArrayList<>();
            
            for (UphotoDetail detail : details) {
                UserDtos.UphotoCommentItem item = new UserDtos.UphotoCommentItem();
                item.setId(detail.getId());
                item.setUid(detail.getUid());
                item.setUphoto_comment_detail(detail.getUphotoCommentDetail());
                item.setCreated_at(detail.getCreatedAt());
                
                User user = userMapper.findByUid(detail.getUid());
                if (user != null) {
                    item.setUsername(user.getUsername());
                    item.setAvatar(user.getAvatar());
                } else {
                    item.setUsername("用户" + detail.getUid());
                    item.setAvatar(null);
                }
                
                comments.add(item);
            }
            
            response.setComments(comments);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setComments(new ArrayList<>());
            response.setSuccess(false);
        }
        
        return response;
    }

    @Override
    public ActivityDtos.ActivityLikeResponse likeActivity(ActivityDtos.ActivityLikeRequest request) {
        ActivityDtos.ActivityLikeResponse response = new ActivityDtos.ActivityLikeResponse();
        response.activity_id = request.activity_id;
        response.uid = request.uid;
        
        try {
            ActivityDetail existing = activityDetailMapper.findByActivityIdAndUid(request.activity_id, request.uid);
            
            if (existing == null) {
                ActivityDetail detail = new ActivityDetail();
                detail.setActivityId(request.activity_id);
                detail.setUid(request.uid);
                detail.setActivityLike(request.like_change);
                detail.setActivityComment(0);
                detail.setActivityCommentDetail(null);
                detail.setCreatedAt(new Date());
                activityDetailMapper.insert(detail);
                response.success = true;
            } else {
                existing.setActivityLike(request.like_change);
                activityDetailMapper.update(existing);
                response.success = true;
            }
        } catch (Exception e) {
            response.success = false;
        }
        
        return response;
    }

    @Override
    public ActivityDtos.ActivityCommentResponse commentActivity(ActivityDtos.ActivityCommentRequest request) {
        ActivityDtos.ActivityCommentResponse response = new ActivityDtos.ActivityCommentResponse();
        response.activity_id = request.activity_id;
        response.uid = request.uid;
        
        try {
            ActivityDetail existing = activityDetailMapper.findByActivityIdAndUid(request.activity_id, request.uid);
            
            if (existing == null) {
                ActivityDetail detail = new ActivityDetail();
                detail.setActivityId(request.activity_id);
                detail.setUid(request.uid);
                detail.setActivityLike(0);
                detail.setActivityComment(1);
                detail.setActivityCommentDetail(request.activity_comment_detail);
                detail.setCreatedAt(new Date());
                activityDetailMapper.insert(detail);
            } else {
                existing.setActivityComment(1);
                existing.setActivityCommentDetail(request.activity_comment_detail);
                existing.setCreatedAt(new Date());
                activityDetailMapper.update(existing);
            }
            
            response.success = true;
        } catch (Exception e) {
            response.success = false;
        }
        
        return response;
    }

    @Override
    public ActivityDtos.ActivityCommentsResponse getActivityComments(Long activityId) {
        ActivityDtos.ActivityCommentsResponse response = new ActivityDtos.ActivityCommentsResponse();
        
        try {
            List<ActivityDetail> details = activityDetailMapper.findCommentsByActivityId(activityId);
            List<ActivityDtos.ActivityCommentItem> comments = new ArrayList<>();
            
            for (ActivityDetail detail : details) {
                ActivityDtos.ActivityCommentItem item = new ActivityDtos.ActivityCommentItem();
                item.setId(detail.getId());
                item.setUid(detail.getUid());
                item.setActivity_comment_detail(detail.getActivityCommentDetail());
                item.setCreated_at(detail.getCreatedAt());
                
                User user = userMapper.findByUid(detail.getUid());
                if (user != null) {
                    item.setUsername(user.getUsername());
                    item.setAvatar(user.getAvatar());
                } else {
                    item.setUsername("用户" + detail.getUid());
                    item.setAvatar(null);
                }
                
                comments.add(item);
            }
            
            response.setComments(comments);
            response.setSuccess(true);
        } catch (Exception e) {
            response.setComments(new ArrayList<>());
            response.setSuccess(false);
        }
        
        return response;
    }
}
