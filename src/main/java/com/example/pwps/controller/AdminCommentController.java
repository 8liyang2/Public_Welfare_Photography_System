package com.example.pwps.controller;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.entity.ActivityDetail;
import com.example.pwps.entity.UphotoDetail;
import com.example.pwps.mapper.ActivityDetailMapper;
import com.example.pwps.mapper.UphotoDetailMapper;
import com.example.pwps.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/comment")
public class AdminCommentController {

    private final ActivityDetailMapper activityDetailMapper;
    private final UphotoDetailMapper uphotoDetailMapper;
    private final UserMapper userMapper;

    public AdminCommentController(ActivityDetailMapper activityDetailMapper, UphotoDetailMapper uphotoDetailMapper, UserMapper userMapper) {
        this.activityDetailMapper = activityDetailMapper;
        this.uphotoDetailMapper = uphotoDetailMapper;
        this.userMapper = userMapper;
    }

    // 获取活动评论列表
    @GetMapping("/activity")
    public ActivityDtos.CommentListResponse getActivityComments(
            @RequestParam("uid") Long uid,
            @RequestParam("permission") Integer permission,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "50") Integer pageSize,
            @RequestParam(value = "keyword", required = false) String keyword) {
        ActivityDtos.CommentListResponse resp = new ActivityDtos.CommentListResponse();
        if (permission != 0) {
            resp.setSuccess(false);
            return resp;
        }

        int offset = (page - 1) * pageSize;
        List<ActivityDetail> comments;
        int total;

        if (keyword != null && !keyword.isEmpty()) {
            comments = activityDetailMapper.searchComments(keyword, offset, pageSize);
            total = activityDetailMapper.countSearchComments(keyword);
        } else {
            comments = activityDetailMapper.findAllComments(offset, pageSize);
            total = activityDetailMapper.countAllComments();
        }

        resp.setData(comments.stream().map(comment -> {
            ActivityDtos.CommentItem item = new ActivityDtos.CommentItem();
            item.setId(comment.getId());
            item.setActivity_id(comment.getActivityId());
            item.setUid(comment.getUid());
            item.setComment_detail(comment.getActivityCommentDetail());
            // 获取用户名
            String username = userMapper.findByUid(comment.getUid()) != null ? userMapper.findByUid(comment.getUid()).getUsername() : "用户" + comment.getUid();
            item.setUsername(username);
            return item;
        }).collect(Collectors.toList()));
        resp.setTotal(total);
        resp.setPage(page);
        resp.setPageSize(pageSize);
        resp.setSuccess(true);
        return resp;
    }

    // 获取作品评论列表
    @GetMapping("/uphoto")
    public ActivityDtos.CommentListResponse getUphotoComments(
            @RequestParam("uid") Long uid,
            @RequestParam("permission") Integer permission,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "50") Integer pageSize,
            @RequestParam(value = "keyword", required = false) String keyword) {
        ActivityDtos.CommentListResponse resp = new ActivityDtos.CommentListResponse();
        if (permission != 0) {
            resp.setSuccess(false);
            return resp;
        }

        int offset = (page - 1) * pageSize;
        List<UphotoDetail> comments;
        int total;

        if (keyword != null && !keyword.isEmpty()) {
            comments = uphotoDetailMapper.searchComments(keyword, offset, pageSize);
            total = uphotoDetailMapper.countSearchComments(keyword);
        } else {
            comments = uphotoDetailMapper.findAllComments(offset, pageSize);
            total = uphotoDetailMapper.countAllComments();
        }

        resp.setData(comments.stream().map(comment -> {
            ActivityDtos.CommentItem item = new ActivityDtos.CommentItem();
            item.setId(comment.getId());
            item.setUpthoto_id(comment.getUpthotoId());
            item.setUid(comment.getUid());
            item.setComment_detail(comment.getUphotoCommentDetail());
            // 获取用户名
            String username = userMapper.findByUid(comment.getUid()) != null ? userMapper.findByUid(comment.getUid()).getUsername() : "用户" + comment.getUid();
            item.setUsername(username);
            return item;
        }).collect(Collectors.toList()));
        resp.setTotal(total);
        resp.setPage(page);
        resp.setPageSize(pageSize);
        resp.setSuccess(true);
        return resp;
    }

    // 删除活动评论
    @PostMapping("/activity/delete")
    public ActivityDtos.CommentDeleteResponse deleteActivityComment(@RequestBody ActivityDtos.CommentDeleteRequest request) {
        ActivityDtos.CommentDeleteResponse resp = new ActivityDtos.CommentDeleteResponse();
        if (request.getPermission() != 0) {
            resp.setSuccess(false);
            return resp;
        }

        try {
            activityDetailMapper.deleteById(request.getId());
            resp.setSuccess(true);
            resp.setId(request.getId());
        } catch (Exception e) {
            resp.setSuccess(false);
        }
        return resp;
    }

    // 删除作品评论
    @PostMapping("/uphoto/delete")
    public ActivityDtos.CommentDeleteResponse deleteUphotoComment(@RequestBody ActivityDtos.CommentDeleteRequest request) {
        ActivityDtos.CommentDeleteResponse resp = new ActivityDtos.CommentDeleteResponse();
        if (request.getPermission() != 0) {
            resp.setSuccess(false);
            return resp;
        }

        try {
            uphotoDetailMapper.deleteById(request.getId());
            resp.setSuccess(true);
            resp.setId(request.getId());
        } catch (Exception e) {
            resp.setSuccess(false);
        }
        return resp;
    }
}