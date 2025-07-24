package com.example.galgame_vote.pojo.voting;

public enum VotingType{
    GROUP_VOTING("分组投票"),
    ONE_ON_ONE_VOTING("1v1投票");

    private final String description;

    VotingType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
