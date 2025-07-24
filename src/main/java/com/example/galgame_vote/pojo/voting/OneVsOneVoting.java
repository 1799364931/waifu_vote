package com.example.galgame_vote.pojo.voting;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_one_vs_one_voting")

public class OneVsOneVoting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",unique = true,nullable =false)
    private UUID id;

    @Column(name = "start_time",nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time",nullable = false)
    @Future
    private Timestamp endTime;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "max_voting_count")
    @Min(0)
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "first_option")
    private VotingOption firstVotingOption;

    @ManyToOne
    @JoinColumn(name = "second_option")
    private VotingOption secondVotingOption;


}
