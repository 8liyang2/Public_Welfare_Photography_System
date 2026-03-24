package com.example.pwps.entity;

import java.io.Serializable;
import java.util.Date;

public class ActivityDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long activityId;
    private Long uid;
    private Integer activityLike;
    private Integer activityComment;
    private String activityCommentDetail;
    private Date createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public Long getUid() { return uid; }
    public void setUid(Long uid) { this.uid = uid; }
    public Integer getActivityLike() { return activityLike; }
    public void setActivityLike(Integer activityLike) { this.activityLike = activityLike; }
    public Integer getActivityComment() { return activityComment; }
    public void setActivityComment(Integer activityComment) { this.activityComment = activityComment; }
    public String getActivityCommentDetail() { return activityCommentDetail; }
    public void setActivityCommentDetail(String activityCommentDetail) { this.activityCommentDetail = activityCommentDetail; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
