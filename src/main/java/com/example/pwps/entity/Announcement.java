package com.example.pwps.entity;

import java.util.Date;

public class Announcement {
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
