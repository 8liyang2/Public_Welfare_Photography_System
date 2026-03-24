package com.example.pwps.mapper;

import com.example.pwps.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    List<Announcement> findActive();
    Announcement findById(Long id);
    void insert(Announcement announcement);
    void update(Announcement announcement);
    void delete(Long id);
}
