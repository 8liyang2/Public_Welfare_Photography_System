package com.example.pwps.service.impl;

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
import com.example.pwps.entity.User;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.UserService;
import com.example.pwps.util.Md5Utils;
import com.example.pwps.util.PictureStorageUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        UserRegisterResponse resp = new UserRegisterResponse();

        if (request == null || !StringUtils.hasText(request.getLoginname())) {
            resp.setSuccess(false);
            resp.setLoginname_exists(0);
            resp.setPassword_exists(0);
            return resp;
        }

        User existing = userMapper.findByLoginname(request.getLoginname());
        resp.setLoginname_exists(existing != null ? 1 : 0);

        boolean passwordSame = request.getPassword() != null
                && request.getPassword().equals(request.getPassword1());
        resp.setPassword_exists(passwordSame ? 1 : 0);

        if (resp.getLoginname_exists() == 1 || !passwordSame) {
            resp.setSuccess(false);
            return resp;
        }

        try {
            User user = new User();
            user.setLoginname(request.getLoginname());
            user.setPassword(Md5Utils.md5(request.getPassword()));
            user.setUsername("用户" + System.currentTimeMillis());
            user.setPermission((byte) 1);
            user.setPoint(0);
            user.setActivityCreated(0);
            user.setUphotoCreated(0);
            if (StringUtils.hasText(request.getAvatar())) {
                String avatarStr = request.getAvatar().trim();
                // 若传入的是 Base64，则落盘并在数据库保存相对路径，避免 varchar(255) 装不下 Base64
                if (avatarStr.startsWith("data:image") || avatarStr.length() > 255) {
                    String fileName = "uid_tmp_" + System.currentTimeMillis() + ".png";
                    String savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                            PictureStorageUtil.Category.AVATAR,
                            avatarStr,
                            fileName
                    );
                    user.setAvatar(savedPath);
                } else {
                    // 若是普通字符串（例如相对路径），直接存
                    user.setAvatar(avatarStr);
                }
            } else {
                user.setAvatar(null);
            }

            userMapper.insert(user);

            resp.setUser_id(user.getUid());
            resp.setSuccess(true);
        } catch (Exception e) {
            resp.setSuccess(false);
        }
        return resp;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse resp = new UserLoginResponse();
        User user = userMapper.findByLoginname(request.getLoginname());
        if (user == null) {
            resp.setSuccess(false);
            return resp;
        }
        String encrypted = Md5Utils.md5(request.getPassword());
        if (!encrypted.equals(user.getPassword())) {
            resp.setSuccess(false);
            return resp;
        }
        resp.setUid(user.getUid());
        resp.setPermission(user.getPermission().intValue());
        resp.setSuccess(true);
        return resp;
    }

    @Override
    public UserUpdateResponse update(UserUpdateRequest request) {
        UserUpdateResponse resp = new UserUpdateResponse();
        if (request == null || request.getUid() == null) {
            resp.setSuccess(false);
            return resp;
        }
        User user = userMapper.findByUid(request.getUid());
        if (user == null) {
            resp.setSuccess(false);
            return resp;
        }

        if (StringUtils.hasText(request.getLoginname())) {
            user.setLoginname(request.getLoginname());
        }
        if (StringUtils.hasText(request.getUsername())) {
            user.setUsername(request.getUsername());
        } else {
            user.setUsername("用户" + user.getUid());
        }
        user.setEmail(request.getEmail());
        user.setPersonalProfile(request.getPersonal_profile());
        // 管理员权限（0）不允许被修改
        if (request.getPermission() != null && user.getPermission() != null && user.getPermission() != 0) {
            user.setPermission(request.getPermission().byteValue());
        }
        if (request.getBirthday() != null) {
            user.setBirthday(request.getBirthday());
        }

        // 处理头像 Base64，落盘到 picture/avatar 并在数据库保存相对路径
        if (StringUtils.hasText(request.getAvatar())) {
            try {
                String fileName = "uid_" + user.getUid() + ".png";
                String savedPath = PictureStorageUtil.saveBase64ToProjectPictureDir(
                        PictureStorageUtil.Category.AVATAR,
                        request.getAvatar(),
                        fileName
                );
                user.setAvatar(savedPath);
            } catch (Exception e) {
                // 若头像保存失败，不影响其他字段更新
            }
        }

        userMapper.update(user);
        resp.setUpdated_at(new Date());
        resp.setSuccess(true);
        return resp;
    }

    @Override
    public UserDeleteResponse delete(UserDeleteRequest request) {
        UserDeleteResponse resp = new UserDeleteResponse();
        if (request == null || request.getUid() == null) {
            resp.setSuccess(false);
            return resp;
        }
        // 管理员不允许注销
        if (request.getPermission() != null && request.getPermission() == 0) {
            resp.setSuccess(false);
            return resp;
        }
        int rows = userMapper.deleteByUid(request.getUid());
        resp.setUpdated_at(new Date());
        resp.setSuccess(rows > 0);
        return resp;
    }

    @Override
    public UserInfoResponse info(Long uid) {
        UserInfoResponse resp = new UserInfoResponse();
        if (uid == null) {
            resp.setSuccess(false);
            return resp;
        }
        User user = userMapper.findByUid(uid);
        if (user == null) {
            resp.setSuccess(false);
            return resp;
        }
        resp.setUid(user.getUid());
        resp.setLoginname(user.getLoginname());
        resp.setUsername(user.getUsername());
        resp.setAvatar(user.getAvatar());
        resp.setEmail(user.getEmail());
        resp.setPersonal_profile(user.getPersonalProfile());
        resp.setPermission(user.getPermission() == null ? null : user.getPermission().intValue());
        resp.setBirthday(user.getBirthday());
        resp.setPoint(user.getPoint());
        resp.setSuccess(true);
        return resp;
    }

    @Override
    public PasswordChangeResponse changePassword(PasswordChangeRequest request) {
        PasswordChangeResponse resp = new PasswordChangeResponse();
        if (request == null || request.getUid() == null || !StringUtils.hasText(request.getOldPassword()) || !StringUtils.hasText(request.getNewPassword())) {
            resp.setSuccess(false);
            resp.setMessage("参数不完整");
            return resp;
        }
        User user = userMapper.findByUid(request.getUid());
        if (user == null) {
            resp.setSuccess(false);
            resp.setMessage("用户不存在");
            return resp;
        }
        String oldEncrypted = Md5Utils.md5(request.getOldPassword());
        if (!oldEncrypted.equals(user.getPassword())) {
            resp.setSuccess(false);
            resp.setMessage("当前密码错误");
            return resp;
        }
        String newEncrypted = Md5Utils.md5(request.getNewPassword());
        userMapper.updatePassword(user.getUid(), newEncrypted);
        resp.setSuccess(true);
        resp.setMessage("密码修改成功");
        return resp;
    }
}

