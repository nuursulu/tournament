package com.example.mycli.Alogic.entity;

import com.example.mycli.model.UserEntity;
import com.example.mycli.model.entity.Match;
import com.example.mycli.model.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "part")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
//    private UserEntity userEntity;
    private String nickname;

    @JsonIgnore
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", nullable = false)
    @JsonIgnore
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournament_id", nullable = false)
    @JsonIgnore
    private Tournament tournament;
}
