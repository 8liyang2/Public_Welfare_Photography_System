package com.example.pwps.entity;

import java.util.Date;

public class Activity {

    private Long activityId;
    private Date createdAt;
    private String activityName;
    private String activityDescription;
    private Long uid;
    private String username;
    private Integer permission;
    private Integer activityCategory;
    private Integer activityStatus;
    private String activityLocation;
    private Date activityTimeEarly;
    private Date activityTimeEnd;
    private Long activityPictureId;
    private String activityCoverImage;
    private Integer activityLikeNumber;
    private Integer activityCommentNumber;
    private Integer activityReviewStatus;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(Integer activityCategory) {
        this.activityCategory = activityCategory;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public Date getActivityTimeEarly() {
        return activityTimeEarly;
    }

    public void setActivityTimeEarly(Date activityTimeEarly) {
        this.activityTimeEarly = activityTimeEarly;
    }

    public Date getActivityTimeEnd() {
        return activityTimeEnd;
    }

    public void setActivityTimeEnd(Date activityTimeEnd) {
        this.activityTimeEnd = activityTimeEnd;
    }

    public Long getActivityPictureId() {
        return activityPictureId;
    }

    public void setActivityPictureId(Long activityPictureId) {
        this.activityPictureId = activityPictureId;
    }

    public String getActivityCoverImage() {
        return activityCoverImage;
    }

    public void setActivityCoverImage(String activityCoverImage) {
        this.activityCoverImage = activityCoverImage;
    }

    public Integer getActivityLikeNumber() {
        return activityLikeNumber;
    }

    public void setActivityLikeNumber(Integer activityLikeNumber) {
        this.activityLikeNumber = activityLikeNumber;
    }

    public Integer getActivityCommentNumber() {
        return activityCommentNumber;
    }

    public void setActivityCommentNumber(Integer activityCommentNumber) {
        this.activityCommentNumber = activityCommentNumber;
    }

    public Integer getActivityReviewStatus() {
        return activityReviewStatus;
    }

    public void setActivityReviewStatus(Integer activityReviewStatus) {
        this.activityReviewStatus = activityReviewStatus;
    }
}

