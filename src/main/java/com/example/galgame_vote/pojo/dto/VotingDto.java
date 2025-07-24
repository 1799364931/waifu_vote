package com.example.galgame_vote.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
public class VotingDto {
    private UUID votingId; // 投票ID
    private String title; // 投票标题
    private String description; // 投票描述
    private Timestamp startTime; // 投票开始时间
    private Timestamp endTime; // 投票结束时间
    private Integer maxVotes; // 每个用户最多投票数

    // 其他相关字段可以根据需要添加
}
