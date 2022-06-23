package com.example.mycli.Alogic.entity;

import com.example.mycli.model.UserEntity;
import com.example.mycli.model.entity.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private boolean onHold = true;

    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tourney")
    private List<UserEntity> participants;






}

