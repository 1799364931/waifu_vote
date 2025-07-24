package com.example.galgame_vote.repository;


import com.example.galgame_vote.pojo.voting.VotingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VotingOptionRepository extends JpaRepository<VotingOption, UUID> {

    // 这里可以添加自定义查询方法，例如根据投票ID查找选项等
    // 其他相关方法可以在这里添加

}
