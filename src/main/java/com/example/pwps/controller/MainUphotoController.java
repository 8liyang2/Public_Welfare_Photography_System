package com.example.pwps.controller;

import com.example.pwps.dto.UserDtos;
import com.example.pwps.entity.Uphoto;
import com.example.pwps.entity.User;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.mapper.UphotoDetailMapper;
import com.example.pwps.service.UphotoService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/main/uphoto")
public class MainUphotoController {

    private final UphotoService uphotoService;
    private final UserMapper userMapper;
    private final UphotoDetailMapper uphotoDetailMapper;

    public MainUphotoController(UphotoService uphotoService, UserMapper userMapper, UphotoDetailMapper uphotoDetailMapper) {
        this.uphotoService = uphotoService;
        this.userMapper = userMapper;
        this.uphotoDetailMapper = uphotoDetailMapper;
    }

    @PostMapping("/search")
    public MainUphotoSearchResponse search(@RequestBody MainUphotoSearchRequest request) {
        MainUphotoSearchResponse resp = new MainUphotoSearchResponse();
        try {
            List<Uphoto> photos;
            
            if (request.getActivity_id() != null && request.getActivity_id() > 0) {
                photos = uphotoService.getPhotosByActivityId(request.getActivity_id());
            } else if (request.getMain_uphoto_search_2() != null && request.getMain_uphoto_search_2() >= 0 && request.getMain_uphoto_search_2() <= 9) {
                photos = uphotoService.getPublicPhotosByLabel(request.getMain_uphoto_search_2(), 1);
            } else {
                photos = uphotoService.getPublicPhotos();
            }
            
            if (request.getMain_uphoto_search_3_earlydate() != null && request.getMain_uphoto_search_3_latedate() != null) {
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    Date earlyDate = sdf.parse(request.getMain_uphoto_search_3_earlydate());
                    Date lateDate = sdf.parse(request.getMain_uphoto_search_3_latedate());
                    lateDate.setHours(23);
                    lateDate.setMinutes(59);
                    lateDate.setSeconds(59);
                    final Date early = earlyDate;
                    final Date late = lateDate;
                    photos = photos.stream()
                            .filter(p -> p.getCreatedAt() != null && 
                                    !p.getCreatedAt().before(early) && 
                                    !p.getCreatedAt().after(late))
                            .collect(java.util.stream.Collectors.toList());
                } catch (Exception e) {
                }
            }
            
            Map<Long, Integer> likeCountMap = new HashMap<>();
            for (Uphoto photo : photos) {
                likeCountMap.put(photo.getUpthotoId(), uphotoDetailMapper.countLikesByUphotoId(photo.getUpthotoId()));
            }
            
            if (request.getMain_uphoto_search_1() != null && request.getMain_uphoto_search_1() == 2) {
                photos.sort((a, b) -> {
                    Integer countA = likeCountMap.getOrDefault(a.getUpthotoId(), 0);
                    Integer countB = likeCountMap.getOrDefault(b.getUpthotoId(), 0);
                    return countB.compareTo(countA);
                });
            } else {
                photos.sort((a, b) -> {
                    if (a.getCreatedAt() == null) return 1;
                    if (b.getCreatedAt() == null) return -1;
                    return b.getCreatedAt().compareTo(a.getCreatedAt());
                });
            }
            
            List<MainUphotoSearchResponseItem> items = photos.stream()
                    .map(p -> {
                        MainUphotoSearchResponseItem item = new MainUphotoSearchResponseItem();
                        item.setUpthoto_id(p.getUpthotoId());
                        item.setUid(p.getUid());
                        item.setUphoto(p.getUphotoPath());
                        item.setUphoto_title(p.getUphotoTitle());
                        item.setUphoto_like_count(likeCountMap.getOrDefault(p.getUpthotoId(), 0));
                        
                        try {
                            User user = userMapper.findByUid(p.getUid());
                            if (user != null) {
                                item.setUsername(user.getUsername());
                                item.setAvatar(user.getAvatar());
                            }
                        } catch (Exception e) {
                        }
                        
                        item.setUphoto_label_0(p.getUphotoLabel0());
                        item.setUphoto_label_1(p.getUphotoLabel1());
                        item.setUphoto_label_2(p.getUphotoLabel2());
                        item.setUphoto_label_3(p.getUphotoLabel3());
                        item.setUphoto_label_4(p.getUphotoLabel4());
                        item.setUphoto_label_5(p.getUphotoLabel5());
                        item.setUphoto_label_6(p.getUphotoLabel6());
                        item.setUphoto_label_7(p.getUphotoLabel7());
                        item.setUphoto_label_8(p.getUphotoLabel8());
                        item.setUphoto_label_9(p.getUphotoLabel9());
                        
                        return item;
                    })
                    .collect(java.util.stream.Collectors.toList());
            resp.setData(items);
            resp.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
        }
        return resp;
    }

    @PostMapping("/{uphotoId}/get")
    public UserDtos.UphotoGetResponse get(@PathVariable("uphotoId") Long uphotoId,
                                          @RequestBody UserDtos.UphotoGetRequest request) {
        request.setUpthoto_id(uphotoId);
        return uphotoService.get(request);
    }

    // 内部类：请求和响应结构
    public static class MainUphotoSearchRequest {
        private Long uid;
        private Long activity_id;
        private Integer main_uphoto_search_1;
        private Integer main_uphoto_search_2;
        private String main_uphoto_search_3_earlydate;
        private String main_uphoto_search_3_latedate;

        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Long getActivity_id() { return activity_id; }
        public void setActivity_id(Long activity_id) { this.activity_id = activity_id; }
        public Integer getMain_uphoto_search_1() { return main_uphoto_search_1; }
        public void setMain_uphoto_search_1(Integer main_uphoto_search_1) { this.main_uphoto_search_1 = main_uphoto_search_1; }
        public Integer getMain_uphoto_search_2() { return main_uphoto_search_2; }
        public void setMain_uphoto_search_2(Integer main_uphoto_search_2) { this.main_uphoto_search_2 = main_uphoto_search_2; }
        public String getMain_uphoto_search_3_earlydate() { return main_uphoto_search_3_earlydate; }
        public void setMain_uphoto_search_3_earlydate(String main_uphoto_search_3_earlydate) { this.main_uphoto_search_3_earlydate = main_uphoto_search_3_earlydate; }
        public String getMain_uphoto_search_3_latedate() { return main_uphoto_search_3_latedate; }
        public void setMain_uphoto_search_3_latedate(String main_uphoto_search_3_latedate) { this.main_uphoto_search_3_latedate = main_uphoto_search_3_latedate; }
    }

    public static class MainUphotoSearchResponseItem {
        private Long upthoto_id;
        private Long uid;
        private String username;
        private String avatar;
        private String uphoto;
        private String uphoto_title;
        private Integer uphoto_like_count;
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

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
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
        public Integer getUphoto_like_count() { return uphoto_like_count; }
        public void setUphoto_like_count(Integer uphoto_like_count) { this.uphoto_like_count = uphoto_like_count; }
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
    }

    public static class MainUphotoSearchResponse {
        private List<MainUphotoSearchResponseItem> data;
        private Boolean success;

        public List<MainUphotoSearchResponseItem> getData() { return data; }
        public void setData(List<MainUphotoSearchResponseItem> data) { this.data = data; }
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
        private Long uid;
        private Boolean success;

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
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
        private Long uid;
        private Boolean success;

        public Long getUpthoto_id() { return upthoto_id; }
        public void setUpthoto_id(Long upthoto_id) { this.upthoto_id = upthoto_id; }
        public Long getUid() { return uid; }
        public void setUid(Long uid) { this.uid = uid; }
        public Boolean getSuccess() { return success; }
        public void setSuccess(Boolean success) { this.success = success; }
    }
}