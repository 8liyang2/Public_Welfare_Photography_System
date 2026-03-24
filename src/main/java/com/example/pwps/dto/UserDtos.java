package com.example.pwps.dto;

import java.util.List;

public class UserDtos {

    public static class UserRegisterRequest {
        private String loginname;
        private String password1;
        private String password;
        private String avatar;

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
        }

        public String getPassword1() {
            return password1;
        }

        public void setPassword1(String password1) {
            this.password1 = password1;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    public static class UserRegisterResponse {
        private Long user_id;
        private Boolean success;
        private Integer loginname_exists;
        private Integer password_exists;

        public Long getUser_id() {
            return user_id;
        }

        public void setUser_id(Long user_id) {
            this.user_id = user_id;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Integer getLoginname_exists() {
            return loginname_exists;
        }

        public void setLoginname_exists(Integer loginname_exists) {
            this.loginname_exists = loginname_exists;
        }

        public Integer getPassword_exists() {
            return password_exists;
        }

        public void setPassword_exists(Integer password_exists) {
            this.password_exists = password_exists;
        }
    }

    public static class UserLoginRequest {
        private String loginname;
        private String password;

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
    }

    public static class UserLoginResponse {
        private Long uid;
        private Integer permission;
        private Boolean success;

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        public Integer getPermission() {
            return permission;
        }

        public void setPermission(Integer permission) {
            this.permission = permission;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }

    public static class UserUpdateRequest {
        private Long uid;
        private String loginname;
        private String username;
        private String avatar;
        private String email;
        private String personal_profile;
        private Integer permission;
        private java.util.Date birthday;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPersonal_profile() {
            return personal_profile;
        }

        public void setPersonal_profile(String personal_profile) {
            this.personal_profile = personal_profile;
        }

        public Integer getPermission() {
            return permission;
        }

        public void setPermission(Integer permission) {
            this.permission = permission;
        }

        public java.util.Date getBirthday() {
            return birthday;
        }

        public void setBirthday(java.util.Date birthday) {
            this.birthday = birthday;
        }
    }

    public static class UserUpdateResponse {
        private java.util.Date updated_at;
        private Boolean success;

        public java.util.Date getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(java.util.Date updated_at) {
            this.updated_at = updated_at;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }

    public static class UserDeleteRequest {
        private Long uid;
        private Integer permission;

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        public Integer getPermission() {
            return permission;
        }

        public void setPermission(Integer permission) {
            this.permission = permission;
        }
    }

    public static class UserDeleteResponse {
        private java.util.Date updated_at;
        private Boolean success;

        public java.util.Date getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(java.util.Date updated_at) {
            this.updated_at = updated_at;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }

    public static class UserInfoResponse {
        private Long uid;
        private String loginname;
        private String username;
        private String avatar;
        private String email;
        private String personal_profile;
        private Integer permission;
        private java.util.Date birthday;
        private Integer point;
        private Boolean success;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPersonal_profile() {
            return personal_profile;
        }

        public void setPersonal_profile(String personal_profile) {
            this.personal_profile = personal_profile;
        }

        public Integer getPermission() {
            return permission;
        }

        public void setPermission(Integer permission) {
            this.permission = permission;
        }

        public java.util.Date getBirthday() {
            return birthday;
        }

        public void setBirthday(java.util.Date birthday) {
            this.birthday = birthday;
        }

        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }
    }

    public static class PasswordChangeRequest {
        private Long uid;
        private String oldPassword;
        private String newPassword;

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    public static class PasswordChangeResponse {
        private Boolean success;
        private String message;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class BanRequest {
        private Long uid;
        private Integer permission;
        private Long ban_uid;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Integer getPermission() { return permission; }
        public void setPermission(Integer permission) { this.permission = permission; }
        public Long getBan_uid() { return ban_uid; }
        public void setBan_uid(Long ban_uid) { this.ban_uid = ban_uid; }
    }

    public static class BanResponse {
        private Long ban_uid;
        private Boolean success;

        public Long getBan_uid() { return ban_uid; }
        public void setBan_uid(Long ban_uid) { this.ban_uid = ban_uid; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
    }

    public static class PermissionRequest {
        private Long uid;
        private Integer permission;
        private Long target_uid;
        private Integer target_perm;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Integer getPermission() { return permission; }
        public void setPermission(Integer permission) { this.permission = permission; }
        public Long getTarget_uid() { return target_uid; }
        public void setTarget_uid(Long target_uid) { this.target_uid = target_uid; }
        public Integer getTarget_perm() { return target_perm; }
        public void setTarget_perm(Integer target_perm) { this.target_perm = target_perm; }
    }

    public static class PermissionResponse {
        private Long target_uid;
        private Boolean success;

        public Long getTarget_uid() { return target_uid; }
        public void setTarget_uid(Long target_uid) { this.target_uid = target_uid; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
    }

    public static class UserItem {
        private Long uid;
        private String loginname;
        private String username;
        private Integer permission;
        private Integer point;
        private java.util.Date created_at;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getLoginname() { return loginname; }
        public void setLoginname(String loginname) { this.loginname = loginname; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public Integer getPermission() { return permission; }
        public void setPermission(Integer permission) { this.permission = permission; }
        public Integer getPoint() { return point; }
        public void setPoint(Integer point) { this.point = point; }
        public java.util.Date getCreated_at() { return created_at; }
        public void setCreated_at(java.util.Date created_at) { this.created_at = created_at; }
    }

    public static class AllUserSearchResponse {
        private Boolean success;
        private List<UserItem> data;

        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public List<UserItem> getData() { return data; }
        public void setData(List<UserItem> data) { this.data = data; }
    }

    // 作品相关DTO
    public static class UphotoUploadRequest {
        private Long uid;
        private String uphoto;
        private String uphoto_title;
        private String uphoto_description;
        private String activity_id;
        private Integer uphoto_status;
        private Integer uphoto_label_0;
        private Integer uphoto_label_1;
        private Integer uphoto_label_2;
        private Integer uphoto_label_3;
        private Integer uphoto_label_4;
        private Integer uphoto_label_5;
        private Integer uphoto_label_6;
        private Integer uphoto_label_7;
        private Integer uphoto_label_8;
        private Integer uphoto_label_9;
        private Integer pointset;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getUphoto() { return uphoto; }
        public void setUphoto(String uphoto) { this.uphoto = uphoto; }
        public String getUphoto_title() { return uphoto_title; }
        public void setUphoto_title(String uphoto_title) { this.uphoto_title = uphoto_title; }
        public String getUphoto_description() { return uphoto_description; }
        public void setUphoto_description(String uphoto_description) { this.uphoto_description = uphoto_description; }
        public String getActivity_id() { return activity_id; }
        public void setActivity_id(String activity_id) { this.activity_id = activity_id; }
        public Integer getUphoto_status() { return uphoto_status; }
        public void setUphoto_status(Integer uphoto_status) { this.uphoto_status = uphoto_status; }
        public Integer getUphoto_label_0() { return uphoto_label_0; }
        public void setUphoto_label_0(Integer uphoto_label_0) { this.uphoto_label_0 = uphoto_label_0; }
        public Integer getUphoto_label_1() { return uphoto_label_1; }
        public void setUphoto_label_1(Integer uphoto_label_1) { this.uphoto_label_1 = uphoto_label_1; }
        public Integer getUphoto_label_2() { return uphoto_label_2; }
        public void setUphoto_label_2(Integer uphoto_label_2) { this.uphoto_label_2 = uphoto_label_2; }
        public Integer getUphoto_label_3() { return uphoto_label_3; }
        public void setUphoto_label_3(Integer uphoto_label_3) { this.uphoto_label_3 = uphoto_label_3; }
        public Integer getUphoto_label_4() { return uphoto_label_4; }
        public void setUphoto_label_4(Integer uphoto_label_4) { this.uphoto_label_4 = uphoto_label_4; }
        public Integer getUphoto_label_5() { return uphoto_label_5; }
        public void setUphoto_label_5(Integer uphoto_label_5) { this.uphoto_label_5 = uphoto_label_5; }
        public Integer getUphoto_label_6() { return uphoto_label_6; }
        public void setUphoto_label_6(Integer uphoto_label_6) { this.uphoto_label_6 = uphoto_label_6; }
        public Integer getUphoto_label_7() { return uphoto_label_7; }
        public void setUphoto_label_7(Integer uphoto_label_7) { this.uphoto_label_7 = uphoto_label_7; }
        public Integer getUphoto_label_8() { return uphoto_label_8; }
        public void setUphoto_label_8(Integer uphoto_label_8) { this.uphoto_label_8 = uphoto_label_8; }
        public Integer getUphoto_label_9() { return uphoto_label_9; }
        public void setUphoto_label_9(Integer uphoto_label_9) { this.uphoto_label_9 = uphoto_label_9; }
        public Integer getPointset() { return pointset; }
        public void setPointset(Integer pointset) { this.pointset = pointset; }
    }

    public static class UphotoUploadResponse {
        private Long upthoto_id;
        private String created_at;
        private Integer status;
        private Boolean success;
        private Integer point_remain;

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public String getCreated_at() { return created_at; }
        public void setCreated_at(String created_at) { this.created_at = created_at; }
        public Integer getStatus() { return status; }
        public void setStatus(Integer status) { this.status = status; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public Integer getPoint_remain() { return point_remain; }
        public void setPoint_remain(Integer point_remain) { this.point_remain = point_remain; }
    }

    public static class UphotoManageRequest {
        private Long uid;
        private String uphoto;
        private String uphoto_title;
        private String uphoto_description;
        private String activity_id;
        private Integer uphoto_status;
        private Integer uphoto_label_0;
        private Integer uphoto_label_1;
        private Integer uphoto_label_2;
        private Integer uphoto_label_3;
        private Integer uphoto_label_4;
        private Integer uphoto_label_5;
        private Integer uphoto_label_6;
        private Integer uphoto_label_7;
        private Integer uphoto_label_8;
        private Integer uphoto_label_9;
        private Long upthoto_id;
        private Integer pointset;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getUphoto() { return uphoto; }
        public void setUphoto(String uphoto) { this.uphoto = uphoto; }
        public String getUphoto_title() { return uphoto_title; }
        public void setUphoto_title(String uphoto_title) { this.uphoto_title = uphoto_title; }
        public String getUphoto_description() { return uphoto_description; }
        public void setUphoto_description(String uphoto_description) { this.uphoto_description = uphoto_description; }
        public String getActivity_id() { return activity_id; }
        public void setActivity_id(String activity_id) { this.activity_id = activity_id; }
        public Integer getUphoto_status() { return uphoto_status; }
        public void setUphoto_status(Integer uphoto_status) { this.uphoto_status = uphoto_status; }
        public Integer getUphoto_label_0() { return uphoto_label_0; }
        public void setUphoto_label_0(Integer uphoto_label_0) { this.uphoto_label_0 = uphoto_label_0; }
        public Integer getUphoto_label_1() { return uphoto_label_1; }
        public void setUphoto_label_1(Integer uphoto_label_1) { this.uphoto_label_1 = uphoto_label_1; }
        public Integer getUphoto_label_2() { return uphoto_label_2; }
        public void setUphoto_label_2(Integer uphoto_label_2) { this.uphoto_label_2 = uphoto_label_2; }
        public Integer getUphoto_label_3() { return uphoto_label_3; }
        public void setUphoto_label_3(Integer uphoto_label_3) { this.uphoto_label_3 = uphoto_label_3; }
        public Integer getUphoto_label_4() { return uphoto_label_4; }
        public void setUphoto_label_4(Integer uphoto_label_4) { this.uphoto_label_4 = uphoto_label_4; }
        public Integer getUphoto_label_5() { return uphoto_label_5; }
        public void setUphoto_label_5(Integer uphoto_label_5) { this.uphoto_label_5 = uphoto_label_5; }
        public Integer getUphoto_label_6() { return uphoto_label_6; }
        public void setUphoto_label_6(Integer uphoto_label_6) { this.uphoto_label_6 = uphoto_label_6; }
        public Integer getUphoto_label_7() { return uphoto_label_7; }
        public void setUphoto_label_7(Integer uphoto_label_7) { this.uphoto_label_7 = uphoto_label_7; }
        public Integer getUphoto_label_8() { return uphoto_label_8; }
        public void setUphoto_label_8(Integer uphoto_label_8) { this.uphoto_label_8 = uphoto_label_8; }
        public Integer getUphoto_label_9() { return uphoto_label_9; }
        public void setUphoto_label_9(Integer uphoto_label_9) { this.uphoto_label_9 = uphoto_label_9; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Integer getPointset() { return pointset; }
        public void setPointset(Integer pointset) { this.pointset = pointset; }
    }

    public static class UphotoManageResponse {
        private Long upthoto_id;
        private Long uid;
        private Boolean success;
        private Integer point_remain;

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public Integer getPoint_remain() { return point_remain; }
        public void setPoint_remain(Integer point_remain) { this.point_remain = point_remain; }
    }

    public static class UphotoSearchRequest {
        private Long uid;
        private Long ask_uid;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getAsk_uid() { return ask_uid; }
        public void setAsk_uid(Long ask_uid) { this.ask_uid = ask_uid; }
    }

    public static class UphotoSearchResponseItem {
        private Long uid;
        private Long upthoto_id;
        private String uphoto;
        private String uphoto_title;
        private String uphoto_description;
        private String activity_id;
        private Integer uphoto_status;
        private Integer uphoto_label_0;
        private Integer uphoto_label_1;
        private Integer uphoto_label_2;
        private Integer uphoto_label_3;
        private Integer uphoto_label_4;
        private Integer uphoto_label_5;
        private Integer uphoto_label_6;
        private Integer uphoto_label_7;
        private Integer uphoto_label_8;
        private Integer uphoto_label_9;
        private java.util.Date created_at;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public String getUphoto() { return uphoto; }
        public void setUphoto(String uphoto) { this.uphoto = uphoto; }
        public String getUphoto_title() { return uphoto_title; }
        public void setUphoto_title(String uphoto_title) { this.uphoto_title = uphoto_title; }
        public String getUphoto_description() { return uphoto_description; }
        public void setUphoto_description(String uphoto_description) { this.uphoto_description = uphoto_description; }
        public String getActivity_id() { return activity_id; }
        public void setActivity_id(String activity_id) { this.activity_id = activity_id; }
        public Integer getUphoto_status() { return uphoto_status; }
        public void setUphoto_status(Integer uphoto_status) { this.uphoto_status = uphoto_status; }
        public Integer getUphoto_label_0() { return uphoto_label_0; }
        public void setUphoto_label_0(Integer uphoto_label_0) { this.uphoto_label_0 = uphoto_label_0; }
        public Integer getUphoto_label_1() { return uphoto_label_1; }
        public void setUphoto_label_1(Integer uphoto_label_1) { this.uphoto_label_1 = uphoto_label_1; }
        public Integer getUphoto_label_2() { return uphoto_label_2; }
        public void setUphoto_label_2(Integer uphoto_label_2) { this.uphoto_label_2 = uphoto_label_2; }
        public Integer getUphoto_label_3() { return uphoto_label_3; }
        public void setUphoto_label_3(Integer uphoto_label_3) { this.uphoto_label_3 = uphoto_label_3; }
        public Integer getUphoto_label_4() { return uphoto_label_4; }
        public void setUphoto_label_4(Integer uphoto_label_4) { this.uphoto_label_4 = uphoto_label_4; }
        public Integer getUphoto_label_5() { return uphoto_label_5; }
        public void setUphoto_label_5(Integer uphoto_label_5) { this.uphoto_label_5 = uphoto_label_5; }
        public Integer getUphoto_label_6() { return uphoto_label_6; }
        public void setUphoto_label_6(Integer uphoto_label_6) { this.uphoto_label_6 = uphoto_label_6; }
        public Integer getUphoto_label_7() { return uphoto_label_7; }
        public void setUphoto_label_7(Integer uphoto_label_7) { this.uphoto_label_7 = uphoto_label_7; }
        public Integer getUphoto_label_8() { return uphoto_label_8; }
        public void setUphoto_label_8(Integer uphoto_label_8) { this.uphoto_label_8 = uphoto_label_8; }
        public Integer getUphoto_label_9() { return uphoto_label_9; }
        public void setUphoto_label_9(Integer uphoto_label_9) { this.uphoto_label_9 = uphoto_label_9; }
        public java.util.Date getCreated_at() { return created_at; }
        public void setCreated_at(java.util.Date created_at) { this.created_at = created_at; }
    }

    public static class UphotoSearchResponse {
        private List<UphotoSearchResponseItem> data;
        private Boolean success;

        public List<UphotoSearchResponseItem> getData() { return data; }
        public void setData(List<UphotoSearchResponseItem> data) { this.data = data; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
    }

    public static class UphotoDeleteRequest {
        private Long uid;
        private Long upthoto_id;
        private Integer pointset;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Integer getPointset() { return pointset; }
        public void setPointset(Integer pointset) { this.pointset = pointset; }
    }

    public static class UphotoDeleteResponse {
        private Long uid;
        private Long upthoto_id;
        private Boolean success;
        private Integer point_remain;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public Integer getPoint_remain() { return point_remain; }
        public void setPoint_remain(Integer point_remain) { this.point_remain = point_remain; }
    }

    public static class UphotoGetRequest {
        private Long uid;
        private Long upthoto_id;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
    }

    public static class UphotoGetResponse {
        private Long uid;
        private String username;
        private String avatar;
        private String uphoto;
        private String uphoto_title;
        private String uphoto_description;
        private String activity_id;
        private String activity_name;
        private Integer uphoto_status;
        private Integer uphoto_label_0;
        private Integer uphoto_label_1;
        private Integer uphoto_label_2;
        private Integer uphoto_label_3;
        private Integer uphoto_label_4;
        private Integer uphoto_label_5;
        private Integer uphoto_label_6;
        private Integer uphoto_label_7;
        private Integer uphoto_label_8;
        private Integer uphoto_label_9;
        private Integer uphoto_like_number;
        private Integer uphoto_comment_number;
        private Boolean liked;
        private java.util.Date created_at;
        private Boolean success;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
        public String getUphoto() { return uphoto; }
        public void setUphoto(String uphoto) { this.uphoto = uphoto; }
        public String getUphoto_title() { return uphoto_title; }
        public void setUphoto_title(String uphoto_title) { this.uphoto_title = uphoto_title; }
        public String getUphoto_description() { return uphoto_description; }
        public void setUphoto_description(String uphoto_description) { this.uphoto_description = uphoto_description; }
        public String getActivity_id() { return activity_id; }
        public void setActivity_id(String activity_id) { this.activity_id = activity_id; }
        public String getActivity_name() { return activity_name; }
        public void setActivity_name(String activity_name) { this.activity_name = activity_name; }
        public Integer getUphoto_status() { return uphoto_status; }
        public void setUphoto_status(Integer uphoto_status) { this.uphoto_status = uphoto_status; }
        public Integer getUphoto_label_0() { return uphoto_label_0; }
        public void setUphoto_label_0(Integer uphoto_label_0) { this.uphoto_label_0 = uphoto_label_0; }
        public Integer getUphoto_label_1() { return uphoto_label_1; }
        public void setUphoto_label_1(Integer uphoto_label_1) { this.uphoto_label_1 = uphoto_label_1; }
        public Integer getUphoto_label_2() { return uphoto_label_2; }
        public void setUphoto_label_2(Integer uphoto_label_2) { this.uphoto_label_2 = uphoto_label_2; }
        public Integer getUphoto_label_3() { return uphoto_label_3; }
        public void setUphoto_label_3(Integer uphoto_label_3) { this.uphoto_label_3 = uphoto_label_3; }
        public Integer getUphoto_label_4() { return uphoto_label_4; }
        public void setUphoto_label_4(Integer uphoto_label_4) { this.uphoto_label_4 = uphoto_label_4; }
        public Integer getUphoto_label_5() { return uphoto_label_5; }
        public void setUphoto_label_5(Integer uphoto_label_5) { this.uphoto_label_5 = uphoto_label_5; }
        public Integer getUphoto_label_6() { return uphoto_label_6; }
        public void setUphoto_label_6(Integer uphoto_label_6) { this.uphoto_label_6 = uphoto_label_6; }
        public Integer getUphoto_label_7() { return uphoto_label_7; }
        public void setUphoto_label_7(Integer uphoto_label_7) { this.uphoto_label_7 = uphoto_label_7; }
        public Integer getUphoto_label_8() { return uphoto_label_8; }
        public void setUphoto_label_8(Integer uphoto_label_8) { this.uphoto_label_8 = uphoto_label_8; }
        public Integer getUphoto_label_9() { return uphoto_label_9; }
        public void setUphoto_label_9(Integer uphoto_label_9) { this.uphoto_label_9 = uphoto_label_9; }
        public Integer getUphoto_like_number() { return uphoto_like_number; }
        public void setUphoto_like_number(Integer uphoto_like_number) { this.uphoto_like_number = uphoto_like_number; }
        public Integer getUphoto_comment_number() { return uphoto_comment_number; }
        public void setUphoto_comment_number(Integer uphoto_comment_number) { this.uphoto_comment_number = uphoto_comment_number; }
        public Boolean getLiked() { return liked; }
        public void setLiked(Boolean liked) { this.liked = liked; }
        public java.util.Date getCreated_at() { return created_at; }
        public void setCreated_at(java.util.Date created_at) { this.created_at = created_at; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
    }

    public static class UphotoLikeRequest {
        private Long uid;
        private Long upthoto_id;
        private Integer like_change;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Integer getLike_change() { return like_change; }
        public void setLike_change(Integer like_change) { this.like_change = like_change; }
    }

    public static class UphotoLikeResponse {
        private Long upthoto_id;
        private Boolean success;
        private Integer like_count;
        private Boolean liked;

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public Integer getLike_count() { return like_count; }
        public void setLike_count(Integer like_count) { this.like_count = like_count; }
        public Boolean getLiked() { return liked; }
        public void setLiked(Boolean liked) { this.liked = liked; }
    }

    public static class UphotoCommentRequest {
        private Long uid;
        private Long upthoto_id;
        private String uphoto_comment_detail;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public String getUphoto_comment_detail() { return uphoto_comment_detail; }
        public void setUphoto_comment_detail(String uphoto_comment_detail) { this.uphoto_comment_detail = uphoto_comment_detail; }
    }

    public static class UphotoCommentResponse {
        private Long upthoto_id;
        private Boolean success;
        private Integer comment_count;

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public Integer getComment_count() { return comment_count; }
        public void setComment_count(Integer comment_count) { this.comment_count = comment_count; }
    }

    public static class UphotoCommentItem {
        private Long id;
        private Long uid;
        private String username;
        private String avatar;
        private String uphoto_comment_detail;
        private java.util.Date created_at;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
        public String getUphoto_comment_detail() { return uphoto_comment_detail; }
        public void setUphoto_comment_detail(String uphoto_comment_detail) { this.uphoto_comment_detail = uphoto_comment_detail; }
        public java.util.Date getCreated_at() { return created_at; }
        public void setCreated_at(java.util.Date created_at) { this.created_at = created_at; }
    }

    public static class UphotoCommentsResponse {
        private Boolean success;
        private java.util.List<UphotoCommentItem> comments;

        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public java.util.List<UphotoCommentItem> getComments() { return comments; }
        public void setComments(java.util.List<UphotoCommentItem> comments) { this.comments = comments; }
    }
}

