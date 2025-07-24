package com.example.galgame_vote.service;

import com.example.galgame_vote.pojo.VotingHelper;
import com.example.galgame_vote.pojo.dto.*;
import com.example.galgame_vote.pojo.util.ResponseMessage;
import com.example.galgame_vote.pojo.voting.GroupVoting;
import com.example.galgame_vote.pojo.voting.VotingRecord;
import com.example.galgame_vote.repository.GroupVotingRepository;
import com.example.galgame_vote.repository.UserRepository;
import com.example.galgame_vote.repository.VotingOptionRepository;
import com.example.galgame_vote.repository.VotingRecordRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VotingService {

    private final GroupVotingRepository groupVotingRepository;
    private final VotingOptionRepository votingOptionRepository;
    private final VotingRecordRepository votingRecordRepository;
    private final UserRepository userRepository;
    private final VotingHelper votingHelper;

    public VotingService(GroupVotingRepository groupVotingRepository
            , VotingOptionRepository votingOptionRepository
            , VotingRecordRepository votingRecordRepository
            , UserRepository userRepository
            , VotingHelper votingHelper) {
        this.groupVotingRepository = groupVotingRepository;
        this.votingOptionRepository = votingOptionRepository;
        this.votingRecordRepository = votingRecordRepository;
        this.userRepository = userRepository;
        this.votingHelper = votingHelper;
    }

    // 创建分组投票
    public ResponseMessage<UUID> createGroupVoting(CreateVotingRequestDto createVotingRequestDto) {
        GroupVoting groupVoting = GroupVoting.builder()
                .id(UUID.randomUUID())
                .title(createVotingRequestDto.getTitle())
                .description(createVotingRequestDto.getDescription())
                .startTime(createVotingRequestDto.getStartTime())
                .endTime(createVotingRequestDto.getEndTime())
                .maxVotingCount(createVotingRequestDto.getMaxVotingCount())
                .build();
        groupVotingRepository.save(groupVoting);
        // 存储投票选项
        createVotingRequestDto.getVotingOptions().forEach(option -> {
            option.setGroupVoting(groupVoting);
            votingOptionRepository.save(option);
        });
        // 返回一个新创建的投票ID
        return ResponseMessage.success(groupVoting.getId(),"分组投票创建成功");
    }

    // 投票
    public ResponseMessage<Boolean> vote(VoteRequestDto voteRequestDto) {
        var groupVoting = groupVotingRepository.findById(voteRequestDto.getVotingId());
        var user = userRepository.findById(voteRequestDto.getUserId());
        if(!votingHelper.votingIsValid(groupVoting.get(), user.get())){
            return ResponseMessage.error(false, "投票无效或不符合条件", HttpStatus.BAD_REQUEST.value());
        }

        var votingOptionList = voteRequestDto.getVotedOptionIds().stream()
                .map(votingOptionRepository::findById)
                .filter(option -> option.isPresent() && votingHelper.votingOptionIsValid(option.get(), user.get())).toList();

        votingOptionList.forEach(option -> {
            VotingRecord votingRecord = new VotingRecord();
            votingRecord.setGroupVoting(groupVoting.get());
            votingRecord.setUser(user.get());
            votingRecord.setVotingOption(option.get());
            votingRecordRepository.save(votingRecord);
        });
        return ResponseMessage.success(true, "投票成功");
    }

    // 获取投票列表
    public ResponseMessage<List<VotingDto>> getVotingSummaryList() {
        List<GroupVoting> groupVotings = groupVotingRepository.findAll();
        List<VotingDto> votingDtos = groupVotings.stream()
                .map(voting -> new VotingDto(voting.getId(), voting.getTitle(), voting.getDescription(),
                        voting.getStartTime(), voting.getEndTime(), voting.getMaxVotingCount()))
                .toList();
        return ResponseMessage.success(votingDtos, "获取投票列表成功");
    }

    // 获取投票详情
    public ResponseMessage<VotingDetailDto> getVotingDetail(UUID votingId) {
        var groupVoting = groupVotingRepository.findById(votingId);
        if (groupVoting.isEmpty()) {
            return ResponseMessage.error(null, "投票不存在", HttpStatus.NOT_FOUND.value());
        }

        VotingDto votingDto = new VotingDto(
                groupVoting.get().getId(),
                groupVoting.get().getTitle(),
                groupVoting.get().getDescription(),
                groupVoting.get().getStartTime(),
                groupVoting.get().getEndTime(),
                groupVoting.get().getMaxVotingCount()
        );

        List<VotingOptionDto> votingOptionDtos = groupVoting.get().getVotingOptionList().stream()
                .map(option -> new VotingOptionDto(
                        option.getId(),
                        option.getDescription(),
                        option.getCount(),
                        option.getLimitCount(),
                        option.getUrl()
                )).toList();

        VotingDetailDto votingDetailDto = new VotingDetailDto(
                votingDto,
                votingOptionDtos
        );

        return ResponseMessage.success(votingDetailDto, "获取投票详情成功");

    }

}
