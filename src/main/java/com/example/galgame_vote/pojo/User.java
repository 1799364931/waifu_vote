package com.example.galgame_vote.pojo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name="tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",unique = true,nullable =false)
    private UUID id;

    @Column(name = "account",nullable = false)
    private String account;
}
