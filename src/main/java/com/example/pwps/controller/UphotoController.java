package com.example.pwps.controller;

import com.example.pwps.dto.UserDtos;
import com.example.pwps.service.UphotoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{uid}/uphoto")
public class UphotoController {

    private final UphotoService uphotoService;

    public UphotoController(UphotoService uphotoService) {
        this.uphotoService = uphotoService;
    }

    // 作品上传接口
    @PostMapping("/upload")
    public UserDtos.UphotoUploadResponse upload(@PathVariable("uid") Long uid, @RequestBody UserDtos.UphotoUploadRequest request) {
        // 验证uid是否与请求中的uid一致
        if (!uid.equals(request.getUid())) {
            UserDtos.UphotoUploadResponse resp = new UserDtos.UphotoUploadResponse();
            resp.setSuccess(false);
            return resp;
        }
        return uphotoService.upload(request);
    }

    // 作品管理接口
    @PostMapping("/manage")
    public UserDtos.UphotoManageResponse manage(@PathVariable("uid") Long uid, @RequestBody UserDtos.UphotoManageRequest request) {
        // 验证uid是否与请求中的uid一致
        if (!uid.equals(request.getUid())) {
            UserDtos.UphotoManageResponse resp = new UserDtos.UphotoManageResponse();
            resp.setSuccess(false);
            return resp;
        }
        return uphotoService.manage(request);
    }

    // 作品搜索接口
    @PostMapping("/search")
    public UserDtos.UphotoSearchResponse search(@PathVariable("uid") Long uid, @RequestBody UserDtos.UphotoSearchRequest request) {
        if (request.getAsk_uid() == null) {
            request.setAsk_uid(uid);
        }
        return uphotoService.search(request);
    }

    // 作品删除接口
    @PostMapping("/delete")
    public UserDtos.UphotoDeleteResponse delete(@PathVariable("uid") Long uid, @RequestBody UserDtos.UphotoDeleteRequest request) {
        // 验证uid是否与请求中的uid一致
        if (!uid.equals(request.getUid())) {
            UserDtos.UphotoDeleteResponse resp = new UserDtos.UphotoDeleteResponse();
            resp.setSuccess(false);
            return resp;
        }
        return uphotoService.delete(request);
    }

    // 作品详情接口
    @PostMapping("/{upthoto_id}/get")
    public UserDtos.UphotoGetResponse get(@PathVariable("uid") Long uid, @PathVariable("upthoto_id") Long upthotoId, @RequestBody UserDtos.UphotoGetRequest request) {
        // 验证uid是否与请求中的uid一致
        if (!uid.equals(request.getUid())) {
            UserDtos.UphotoGetResponse resp = new UserDtos.UphotoGetResponse();
            resp.setSuccess(false);
            return resp;
        }
        request.setUpthoto_id(upthotoId);
        return uphotoService.get(request);
    }
}