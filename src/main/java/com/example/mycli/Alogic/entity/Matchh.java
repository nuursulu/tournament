package com.example.mycli.Alogic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "matchh")
public class Matchh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    private Long firstParticipantScore;
    private Long secondParticipantScore;

    @JsonIgnore
    private Long firstParticipantId;
    @JsonIgnore
    private Long secondParticipantId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private Round round;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tourney_id")
    private Tourney tourney;

}