package com.example.galgame_vote.pojo.dto;


import com.example.galgame_vote.pojo.voting.VotingOption;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CreateVotingRequestDto {
    private String title; // 投票标题
    private String description; // 投票描述
    private String type; // 投票类型（分组投票或1v1投票）
    private List<VotingOption> votingOptions; // 投票选项列表
    private Timestamp startTime; // 投票开始时间
    private Timestamp endTime; // 投票结束时间
    private Integer maxVotingCount; // 最大投票次数
}
