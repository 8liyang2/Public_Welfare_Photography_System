package com.example.pwps.controller;

import com.example.pwps.dto.UserDtos;
import com.example.pwps.entity.User;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AdminUserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/user/ban")
    public UserDtos.BanResponse ban(@RequestBody UserDtos.BanRequest request) {
        UserDtos.BanResponse resp = new UserDtos.BanResponse();
        if (request == null || request.getUid() == null || request.getPermission() == null || request.getPermission() != 0 || request.getBan_uid() == null) {
            resp.setSuccess(false);
            return resp;
        }
        User target = userMapper.findByUid(request.getBan_uid());
        if (target == null) {
            resp.setSuccess(false);
            return resp;
        }
        target.setPermission((byte) 2);
        userMapper.update(target);
        resp.setBan_uid(request.getBan_uid());
        resp.setSuccess(true);
        return resp;
    }

    @PostMapping("/user/permission")
    public UserDtos.PermissionResponse changePermission(@RequestBody UserDtos.PermissionRequest request) {
        UserDtos.PermissionResponse resp = new UserDtos.PermissionResponse();
        if (request == null || request.getUid() == null || request.getPermission() == null || request.getPermission() != 0 || request.getTarget_uid() == null || request.getTarget_perm() == null) {
            resp.setSuccess(false);
            return resp;
        }
        User target = userMapper.findByUid(request.getTarget_uid());
        if (target == null) {
            resp.setSuccess(false);
            return resp;
        }
        target.setPermission(request.getTarget_perm().byteValue());
        userMapper.update(target);
        resp.setTarget_uid(request.getTarget_uid());
        resp.setSuccess(true);
        return resp;
    }

    @GetMapping("/all_user/search")
    public UserDtos.AllUserSearchResponse searchAllUsers(@RequestParam("uid") Long uid, @RequestParam("permission") Integer permission) {
        UserDtos.AllUserSearchResponse resp = new UserDtos.AllUserSearchResponse();
        if (uid == null || permission == null || permission != 0) {
            resp.setSuccess(false);
            return resp;
        }
        List<User> users = userMapper.findAll();
        resp.setData(users.stream().map(u -> {
            UserDtos.UserItem item = new UserDtos.UserItem();
            item.setUid(u.getUid());
            item.setLoginname(u.getLoginname());
            item.setUsername(u.getUsername());
            item.setPermission(u.getPermission() == null ? null : u.getPermission().intValue());
            item.setPoint(u.getPoint());
            item.setCreated_at(u.getCreatedAt());
            return item;
        }).collect(Collectors.toList()));
        resp.setSuccess(true);
        return resp;
    }
}
