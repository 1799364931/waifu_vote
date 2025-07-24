package com.example.galgame_vote.pojo;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_voting_option")
public class VotingOption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",unique = true,nullable =false)
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "voting_count")
    @Min(0)
    private Integer count;

    @Column(name = "img_url")
    private String url;

    @Column(name = "limit_count")
    @Min(0)
    private Integer limitCount;

    @ManyToOne
    @JoinColumn(name = "voting_id")
    private GroupVoting groupVoting;
}
