package com.example.galgame_vote.repository;

import com.example.galgame_vote.pojo.voting.VotingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VotingRecordRepository extends JpaRepository<VotingRecord, Long> {

    // 这里可以添加自定义查询方法，例如根据投票ID查找记录等
    // public List<VotingRecord> findByVotingId(Long votingId);

    // 查询用户的投票记录
    public List<VotingRecord> findByUserId(UUID userId);

    // 其他相关方法可以在这里添加
}
