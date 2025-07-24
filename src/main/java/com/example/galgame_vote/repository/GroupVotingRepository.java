package com.example.galgame_vote.repository;

import com.example.galgame_vote.pojo.voting.GroupVoting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupVotingRepository extends JpaRepository<GroupVoting, UUID>{
}
