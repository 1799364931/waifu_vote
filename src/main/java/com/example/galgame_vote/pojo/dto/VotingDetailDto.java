package com.example.galgame_vote.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class VotingDetail {
    VotingDto votingDto;
    List<VotingOptionDto> votingOptionDtos;
}
