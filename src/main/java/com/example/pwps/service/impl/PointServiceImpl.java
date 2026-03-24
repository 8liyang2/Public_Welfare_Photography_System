package com.example.pwps.service.impl;

import com.example.pwps.entity.User;
import com.example.pwps.mapper.UserMapper;
import com.example.pwps.service.PointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointServiceImpl implements PointService {

    private final UserMapper userMapper;

    public PointServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int getPoint(Long uid) {
        User user = userMapper.findByUid(uid);
        return user == null || user.getPoint() == null ? 0 : user.getPoint();
    }

    @Override
    @Transactional
    public boolean spend(Long uid, int cost) {
        if (uid == null) return false;
        if (cost <= 0) return true;
        User user = userMapper.findByUid(uid);
        if (user == null) return false;
        int point = user.getPoint() == null ? 0 : user.getPoint();
        if (point - cost < 0) return false;
        user.setPoint(point - cost);
        userMapper.update(user);
        return true;
    }

    @Override
    @Transactional
    public void addOrClamp(Long uid, int delta) {
        if (uid == null || delta == 0) return;
        User user = userMapper.findByUid(uid);
        if (user == null) return;
        int point = user.getPoint() == null ? 0 : user.getPoint();
        int next = point + delta;
        if (next < 0) next = 0;
        user.setPoint(next);
        userMapper.update(user);
    }
}

