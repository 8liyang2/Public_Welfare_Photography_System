package com.example.pwps.mapper;

import com.example.pwps.entity.UphotoDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UphotoDetailMapper {
    void insert(UphotoDetail uphotoDetail);
    void update(UphotoDetail uphotoDetail);
    UphotoDetail findByUphotoIdAndUid(@Param("upthotoId") Long upthotoId, @Param("uid") Long uid);
    List<UphotoDetail> findCommentsByUphotoId(@Param("upthotoId") Long upthotoId);
    List<UphotoDetail> findAllComments(@Param("offset") Integer offset, @Param("limit") Integer limit);
    List<UphotoDetail> searchComments(@Param("keyword") String keyword, @Param("offset") Integer offset, @Param("limit") Integer limit);
    int countAllComments();
    int countSearchComments(@Param("keyword") String keyword);
    int countLikesByUphotoId(@Param("upthotoId") Long upthotoId);
    void deleteById(@Param("id") Long id);
}
