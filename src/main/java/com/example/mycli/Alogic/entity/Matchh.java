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

    private LocalDateTime startTime=null;
    private LocalDateTime finishTime=null;

    private Long firstParticipantScore=null;
    private Long secondParticipantScore=null;


    private Long firstParticipantId=null;

    private Long secondParticipantId=null;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private Round round=null;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tourney_id")
    private Tourney tourney=null;


}
