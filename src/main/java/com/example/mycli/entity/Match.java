package com.example.mycli.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "matchs")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "startTime")
    private LocalDateTime startTime;
    @Column(name = "finishTime")
    private LocalDateTime finishTime;

    @Column(name = "firstScore")
    private Long firstParticipantScore;
    @Column(name = "secondScore")
    private Long secondParticipantScore;

    @JsonIgnore
    @Column(name = "firstId")
    private Long firstParticipantId;

    @JsonIgnore
    @Column(name = "secondId")
    private Long secondParticipantId;

    @JsonIgnore
    @ManyToOne()
    @JoinTable(
            name = "round_match",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "round_id"))
    private Round rounds;



}