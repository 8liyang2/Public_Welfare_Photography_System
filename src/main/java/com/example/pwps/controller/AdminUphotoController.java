package com.example.pwps.controller;

import com.example.pwps.dto.ActivityDtos;
import com.example.pwps.entity.Uphoto;
import com.example.pwps.entity.User;
import com.example.pwps.mapper.UphotoMapper;
import com.example.pwps.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/uphoto")
public class AdminUphotoController {

    private final UphotoMapper uphotoMapper;
    private final UserMapper userMapper;

    public AdminUphotoController(UphotoMapper uphotoMapper, UserMapper userMapper) {
        this.uphotoMapper = uphotoMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/review")
    public ActivityDtos.AdminUphotoReviewListResponse reviewList(@RequestParam("uid") Long uid,
                                                                 @RequestParam("permission") Integer permission) {
        ActivityDtos.AdminUphotoReviewListResponse resp = new ActivityDtos.AdminUphotoReviewListResponse();
        List<Uphoto> list = uphotoMapper.findReviewPending();
        resp.data = list.stream().map(p -> {
            ActivityDtos.AdminUphotoReviewListItem item = new ActivityDtos.AdminUphotoReviewListItem();
            item.upthoto_id = p.getUpthotoId();
            item.uid = p.getUid();
            item.uphoto = p.getUphotoPath();
            item.uphoto_title = p.getUphotoTitle();
            item.uphoto_description = p.getUphotoDescription();
            
            User user = userMapper.findByUid(p.getUid());
            if (user != null) {
                item.username = user.getUsername();
            }
            
            return item;
        }).collect(Collectors.toList());
        resp.success = true;
        return resp;
    }

    @GetMapping("/review/")
    public ActivityDtos.AdminUphotoReviewDecisionResponse reviewDecision(@RequestParam("upthoto_id") Long uphotoId,
                                                                          @RequestParam("uid") Long uid,
                                                                          @RequestParam("permission") Integer permission,
                                                                          @RequestParam("uphoto_review_status") Integer reviewStatus) {
        ActivityDtos.AdminUphotoReviewDecisionResponse resp = new ActivityDtos.AdminUphotoReviewDecisionResponse();
        if (uphotoId == null || reviewStatus == null) {
            resp.success = false;
            return resp;
        }
        uphotoMapper.updateReviewStatus(uphotoId, reviewStatus);
        resp.upthoto_id = uphotoId;
        resp.success = true;
        return resp;
    }
}
