package com.example.pwps.mapper;

import com.example.pwps.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByLoginname(@Param("loginname") String loginname);

    int insert(User user);

    User findByUid(@Param("uid") Long uid);

    int update(User user);

    int deleteByUid(@Param("uid") Long uid);

    int updatePassword(@Param("uid") Long uid, @Param("password") String password);

    Integer findPointByUid(@Param("uid") Long uid);

    int updatePoint(@Param("uid") Long uid, @Param("pointChange") Integer pointChange);

    List<User> findAll();
}

