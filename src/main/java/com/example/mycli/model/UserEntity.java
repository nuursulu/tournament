package com.example.mycli.model;

import com.example.mycli.Alogic.entity.Tourney;
import com.example.mycli.model.entity.Tournament;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    private RoleEntity roleEntity;

}
