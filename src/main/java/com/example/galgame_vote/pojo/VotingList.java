package com.example.galgame_vote.pojo;

import com.example.galgame_vote.pojo.voting.GroupVoting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "tb_voting_list")
public class VotingList {
    // 投票列表实体类

    // 这里可以添加投票列表的属性，例如投票标题、描述等
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title; // 投票标题

    @Column(name = "description", nullable = false)
    private String description; // 投票描述

    @OneToMany(mappedBy = "votingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupVoting> groupVotings; // 关联的群组投票列表
}
