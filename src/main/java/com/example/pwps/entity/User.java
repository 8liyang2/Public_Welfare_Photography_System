package com.example.pwps.entity;

import java.util.Date;

public class User {

    private Long uid;
    private String loginname;
    private String password;
    private String username;
    private String email;
    private String avatar;
    private String personalProfile;
    private Byte permission;
    private Date birthday;
    private Integer activityCreated;
    private Integer uphotoCreated;
    private Integer point;
    private Date createdAt;
    private Date updatedAt;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getActivityCreated() {
        return activityCreated;
    }

    public void setActivityCreated(Integer activityCreated) {
        this.activityCreated = activityCreated;
    }

    public Integer getUphotoCreated() {
        return uphotoCreated;
    }

    public void setUphotoCreated(Integer uphotoCreated) {
        this.uphotoCreated = uphotoCreated;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

