package com.example.pwps.mapper;

import com.example.pwps.entity.ActivityDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityDetailMapper {
    void insert(ActivityDetail activityDetail);
    void update(ActivityDetail activityDetail);
    ActivityDetail findByActivityIdAndUid(@Param("activityId") Long activityId, @Param("uid") Long uid);
    List<ActivityDetail> findCommentsByActivityId(@Param("activityId") Long activityId);
    List<ActivityDetail> findAllComments(@Param("offset") Integer offset, @Param("limit") Integer limit);
    List<ActivityDetail> searchComments(@Param("keyword") String keyword, @Param("offset") Integer offset, @Param("limit") Integer limit);
    int countAllComments();
    int countSearchComments(@Param("keyword") String keyword);
    int countLikesByActivityId(@Param("activityId") Long activityId);
    void deleteById(@Param("id") Long id);
}
