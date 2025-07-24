package com.example.galgame_vote.controller;

import com.example.galgame_vote.pojo.dto.CreateVotingRequestDto;
import com.example.galgame_vote.pojo.dto.VoteRequestDto;
import com.example.galgame_vote.pojo.dto.VotingDetailDto;
import com.example.galgame_vote.pojo.dto.VotingDto;
import com.example.galgame_vote.pojo.util.ResponseMessage;
import com.example.galgame_vote.service.VotingService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("voting")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    // 创建分组投票
    @PostMapping("/createGroupVoting")
    public ResponseMessage<UUID> createGroupVoting(@RequestBody CreateVotingRequestDto createVotingRequestDto) {
        // 这里应该调用服务层的逻辑来创建分组投票
        // 返回一个响应消息，包含新创建的投票ID
        return votingService.createGroupVoting(createVotingRequestDto);
    }

    // 投票
    @PostMapping("/vote")
    public ResponseMessage<Boolean> vote(@RequestBody VoteRequestDto voteRequestDto) {
        // 这里应该调用服务层的逻辑来处理投票
        // 返回一个响应消息，表示投票是否成功
        return votingService.vote(voteRequestDto);
    }

    // 获取投票摘要列表
    @GetMapping("/summaryList")
    public ResponseMessage<List<VotingDto>> getVotingSummaryList() {
        // 这里应该调用服务层的逻辑来获取投票摘要列表
        // 返回一个响应消息，包含投票摘要列表
        return votingService.getVotingSummaryList();
    }

    // 获取投票详情
    @GetMapping("/detail/{votingId}")
    public ResponseMessage<VotingDetailDto> getVotingDetail(@PathVariable UUID votingId) {
        // 这里应该调用服务层的逻辑来获取投票详情
        // 返回一个响应消息，包含投票详情
        return votingService.getVotingDetail(votingId);
    }

    // 其他相关方法可以在这里添加
}
