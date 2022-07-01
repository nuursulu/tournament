package com.example.mycli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @JsonIgnore
    @ManyToOne()
    @JoinTable(
            name = "tourney_round",
            joinColumns = @JoinColumn(name = "round_id"),
            inverseJoinColumns = @JoinColumn(name = "tourney_id"))
    private Tourney tourney;

    @JsonIgnore
    @OneToMany(mappedBy = "rounds")
    private List<Match> matches= new ArrayList<>();

    public void addMatch(Match match) {
        matches.add(match);
        match.setRounds(this);
    }



}
