package com.example.pwps.dto;

import java.util.Date;
import java.util.List;

public class ActivityDtos {

    // /api/user/activity/create
    public static class ActivityCreateRequest {
        public String activity_name;
        public String activity_description;
        public Integer activity_category;
        public String activity_location;
        public Date activity_time; // 文档写 date；此处按单日处理：early=end=activity_time
        public Integer activity_status;
        public String activity_cover_image; // Base64 DataURL 或相对路径
        public Long uid;
        public Integer permission;
        public Integer pointset; // -100
    }

    public static class ActivityCreateResponse {
        public Long activity_id;
        public Date created_at;
        public Boolean success;
        public Long activity_picture_id;
        public Integer point_remain;
    }

    // /api/user/activity/edit
    public static class ActivityEditRequest {
        public Long activity_id;
        public String activity_name;
        public String activity_description;
        public Integer activity_category;
        public String activity_location;
        public Date activity_time;
        public Integer activity_status;
        public String activity_cover_image; // Base64 或相对路径
        public Long uid;
        public Integer permission;
        public Integer pointset; // -5
    }

    public static class ActivityEditResponse {
        public Date created_at;
        public Boolean success;
        public Long activity_id;
        public Integer point_remain;
    }

    // /api/user/activity/delete
    public static class ActivityDeleteRequest {
        public Long activity_id;
        public Long uid;
        public Integer permission;
        public Integer pointset; // -300
    }

    public static class ActivityDeleteResponse {
        public Long uid;
        public Date deleted_at;
        public Boolean success;
        public Integer point_remain;
    }

    // /api/user/activity/query
    public static class ActivityQueryResponseItem {
        public Long activity_id;
        public String activity_cover_image_URL;
        public String activity_name;
        public String activity_cover_image;
        public String activity_description;
        public Integer activity_category;
        public String activity_location;
        public java.util.Date activity_time_early;
        public java.util.Date activity_time_end;
        public Integer activity_status;
        public Integer activity_review_status;
    }

    public static class ActivityQueryResponse {
        public Boolean success;
        public List<ActivityQueryResponseItem> data;
    }

    // /api/admin/activity/review (GET list)
    public static class AdminActivityReviewListItem {
        public Long activity_id;
        public String activity_cover_image_URL;
        public String activity_name;
        public Integer activity_category;
        public Integer activity_status;
        public String activity_description;
        public String activity_cover_image;
    }

    public static class AdminActivityReviewListResponse {
        public Boolean success;
        public List<AdminActivityReviewListItem> data;
    }

    // /api/main/activity/search
    public static class MainActivitySearchResponseItem {
        public Long uid;
        public String username;
        public String activity_cover_image;
        public String activity_name;
        public String activity_description;
        public Integer activity_status;
        public Long activity_id;
        public Integer activity_category;
        public Date activity_time_early;
        public Date activity_time_end;
        public Integer activity_like_number;
        public Integer activity_comment_number;
        public Integer submission_count;
    }

    public static class MainActivitySearchResponse {
        private Boolean success;
        private List<MainActivitySearchResponseItem> data;

        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public List<MainActivitySearchResponseItem> getData() { return data; }
        public void setData(List<MainActivitySearchResponseItem> data) { this.data = data; }
    }

    // /api/main/activity/{id}/like
    public static class ActivityLikeRequest {
        public Long uid;
        public Long activity_id;
        public Integer like_change; // 1 点赞，0 取消
    }

    public static class ActivityLikeResponse {
        public Long activity_id;
        public Boolean success;
        public Long uid;
    }

    // /api/main/activity/{id}/comment
    public static class ActivityCommentRequest {
        public Long uid;
        public Long activity_id;
        public String activity_comment_detail;
    }

    public static class ActivityCommentResponse {
        public Long activity_id;
        public Boolean success;
        public Long uid;
    }

    // /api/main/activity/{id}/get
    public static class ActivityGetRequest {
        public Long uid;
        public Long activity_id;
        public Integer permission;
    }

    public static class ActivityGetResponse {
        public Long activity_id;
        public Boolean success;
        public Long uid;
        public String username;
        public String avatar;
        public String activity_name;
        public String activity_description;
        public Integer activity_category;
        public String activity_location;
        public Date activity_time;
        public Integer activity_status;
        public String activity_cover_image;
        public Integer activity_like_number;
        public Integer activity_comment_number;
        public Integer like_count;
        public Boolean liked;
    }

    // /api/admin/activity/review/ (decision)
    public static class AdminActivityReviewDecisionResponse {
        public Long activity_id;
        public Boolean success;
    }

    public static class AdminUphotoReviewListItem {
        public Long upthoto_id;
        public Long uid;
        public String username;
        public String uphoto;
        public String uphoto_title;
        public String uphoto_description;
    }

    public static class AdminUphotoReviewListResponse {
        public Boolean success;
        public List<AdminUphotoReviewListItem> data;
    }

    public static class AdminUphotoReviewDecisionResponse {
        public Long upthoto_id;
        public Boolean success;
    }

    // 评论管理相关DTO
    public static class CommentItem {
        private Long id;
        private Long activity_id;
        private Long upthoto_id;
        private Long uid;
        private String username;
        private String comment_detail;
        private Date created_at;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getActivity_id() { return activity_id; }
        public void setActivity_id(Long activity_id) { this.activity_id = activity_id; }
        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getComment_detail() { return comment_detail; }
        public void setComment_detail(String comment_detail) { this.comment_detail = comment_detail; }
        public Date getCreated_at() { return created_at; }
        public void setCreated_at(Date created_at) { this.created_at = created_at; }
    }

    public static class CommentListResponse {
        private Boolean success;
        private List<CommentItem> data;
        private Integer total;
        private Integer page;
        private Integer pageSize;

        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public List<CommentItem> getData() { return data; }
        public void setData(List<CommentItem> data) { this.data = data; }
        public Integer getTotal() { return total; }
        public void setTotal(Integer total) { this.total = total; }
        public Integer getPage() { return page; }
        public void setPage(Integer page) { this.page = page; }
        public Integer getPageSize() { return pageSize; }
        public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }
    }

    public static class CommentDeleteRequest {
        private Long id;
        private Long uid;
        private Integer permission;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Integer getPermission() { return permission; }
        public void setPermission(Integer permission) { this.permission = permission; }
    }

    public static class CommentDeleteResponse {
        private Boolean success;
        private Long id;

        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
    }

    public static class ActivityCommentItem {
        private Long id;
        private Long uid;
        private String username;
        private String avatar;
        private String activity_comment_detail;
        private Date created_at;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getAvatar() { return avatar; }
        public void setAvatar(String avatar) { this.avatar = avatar; }
        public String getActivity_comment_detail() { return activity_comment_detail; }
        public void setActivity_comment_detail(String activity_comment_detail) { this.activity_comment_detail = activity_comment_detail; }
        public Date getCreated_at() { return created_at; }
        public void setCreated_at(Date created_at) { this.created_at = created_at; }
    }

    public static class ActivityCommentsResponse {
        private Boolean success;
        private List<ActivityCommentItem> comments;

        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
        public List<ActivityCommentItem> getComments() { return comments; }
        public void setComments(List<ActivityCommentItem> comments) { this.comments = comments; }
    }
}

