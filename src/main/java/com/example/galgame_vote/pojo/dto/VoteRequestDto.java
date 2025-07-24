package com.example.galgame_vote.pojo.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class VoteRequestDto {
    private UUID votingId; // 投票ID
    private UUID userId; // 用户ID
    private List<UUID> votedOptionIds; // 已投票的选项ID列表
    private Timestamp voteTime; // 投票时间
//    private String ipAddress; // 用户IP地址
//    private String userAgent; // 用户代理信息

}
