package com.example.pwps.mapper;

import com.example.pwps.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ActivityMapper {

    int insert(Activity activity);

    int update(Activity activity);

    int deleteById(@Param("activityId") Long activityId);

    Activity findById(@Param("activityId") Long activityId);

    List<Activity> findByUid(@Param("uid") Long uid);

    List<Activity> findReviewPending();

    List<Activity> searchMain(@Param("category") Integer category,
                              @Param("startDate") Date startDate,
                              @Param("endDate") Date endDate,
                              @Param("sort") String sort);

    List<Activity> findAll();

    int updateLikeAndComment(@Param("activityId") Long activityId,
                             @Param("likeDelta") int likeDelta,
                             @Param("commentDelta") int commentDelta);

    int updateReviewStatus(@Param("activityId") Long activityId,
                           @Param("reviewStatus") int reviewStatus,
                           @Param("activityStatus") int activityStatus);

    int updatePictureIdAndCover(@Param("activityId") Long activityId,
                                @Param("activityPictureId") Long activityPictureId,
                                @Param("activityCoverImage") String activityCoverImage);
}

