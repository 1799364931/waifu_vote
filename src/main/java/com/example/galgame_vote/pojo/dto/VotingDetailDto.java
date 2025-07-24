package com.example.galgame_vote.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VotingDetailDto {
    VotingDto votingDto;
    List<VotingOptionDto> votingOptionDtos;
}
