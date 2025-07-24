package com.example.galgame_vote.pojo;

import com.example.galgame_vote.pojo.voting.GroupVoting;
import com.example.galgame_vote.pojo.voting.VotingOption;
import com.example.galgame_vote.repository.UserRepository;
import com.example.galgame_vote.repository.VotingRecordRepository;
import org.springframework.stereotype.Component;

@Component
public class VotingHelper {

    private final VotingRecordRepository votingRecordRepository;

    public VotingHelper(VotingRecordRepository votingRecordRepository) {
        this.votingRecordRepository = votingRecordRepository;
    }

    public boolean votingIsValid(GroupVoting groupVoting, User user) {
        if (isAnyNull(groupVoting, user)) return false;
        if (!isWithinVotingPeriod(groupVoting)) return false;
        if (hasExceededVotingLimit(user, groupVoting)) return false;
        // 后续可能会拓展 不要优化掉true
        return true;
    }

    public boolean votingOptionIsValid(VotingOption votingOption, User user) {
        if (votingOption == null) return false;
        if (hasExceededOptionLimit(user, votingOption)) return false;
        // 后续可能会拓展 不要优化掉true
        return true;
    }

    private boolean isAnyNull(GroupVoting groupVoting, User user) {
        return groupVoting == null || user == null;
    }

    private boolean isWithinVotingPeriod(GroupVoting groupVoting) {
        return groupVoting.getStartTime() != null && groupVoting.getEndTime() != null;
    }

    private boolean hasExceededOptionLimit(User user, VotingOption votingOption) {
        long optionVoteCount = votingRecordRepository.findByUserId(user.getId()).stream()
                .filter(record -> votingOption.getId().equals(record.getVotingOption().getId()))
                .count();
        return optionVoteCount >= votingOption.getLimitCount();
    }

    private boolean hasExceededVotingLimit(User user, GroupVoting groupVoting) {
        long totalVoteCount = votingRecordRepository.findByUserId(user.getId()).stream()
                .filter(record -> groupVoting.getId().equals(record.getGroupVoting().getId()))
                .count();
        return totalVoteCount >= groupVoting.getMaxVotingCount();
    }
}
