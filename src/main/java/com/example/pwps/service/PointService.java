package com.example.pwps.service;

/**
 * 积分规则（来自 SQL 建表要求）：
 * - 上传作品：-10，通过审核 +20，删除通过作品 -10
 * - 创建活动：-100，修改作品/活动：-5，删除活动：-300
 * - 获得点赞：+1（取消点赞 -1）
 * - 评论：+5（评论被删除 -8）
 *
 * 约束：积分不会小于 0；若某次扣除会导致 <0，则该请求不通过。
 */
public interface PointService {

    /**
     * 扣除积分（不允许扣到 0 以下），成功返回 true。
     */
    boolean spend(Long uid, int cost);

    /**
     * 增加积分（允许为负但会 clamp 到 >=0）。
     */
    void addOrClamp(Long uid, int delta);

    int getPoint(Long uid);
}

