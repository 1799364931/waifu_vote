package com.example.galgame_vote.pojo.voting;


import com.example.galgame_vote.pojo.User;
import com.example.galgame_vote.pojo.voting.GroupVoting;
import com.example.galgame_vote.pojo.voting.VotingOption;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_voting_record",indexes = {
        @Index(name = "idx_voting_record_user", columnList = "voter_id"),
        @Index(name = "idx_voting_record_voting", columnList = "voting_id"),
        @Index(name = "idx_voting_record_option", columnList = "voting_option_id")})
public class VotingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",unique = true,nullable =false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    private GroupVoting groupVoting;

    @ManyToOne
    @JoinColumn(name = "voting_option_id")
    private VotingOption votingOption;

    @Column(name = "vote_time")
    private Timestamp voteTime;
}
