package com.example.galgame_vote.pojo.voting;

import com.example.galgame_vote.pojo.VotingList;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "tb_group_voting")
@NoArgsConstructor
@AllArgsConstructor
public class GroupVoting {
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
    private Integer maxVotingCount;

    @OneToMany(mappedBy = "groupVoting")
    private List<VotingOption> votingOptionList;

    @ManyToOne
    @JoinColumn(name = "voting_list_id", nullable = true)
    private VotingList votingList; // 投票列表ID
}
