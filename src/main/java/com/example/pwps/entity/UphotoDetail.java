package com.example.pwps.entity;

import java.io.Serializable;
import java.util.Date;

public class UphotoDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long upthotoId;
    private Long uid;
    private Integer uphotoLike;
    private Integer uphotoComment;
    private String uphotoCommentDetail;
    private Date createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUpthotoId() { return upthotoId; }
    public void setUpthotoId(Long upthotoId) { this.upthotoId = upthotoId; }
    public Long getUid() { return uid; }
    public void setUid(Long uid) { this.uid = uid; }
    public Integer getUphotoLike() { return uphotoLike; }
    public void setUphotoLike(Integer uphotoLike) { this.uphotoLike = uphotoLike; }
    public Integer getUphotoComment() { return uphotoComment; }
    public void setUphotoComment(Integer uphotoComment) { this.uphotoComment = uphotoComment; }
    public String getUphotoCommentDetail() { return uphotoCommentDetail; }
    public void setUphotoCommentDetail(String uphotoCommentDetail) { this.uphotoCommentDetail = uphotoCommentDetail; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
