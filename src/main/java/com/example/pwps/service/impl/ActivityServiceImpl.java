package com.example.pwps.service.impl;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.dto.ActivityDtos.ActivityCreateRequest;
import com.example.pwps.dto.ActivityDtos.ActivityCreateResponse;
import com.example.pwps.entity.Activity;
import com.example.pwps.entity.ActivityDetail;
import com.example.pwps.entity.User;
import com.example.pwps.mapper.ActivityMapper;
import com.example.pwps.mapper.ActivityDetailMapper;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.ActivityService;
import com.example.pwps.service.PointService;
import com.example.pwps.util.PictureStorageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;
    private final ActivityDetailMapper activityDetailMapper;
    private final UserMapper userMapper;
    private final PointService pointService;

    public ActivityServiceImpl(ActivityMapper activityMapper,
                               ActivityDetailMapper activityDetailMapper,
                               UserMapper userMapper,
                               PointService pointService) {
        this.activityMapper = activityMapper;
        this.activityDetailMapper = activityDetailMapper;
        this.userMapper = userMapper;
        this.pointService = pointService;
    }

    @Override
    @Transactional
    public ActivityCreateResponse create(ActivityCreateRequest request) {
        ActivityCreateResponse resp = new ActivityCreateResponse();
        if (request == null || request.uid == null || request.permission == null) {
            resp.success = false;
            return resp;
        }

        User creator = userMapper.findByUid(request.uid);
        if (creator == null) {
            resp.success = false;
            return resp;
        }

        // 非管理员不能创建官方活动（category=1）
        if (request.permission != 0 && request.activity_category != null && request.activity_category == 1) {
            resp.success = false;
            resp.point_remain = pointService.getPoint(request.uid);
            return resp;
        }

        // 积分校验：创建活动 -100
        int beforePoint = pointService.getPoint(request.uid);
        if (beforePoint - 100 < 0) {
            resp.success = false;
            resp.point_remain = beforePoint - 100;
            return resp;
        }
        if (!pointService.spend(request.uid, 100)) {
            resp.success = false;
            resp.point_remain = beforePoint - 100;
            return resp;
        }

        Activity activity = new Activity();
        activity.setActivityName(request.activity_name);
        activity.setActivityDescription(request.activity_description);
        activity.setActivityCategory(request.activity_category);
        activity.setActivityStatus(request.activity_status);
        activity.setActivityLocation(request.activity_location);
        if (request.activity_time != null) {
            activity.setActivityTimeEarly(request.activity_time);
            activity.setActivityTimeEnd(request.activity_time);
        } else {
            Date now = new Date();
            activity.setActivityTimeEarly(now);
            activity.setActivityTimeEnd(now);
        }
        activity.setUid(request.uid);
        activity.setUsername(StringUtils.hasText(creator.getUsername()) ? creator.getUsername() : ("用户" + creator.getUid()));
        activity.setPermission(request.permission);
        activity.setActivityPictureId(0L);
        activity.setActivityCoverImage(null);
        activity.setActivityLikeNumber(0);
        activity.setActivityCommentNumber(0);
        activity.setActivityReviewStatus(1);

        activityMapper.insert(activity);

        // 生成 picture_id：从 10000 起，简单用 10000 + activity_id 偏移
        long pictureId = 10000L + activity.getActivityId();
        String coverPath = null;
        if (StringUtils.hasText(request.activity_cover_image)) {
            String fileName = "activity_" + activity.getActivityId() + ".png";
            coverPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                    PictureStorageUtil.Category.ACTIVITY,
                    request.activity_cover_image,
                    fileName
            );
        }
        activityMapper.updatePictureIdAndCover(activity.getActivityId(), pictureId, coverPath);

        resp.activity_id = activity.getActivityId();
        resp.created_at = new Date();
        resp.activity_picture_id = pictureId;
        resp.point_remain = pointService.getPoint(request.uid);
        resp.success = true;
        return resp;
    }

    @Override
    @Transactional
    public ActivityDtos.ActivityEditResponse edit(ActivityDtos.ActivityEditRequest request) {
        ActivityDtos.ActivityEditResponse resp = new ActivityDtos.ActivityEditResponse();
        if (request == null || request.activity_id == null || request.uid == null || request.permission == null) {
            resp.success = false;
            return resp;
        }
        Activity activity = activityMapper.findById(request.activity_id);
        if (activity == null) {
            resp.success = false;
            return resp;
        }

        // 活动创建者或管理员才允许编辑
        if (!request.uid.equals(activity.getUid()) && request.permission != 0) {
            resp.success = false;
            return resp;
        }

        // 积分校验：修改活动 -5
        int beforePoint = pointService.getPoint(request.uid);
        if (beforePoint -5 <0) {
            resp.success = false;
            resp.point_remain = beforePoint -5;
            return resp;
        }
        if (!pointService.spend(request.uid,5)) {
            resp.success = false;
            resp.point_remain = beforePoint -5;
            return resp;
        }

        activity.setActivityName(request.activity_name);
        activity.setActivityDescription(request.activity_description);
        // 管理员可编辑公告分类
        if (request.activity_category != null) {
            activity.setActivityCategory(request.activity_category);
        }
        // 管理员可编辑活动地点
        if (request.permission != null && request.permission == 0 && StringUtils.hasText(request.activity_location)) {
            activity.setActivityLocation(request.activity_location);
        }
        if (request.activity_time != null) {
            activity.setActivityTimeEarly(request.activity_time);
            activity.setActivityTimeEnd(request.activity_time);
        }
        // 管理员可编辑权限设置
        if (request.permission != null) {
            activity.setPermission(request.permission);
        }
        // 允许更新封面
        if (StringUtils.hasText(request.activity_cover_image)) {
            String fileName = "activity_" + activity.getActivityId() + ".png";
            String coverPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                    PictureStorageUtil.Category.ACTIVITY,
                    request.activity_cover_image,
                    fileName
            );
            activity.setActivityCoverImage(coverPath);
        }
        // 编辑后回到未审核
        activity.setActivityReviewStatus(1);
        activityMapper.update(activity);

        resp.activity_id = activity.getActivityId();
         resp.created_at = new Date();
        resp.point_remain = pointService.getPoint(request.uid);
        resp.success = true;
        return resp;
    }

    @Override
    @Transactional
    public ActivityDtos.ActivityDeleteResponse delete(ActivityDtos.ActivityDeleteRequest request) {
        ActivityDtos.ActivityDeleteResponse resp = new ActivityDtos.ActivityDeleteResponse();
        if (request == null || request.activity_id == null || request.uid == null) {
            resp.success = false;
            return resp;
        }
        Activity activity = activityMapper.findById(request.activity_id);
        if (activity == null) {
            resp.success = false;
            return resp;
        }

        // 删除权限：category=1/2 仅管理员；category=3 管理员或创建者
        int category = activity.getActivityCategory() == null ? -1 : activity.getActivityCategory();
        if (category == 1 || category == 2) {
            if (request.permission == null || request.permission != 0) {
                resp.success = false;
                return resp;
            }
        } else if (category == 3) {
            if ((request.permission == null || request.permission != 0) && !request.uid.equals(activity.getUid())) {
                resp.success = false;
                return resp;
            }
        }

        // 积分校验：删除活动 -300
        int beforePoint = pointService.getPoint(request.uid);
        if (beforePoint - 300 < 0) {
            resp.success = false;
            resp.point_remain = beforePoint - 300;
            return resp;
        }
        if (!pointService.spend(request.uid, 300)) {
            resp.success = false;
            resp.point_remain = beforePoint - 300;
            return resp;
        }

        activityMapper.deleteById(request.activity_id);
        resp.uid = request.uid;
        resp.deleted_at = new Date();
        resp.point_remain = pointService.getPoint(request.uid);
        resp.success = true;
        return resp;
    }

    @Override
    public ActivityDtos.ActivityQueryResponse query(Long uid, Long askUid) {
        ActivityDtos.ActivityQueryResponse resp = new ActivityDtos.ActivityQueryResponse();
        if (askUid == null) {
            resp.success = false;
            return resp;
        }
        List<Activity> list = activityMapper.findByUid(askUid);
        boolean self = uid != null && uid.equals(askUid);
        resp.data = list.stream()
                .filter(a -> self || (a.getActivityReviewStatus() != null && a.getActivityReviewStatus() == 2))
                .map(a -> {
                    ActivityDtos.ActivityQueryResponseItem item = new ActivityDtos.ActivityQueryResponseItem();
                    item.activity_id = a.getActivityId();
                    item.activity_name = a.getActivityName();
                    item.activity_cover_image = a.getActivityCoverImage();
                    item.activity_cover_image_URL = a.getActivityCoverImage();
                    item.activity_description = a.getActivityDescription();
                    item.activity_category = a.getActivityCategory();
                    item.activity_location = a.getActivityLocation();
                    item.activity_time_early = a.getActivityTimeEarly();
                    item.activity_time_end = a.getActivityTimeEnd();
                    item.activity_status = a.getActivityStatus();
                    item.activity_review_status = a.getActivityReviewStatus();
                    return item;
                })
                .collect(Collectors.toList());
        resp.success = true;
        return resp;
    }

    @Override
    public ActivityDtos.AdminActivityReviewListResponse adminReviewList(Long uid, Integer permission) {
        ActivityDtos.AdminActivityReviewListResponse resp = new ActivityDtos.AdminActivityReviewListResponse();
        if (uid == null || permission == null || permission != 0) {
            resp.success = false;
            return resp;
        }
        List<Activity> list = activityMapper.findReviewPending();
        resp.data = list.stream().map(a -> {
            ActivityDtos.AdminActivityReviewListItem item = new ActivityDtos.AdminActivityReviewListItem();
            item.activity_id = a.getActivityId();
            item.activity_name = a.getActivityName();
            item.activity_description = a.getActivityDescription();
            item.activity_category = a.getActivityCategory();
            item.activity_status = a.getActivityStatus();
            item.activity_cover_image = a.getActivityCoverImage();
            item.activity_cover_image_URL = a.getActivityCoverImage();
            return item;
        }).collect(Collectors.toList());
        resp.success = true;
        return resp;
    }

    @Override
    @Transactional
    public ActivityDtos.AdminActivityReviewDecisionResponse adminReviewDecision(Long activityId, Long uid, Integer permission, Integer reviewStatus) {
        ActivityDtos.AdminActivityReviewDecisionResponse resp = new ActivityDtos.AdminActivityReviewDecisionResponse();
        resp.activity_id = activityId;
        if (activityId == null || uid == null || permission == null || permission != 0 || reviewStatus == null) {
            resp.success = false;
            return resp;
        }
        Activity activity = activityMapper.findById(activityId);
        if (activity == null) {
            resp.success = false;
            return resp;
        }
        int status = activity.getActivityStatus() == null ? 1 : activity.getActivityStatus();
        if (reviewStatus == 0) {
            status = 0; // 文档：审核未通过同步令 activity_status=0
        }
        activityMapper.updateReviewStatus(activityId, reviewStatus, status);
        resp.success = true;
        return resp;
    }



    @Override
    @Transactional
    public ActivityDtos.ActivityLikeResponse like(ActivityDtos.ActivityLikeRequest request) {
        ActivityDtos.ActivityLikeResponse resp = new ActivityDtos.ActivityLikeResponse();
        if (request == null || request.uid == null || request.activity_id == null || request.like_change == null) {
            resp.success = false;
            return resp;
        }
        Activity activity = activityMapper.findById(request.activity_id);
        if (activity == null || activity.getActivityReviewStatus() == null || activity.getActivityReviewStatus() != 2) {
            resp.success = false;
            return resp;
        }
        // 作者不能点赞自己的活动
        if (request.uid.equals(activity.getUid())) {
            resp.success = false;
            return resp;
        }

        ActivityDetail detail = activityDetailMapper.findByActivityIdAndUid(request.activity_id, request.uid);
        int before = detail == null || detail.getActivityLike() == null ? 0 : detail.getActivityLike();
        int after = request.like_change == 1 ? 1 : 0;
        // 防止点赞数 <0：若当前未点赞且请求取消，直接失败
        if (before == 0 && after == 0) {
            resp.success = false;
            return resp;
        }
        int delta = after - before;
        if (delta == 0) {
            resp.success = true;
            resp.activity_id = request.activity_id;
            resp.uid = request.uid;
            return resp;
        }
        // 防止数据库点赞数 <0：若点赞数为0且要减，禁止
        int likeNum = activity.getActivityLikeNumber() == null ? 0 : activity.getActivityLikeNumber();
        if (delta < 0 && likeNum <= 0) {
            resp.success = false;
            return resp;
        }

        if (detail == null) {
            detail = new ActivityDetail();
            detail.setActivityId(request.activity_id);
            detail.setUid(request.uid);
            detail.setActivityLike(after);
            detail.setActivityComment(0);
            detail.setActivityCommentDetail(null);
            activityDetailMapper.insert(detail);
        } else {
            detail.setActivityLike(after);
            activityDetailMapper.update(detail);
        }
        activityMapper.updateLikeAndComment(request.activity_id, delta, 0);

        // 积分：活动获得点赞 +1；取消点赞 -1（不低于0）
        pointService.addOrClamp(activity.getUid(), delta);

        // 若点赞和评论都为0，则删除该行
        if ((detail.getActivityLike() == null || detail.getActivityLike() == 0)
                && (detail.getActivityComment() == null || detail.getActivityComment() == 0)) {
            activityDetailMapper.deleteById(detail.getId());
        }

        resp.activity_id = request.activity_id;
        resp.uid = request.uid;
        resp.success = true;
        return resp;
    }

    @Override
    @Transactional
    public ActivityDtos.ActivityCommentResponse comment(ActivityDtos.ActivityCommentRequest request) {
        ActivityDtos.ActivityCommentResponse resp = new ActivityDtos.ActivityCommentResponse();
        if (request == null || request.uid == null || request.activity_id == null || !StringUtils.hasText(request.activity_comment_detail)) {
            resp.success = false;
            return resp;
        }
        Activity activity = activityMapper.findById(request.activity_id);
        if (activity == null || activity.getActivityReviewStatus() == null || activity.getActivityReviewStatus() != 2) {
            resp.success = false;
            return resp;
        }
        ActivityDetail detail = activityDetailMapper.findByActivityIdAndUid(request.activity_id, request.uid);
        if (detail != null && detail.getActivityComment() != null && detail.getActivityComment() == 1) {
            // 评论不可覆盖
            resp.success = false;
            return resp;
        }
        if (detail == null) {
            detail = new ActivityDetail();
            detail.setActivityId(request.activity_id);
            detail.setUid(request.uid);
            detail.setActivityLike(0);
            detail.setActivityComment(1);
            detail.setActivityCommentDetail(request.activity_comment_detail);
            activityDetailMapper.insert(detail);
        } else {
            detail.setActivityComment(1);
            detail.setActivityCommentDetail(request.activity_comment_detail);
            activityDetailMapper.update(detail);
        }
        activityMapper.updateLikeAndComment(request.activity_id, 0, 1);

        // 积分：发表评论 +5（评论被删除 -8 由管理员评论管理接口执行）
        pointService.addOrClamp(request.uid, 5);

        resp.activity_id = request.activity_id;
        resp.uid = request.uid;
        resp.success = true;
        return resp;
    }

    @Override
    public ActivityDtos.ActivityGetResponse get(ActivityDtos.ActivityGetRequest request) {
        ActivityDtos.ActivityGetResponse resp = new ActivityDtos.ActivityGetResponse();
        if (request == null || request.activity_id == null) {
            resp.success = false;
            return resp;
        }
        Activity activity = activityMapper.findById(request.activity_id);
        if (activity == null) {
            resp.success = false;
            return resp;
        }
        if (activity.getActivityReviewStatus() == null || activity.getActivityReviewStatus() != 2) {
            boolean admin = request.permission != null && request.permission == 0;
            boolean owner = request.uid != null && request.uid.equals(activity.getUid());
            if (!admin && !owner) {
                resp.success = false;
                return resp;
            }
        }
        resp.activity_id = activity.getActivityId();
        resp.uid = activity.getUid();
        resp.activity_name = activity.getActivityName();
        resp.activity_description = activity.getActivityDescription();
        resp.activity_category = activity.getActivityCategory();
        resp.activity_location = activity.getActivityLocation();
        resp.activity_time = activity.getActivityTimeEarly();
        resp.activity_status = activity.getActivityStatus();
        resp.activity_cover_image = activity.getActivityCoverImage();
        resp.activity_like_number = activity.getActivityLikeNumber();
        resp.activity_comment_number = activity.getActivityCommentNumber();
        resp.like_count = activity.getActivityLikeNumber();
        
        try {
            User user = userMapper.findByUid(activity.getUid());
            if (user != null) {
                resp.username = user.getUsername();
                resp.avatar = user.getAvatar();
            }
        } catch (Exception e) {
        }
        
        if (request.uid != null) {
            ActivityDetail detail = activityDetailMapper.findByActivityIdAndUid(request.activity_id, request.uid);
            resp.liked = detail != null && detail.getActivityLike() != null && detail.getActivityLike() == 1;
        } else {
            resp.liked = false;
        }
        
        resp.success = true;
        return resp;
    }

    @Override
    public ActivityDtos.MainActivitySearchResponse mainSearch(Long uid, Integer category, Date startDate, Date endDate, String sort) {
        ActivityDtos.MainActivitySearchResponse resp = new ActivityDtos.MainActivitySearchResponse();
        try {
            List<Activity> list = activityMapper.searchMain(category, startDate, endDate, sort);
            // 过滤审核通过的活动
            list = list.stream()
                    .filter(a -> a.getActivityReviewStatus() != null && a.getActivityReviewStatus() == 2)
                    .collect(Collectors.toList());
            List<ActivityDtos.MainActivitySearchResponseItem> items = list.stream().map(a -> {
                ActivityDtos.MainActivitySearchResponseItem item = new ActivityDtos.MainActivitySearchResponseItem();
                item.activity_id = a.getActivityId();
                item.uid = a.getUid();
                item.username = a.getUsername();
                item.activity_name = a.getActivityName();
                item.activity_description = a.getActivityDescription();
                item.activity_status = a.getActivityStatus();
                item.activity_category = a.getActivityCategory();
                item.activity_cover_image = a.getActivityCoverImage();
                item.activity_time_early = a.getActivityTimeEarly();
                item.activity_time_end = a.getActivityTimeEnd();
                item.activity_like_number = a.getActivityLikeNumber();
                item.activity_comment_number = a.getActivityCommentNumber();
                // 投稿数暂时设为0，需要根据实际情况从数据库查询
                item.submission_count = 0;
                return item;
            }).collect(Collectors.toList());
            resp.setData(items);
            resp.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
        }
        return resp;
    }
}

