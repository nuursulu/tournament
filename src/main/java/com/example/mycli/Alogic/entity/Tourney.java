package com.example.mycli.Alogic.entity;

import com.example.mycli.model.UserEntity;
import com.example.mycli.model.entity.Match;
import com.example.mycli.model.entity.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tourney")
public class Tourney {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    private String status;

    @JsonIgnore
    @ManyToMany
    private List<UserEntity> participants;

}

