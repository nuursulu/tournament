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
@Table(name = "tourney")
public class Tourney {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;

    @JsonIgnore
    @ManyToMany(mappedBy = "tourneyList")
    private List<UserEntity> participants =  new ArrayList<>();;

    @JsonIgnore
    @OneToMany(mappedBy = "tourney")
    private List<Round> rounds =  new ArrayList<>();;

}

