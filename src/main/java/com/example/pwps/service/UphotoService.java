package com.example.pwps.service;

import com.example.pwps.dto.UserDtos;
import com.example.pwps.entity.Uphoto;

import java.util.List;

public interface UphotoService {
    UserDtos.UphotoUploadResponse upload(UserDtos.UphotoUploadRequest request);
    UserDtos.UphotoManageResponse manage(UserDtos.UphotoManageRequest request);
    UserDtos.UphotoSearchResponse search(UserDtos.UphotoSearchRequest request);
    UserDtos.UphotoDeleteResponse delete(UserDtos.UphotoDeleteRequest request);
    UserDtos.UphotoGetResponse get(UserDtos.UphotoGetRequest request);
    List<Uphoto> getPublicPhotos();
    List<Uphoto> getPublicPhotosByLabel(Integer labelIndex, Integer labelValue);
    List<Uphoto> getPhotosByActivityId(Long activityId);
}