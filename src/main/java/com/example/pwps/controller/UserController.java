package com.example.pwps.controller;

import com.example.pwps.dto.UserDtos.UserLoginRequest;
import com.example.pwps.dto.UserDtos.UserLoginResponse;
import com.example.pwps.dto.UserDtos.UserRegisterRequest;
import com.example.pwps.dto.UserDtos.UserRegisterResponse;
import com.example.pwps.dto.UserDtos.UserUpdateRequest;
import com.example.pwps.dto.UserDtos.UserUpdateResponse;
import com.example.pwps.dto.UserDtos.UserDeleteRequest;
import com.example.pwps.dto.UserDtos.UserDeleteResponse;
import com.example.pwps.dto.UserDtos.UserInfoResponse;
import com.example.pwps.dto.UserDtos.PasswordChangeRequest;
import com.example.pwps.dto.UserDtos.PasswordChangeResponse;
import com.example.pwps.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PutMapping("/update")
    public UserUpdateResponse update(@RequestBody UserUpdateRequest request) {
        return userService.update(request);
    }

    @DeleteMapping("/delete")
    public UserDeleteResponse delete(@RequestBody UserDeleteRequest request) {
        return userService.delete(request);
    }

    @GetMapping("/info")
    public UserInfoResponse info(@RequestParam("uid") Long uid) {
        return userService.info(uid);
    }

    @PostMapping("/password/change")
    public PasswordChangeResponse changePassword(@RequestBody PasswordChangeRequest request) {
        return userService.changePassword(request);
    }
}

