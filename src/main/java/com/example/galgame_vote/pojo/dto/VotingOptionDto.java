package com.example.galgame_vote.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class VotingOptionDto {
    private UUID optionId; // 选项ID
    private String description; // 选项名称
    private Integer voteCount; // 投票数量
    private Integer limitCount;
    private String url;


}
