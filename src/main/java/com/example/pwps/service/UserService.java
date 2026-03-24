package com.example.pwps.service;

import com.example.pwps.dto.UserDtos;
import com.example.pwps.dto.UserDtos.UserLoginRequest;
import com.example.pwps.dto.UserDtos.UserLoginResponse;
import com.example.pwps.dto.UserDtos.UserRegisterRequest;
import com.example.pwps.dto.UserDtos.UserRegisterResponse;
import com.example.pwps.dto.UserDtos.UserUpdateRequest;
import com.example.pwps.dto.UserDtos.UserUpdateResponse;
import com.example.pwps.dto.UserDtos.UserDeleteRequest;
import com.example.pwps.dto.UserDtos.UserDeleteResponse;
import com.example.pwps.dto.UserDtos.UserInfoResponse;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest request);

    UserLoginResponse login(UserLoginRequest request);

    UserUpdateResponse update(UserUpdateRequest request);

    UserDeleteResponse delete(UserDeleteRequest request);

    UserInfoResponse info(Long uid);

    UserDtos.PasswordChangeResponse changePassword(UserDtos.PasswordChangeRequest request);
}

