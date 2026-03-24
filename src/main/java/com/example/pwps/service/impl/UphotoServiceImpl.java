package com.example.pwps.service.impl;

import com.example.pwps.dto.UserDtos;
import com.example.pwps.entity.Activity;
import com.example.pwps.entity.Uphoto;
import com.example.pwps.entity.UphotoDetail;
import com.example.pwps.entity.User;
import com.example.pwps.mapper.ActivityMapper;
import com.example.pwps.mapper.UphotoDetailMapper;
import com.example.pwps.mapper.UphotoMapper;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.UphotoService;
import com.example.pwps.util.PictureStorageUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UphotoServiceImpl implements UphotoService {

    private final UphotoMapper uphotoMapper;
    private final UserMapper userMapper;
    private final ActivityMapper activityMapper;
    private final UphotoDetailMapper uphotoDetailMapper;

    public UphotoServiceImpl(UphotoMapper uphotoMapper, UserMapper userMapper, 
                              ActivityMapper activityMapper, UphotoDetailMapper uphotoDetailMapper) {
        this.uphotoMapper = uphotoMapper;
        this.userMapper = userMapper;
        this.activityMapper = activityMapper;
        this.uphotoDetailMapper = uphotoDetailMapper;
    }

    @Override
    public UserDtos.UphotoUploadResponse upload(UserDtos.UphotoUploadRequest request) {
        UserDtos.UphotoUploadResponse resp = new UserDtos.UphotoUploadResponse();
        try {
            // 检查积分
            Integer currentPoint = userMapper.findPointByUid(request.getUid());
            if (currentPoint == null) {
                resp.setSuccess(false);
                return resp;
            }
            if (currentPoint < Math.abs(request.getPointset())) {
                resp.setSuccess(false);
                resp.setPoint_remain(currentPoint);
                return resp;
            }

            // 计算标签数量
            int labelCount = 0;
            if (request.getUphoto_label_0() != null && request.getUphoto_label_0() == 1) labelCount++;
            if (request.getUphoto_label_1() != null && request.getUphoto_label_1() == 1) labelCount++;
            if (request.getUphoto_label_2() != null && request.getUphoto_label_2() == 1) labelCount++;
            if (request.getUphoto_label_3() != null && request.getUphoto_label_3() == 1) labelCount++;
            if (request.getUphoto_label_4() != null && request.getUphoto_label_4() == 1) labelCount++;
            if (request.getUphoto_label_5() != null && request.getUphoto_label_5() == 1) labelCount++;
            if (request.getUphoto_label_6() != null && request.getUphoto_label_6() == 1) labelCount++;
            if (request.getUphoto_label_7() != null && request.getUphoto_label_7() == 1) labelCount++;
            if (request.getUphoto_label_8() != null && request.getUphoto_label_8() == 1) labelCount++;
            if (request.getUphoto_label_9() != null && request.getUphoto_label_9() == 1) labelCount++;

            if (labelCount > 3) {
                resp.setSuccess(false);
                return resp;
            }

            // 处理图片
            String savedPath = null;
            if (StringUtils.hasText(request.getUphoto())) {
                try {
                    String fileName = "uphoto_" + System.currentTimeMillis() + ".png";
                    savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                            PictureStorageUtil.Category.UPHOTO,
                            request.getUphoto(),
                            fileName
                    );
                } catch (Exception e) {
                    // 图片保存失败，不影响其他字段
                }
            }

            // 创建作品
            Uphoto uphoto = new Uphoto();
            uphoto.setUid(request.getUid());
            uphoto.setUphotoPath(savedPath);
            uphoto.setUphotoTitle(request.getUphoto_title());
            uphoto.setUphotoDescription(request.getUphoto_description());
            uphoto.setActivityId(request.getActivity_id());
            uphoto.setUphotoStatus(request.getUphoto_status());
            uphoto.setUphotoLabel0(request.getUphoto_label_0());
            uphoto.setUphotoLabel1(request.getUphoto_label_1());
            uphoto.setUphotoLabel2(request.getUphoto_label_2());
            uphoto.setUphotoLabel3(request.getUphoto_label_3());
            uphoto.setUphotoLabel4(request.getUphoto_label_4());
            uphoto.setUphotoLabel5(request.getUphoto_label_5());
            uphoto.setUphotoLabel6(request.getUphoto_label_6());
            uphoto.setUphotoLabel7(request.getUphoto_label_7());
            uphoto.setUphotoLabel8(request.getUphoto_label_8());
            uphoto.setUphotoLabel9(request.getUphoto_label_9());
            uphoto.setUphotoReviewStatus(1); // 待审核
            uphoto.setCreatedAt(new Date());

            uphotoMapper.insert(uphoto);

            // 更新积分
            userMapper.updatePoint(request.getUid(), request.getPointset());
            Integer newPoint = userMapper.findPointByUid(request.getUid());

            resp.setUpthoto_id(uphoto.getUpthotoId());
            resp.setCreated_at(uphoto.getCreatedAt().toString());
            resp.setStatus(uphoto.getUphotoReviewStatus());
            resp.setSuccess(true);
            resp.setPoint_remain(newPoint);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
        }
        return resp;
    }

    @Override
    public UserDtos.UphotoManageResponse manage(UserDtos.UphotoManageRequest request) {
        UserDtos.UphotoManageResponse resp = new UserDtos.UphotoManageResponse();
        try {
            Integer pointset = request.getPointset() != null ? request.getPointset() : 0;
            
            if (pointset < 0) {
                Integer currentPoint = userMapper.findPointByUid(request.getUid());
                if (currentPoint == null) {
                    resp.setSuccess(false);
                    return resp;
                }
                if (currentPoint < Math.abs(pointset)) {
                    resp.setSuccess(false);
                    resp.setPoint_remain(currentPoint);
                    return resp;
                }
            }

            Uphoto existing = uphotoMapper.findById(request.getUpthoto_id());
            if (existing == null || !existing.getUid().equals(request.getUid())) {
                resp.setSuccess(false);
                return resp;
            }

            int labelCount = 0;
            if (request.getUphoto_label_0() != null && request.getUphoto_label_0() == 1) labelCount++;
            if (request.getUphoto_label_1() != null && request.getUphoto_label_1() == 1) labelCount++;
            if (request.getUphoto_label_2() != null && request.getUphoto_label_2() == 1) labelCount++;
            if (request.getUphoto_label_3() != null && request.getUphoto_label_3() == 1) labelCount++;
            if (request.getUphoto_label_4() != null && request.getUphoto_label_4() == 1) labelCount++;
            if (request.getUphoto_label_5() != null && request.getUphoto_label_5() == 1) labelCount++;
            if (request.getUphoto_label_6() != null && request.getUphoto_label_6() == 1) labelCount++;
            if (request.getUphoto_label_7() != null && request.getUphoto_label_7() == 1) labelCount++;
            if (request.getUphoto_label_8() != null && request.getUphoto_label_8() == 1) labelCount++;
            if (request.getUphoto_label_9() != null && request.getUphoto_label_9() == 1) labelCount++;

            if (labelCount > 3) {
                resp.setSuccess(false);
                return resp;
            }

            String savedPath = existing.getUphotoPath();
            if (StringUtils.hasText(request.getUphoto())) {
                try {
                    String fileName = "uphoto_" + request.getUpthoto_id() + ".png";
                    savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                            PictureStorageUtil.Category.UPHOTO,
                            request.getUphoto(),
                            fileName
                    );
                } catch (Exception e) {
                    // 图片保存失败，使用原有图片
                }
            }

            existing.setUphotoPath(savedPath);
            existing.setUphotoTitle(request.getUphoto_title());
            existing.setUphotoDescription(request.getUphoto_description());
            existing.setActivityId(request.getActivity_id());
            existing.setUphotoStatus(request.getUphoto_status());
            existing.setUphotoLabel0(request.getUphoto_label_0());
            existing.setUphotoLabel1(request.getUphoto_label_1());
            existing.setUphotoLabel2(request.getUphoto_label_2());
            existing.setUphotoLabel3(request.getUphoto_label_3());
            existing.setUphotoLabel4(request.getUphoto_label_4());
            existing.setUphotoLabel5(request.getUphoto_label_5());
            existing.setUphotoLabel6(request.getUphoto_label_6());
            existing.setUphotoLabel7(request.getUphoto_label_7());
            existing.setUphotoLabel8(request.getUphoto_label_8());
            existing.setUphotoLabel9(request.getUphoto_label_9());
            existing.setUphotoReviewStatus(1);

            uphotoMapper.update(existing);

            if (pointset != 0) {
                userMapper.updatePoint(request.getUid(), pointset);
            }
            Integer newPoint = userMapper.findPointByUid(request.getUid());

            resp.setUpthoto_id(existing.getUpthotoId());
            resp.setUid(existing.getUid());
            resp.setSuccess(true);
            resp.setPoint_remain(newPoint);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
        }
        return resp;
    }

    @Override
    public UserDtos.UphotoSearchResponse search(UserDtos.UphotoSearchRequest request) {
        UserDtos.UphotoSearchResponse resp = new UserDtos.UphotoSearchResponse();
        try {
            List<Uphoto> photos = uphotoMapper.findByUid(request.getAsk_uid());
            if (request.getUid() != null && !request.getUid().equals(request.getAsk_uid())) {
                photos = photos.stream()
                        .filter(p -> p.getUphotoStatus() == 1 && p.getUphotoReviewStatus() == 2)
                        .collect(Collectors.toList());
            }
            List<UserDtos.UphotoSearchResponseItem> items = photos.stream()
                    .map(p -> {
                        UserDtos.UphotoSearchResponseItem item = new UserDtos.UphotoSearchResponseItem();
                        item.setUid(p.getUid());
                        item.setUpthoto_id(p.getUpthotoId());
                        item.setUphoto(p.getUphotoPath());
                        item.setUphoto_title(p.getUphotoTitle());
                        item.setUphoto_description(p.getUphotoDescription());
                        item.setActivity_id(p.getActivityId());
                        item.setUphoto_status(p.getUphotoStatus());
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
                        item.setCreated_at(p.getCreatedAt());
                        return item;
                    })
                    .collect(Collectors.toList());
            resp.setData(items);
            resp.setSuccess(true);
        } catch (Exception e) {
            resp.setSuccess(false);
        }
        return resp;
    }

    @Override
    public UserDtos.UphotoDeleteResponse delete(UserDtos.UphotoDeleteRequest request) {
        UserDtos.UphotoDeleteResponse resp = new UserDtos.UphotoDeleteResponse();
        try {
            // 检查积分
            Integer currentPoint = userMapper.findPointByUid(request.getUid());
            if (currentPoint < Math.abs(request.getPointset())) {
                resp.setSuccess(false);
                resp.setPoint_remain(currentPoint);
                return resp;
            }

            // 检查作品是否存在且属于该用户
            Uphoto existing = uphotoMapper.findById(request.getUpthoto_id());
            if (existing == null || !existing.getUid().equals(request.getUid())) {
                resp.setSuccess(false);
                return resp;
            }

            // 删除作品
            uphotoMapper.deleteById(request.getUpthoto_id());

            // 更新积分
            userMapper.updatePoint(request.getUid(), request.getPointset());
            Integer newPoint = userMapper.findPointByUid(request.getUid());

            resp.setUid(request.getUid());
            resp.setUpthoto_id(request.getUpthoto_id());
            resp.setSuccess(true);
            resp.setPoint_remain(newPoint);
        } catch (Exception e) {
            resp.setSuccess(false);
        }
        return resp;
    }

    @Override
    public UserDtos.UphotoGetResponse get(UserDtos.UphotoGetRequest request) {
        UserDtos.UphotoGetResponse resp = new UserDtos.UphotoGetResponse();
        try {
            Uphoto photo = uphotoMapper.findById(request.getUpthoto_id());
            if (photo == null) {
                resp.setSuccess(false);
                return resp;
            }

            if (request.getUid() == null || !request.getUid().equals(photo.getUid())) {
                if (photo.getUphotoStatus() != 1 || photo.getUphotoReviewStatus() != 2) {
                    resp.setSuccess(false);
                    return resp;
                }
            }

            User user = userMapper.findByUid(photo.getUid());
            
            resp.setUid(photo.getUid());
            resp.setUsername(user != null ? user.getUsername() : "未知用户");
            resp.setAvatar(user != null ? user.getAvatar() : null);
            resp.setUphoto(photo.getUphotoPath());
            resp.setUphoto_title(photo.getUphotoTitle());
            resp.setUphoto_description(photo.getUphotoDescription());
            resp.setActivity_id(photo.getActivityId());
            
            if (photo.getActivityId() != null && !photo.getActivityId().isEmpty()) {
                try {
                    Long activityId = Long.parseLong(photo.getActivityId());
                    Activity activity = activityMapper.findById(activityId);
                    if (activity != null) {
                        resp.setActivity_name(activity.getActivityName());
                    }
                } catch (NumberFormatException e) {
                }
            }
            
            resp.setUphoto_status(photo.getUphotoStatus());
            resp.setUphoto_label_0(photo.getUphotoLabel0());
            resp.setUphoto_label_1(photo.getUphotoLabel1());
            resp.setUphoto_label_2(photo.getUphotoLabel2());
            resp.setUphoto_label_3(photo.getUphotoLabel3());
            resp.setUphoto_label_4(photo.getUphotoLabel4());
            resp.setUphoto_label_5(photo.getUphotoLabel5());
            resp.setUphoto_label_6(photo.getUphotoLabel6());
            resp.setUphoto_label_7(photo.getUphotoLabel7());
            resp.setUphoto_label_8(photo.getUphotoLabel8());
            resp.setUphoto_label_9(photo.getUphotoLabel9());
            
            int likeCount = uphotoDetailMapper.countLikesByUphotoId(request.getUpthoto_id());
            List<UphotoDetail> comments = uphotoDetailMapper.findCommentsByUphotoId(request.getUpthoto_id());
            
            resp.setUphoto_like_number(likeCount);
            resp.setUphoto_comment_number(comments.size());
            
            if (request.getUid() != null && request.getUid() > 0) {
                UphotoDetail detail = uphotoDetailMapper.findByUphotoIdAndUid(request.getUpthoto_id(), request.getUid());
                resp.setLiked(detail != null && detail.getUphotoLike() != null && detail.getUphotoLike() == 1);
            } else {
                resp.setLiked(false);
            }
            
            resp.setCreated_at(photo.getCreatedAt());
            resp.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
        }
        return resp;
    }

    @Override
    public List<Uphoto> getPublicPhotos() {
        return uphotoMapper.findPublicPhotos();
    }

    @Override
    public List<Uphoto> getPublicPhotosByLabel(Integer labelIndex, Integer labelValue) {
        return uphotoMapper.findPublicPhotosByLabel(labelIndex, labelValue);
    }

    @Override
    public List<Uphoto> getPhotosByActivityId(Long activityId) {
        List<Uphoto> photos = uphotoMapper.findByActivityId(String.valueOf(activityId));
        return photos.stream()
                .filter(p -> p.getUphotoStatus() != null && p.getUphotoStatus() == 1 
                        && p.getUphotoReviewStatus() != null && p.getUphotoReviewStatus() == 2)
                .collect(Collectors.toList());
    }
}