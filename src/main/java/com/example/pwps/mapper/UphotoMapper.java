package com.example.pwps.mapper;

import com.example.pwps.entity.Uphoto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UphotoMapper {
    Uphoto findById(@Param("upthoto_id") Long upthotoId);
    List<Uphoto> findByUid(@Param("uid") Long uid);
    List<Uphoto> findByActivityId(@Param("activity_id") String activityId);
    List<Uphoto> findReviewPending();
    List<Uphoto> findPublicPhotos();
    List<Uphoto> findPublicPhotosByLabel(@Param("label_index") Integer labelIndex, @Param("label_value") Integer labelValue);
    List<Uphoto> findAll();
    int insert(Uphoto uphoto);
    int update(Uphoto uphoto);
    int updateReviewStatus(@Param("upthoto_id") Long upthotoId, @Param("uphoto_review_status") Integer reviewStatus);
    int deleteById(@Param("upthoto_id") Long upthotoId);
    int countByUid(@Param("uid") Long uid);
}
