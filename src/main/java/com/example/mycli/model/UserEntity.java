package com.example.mycli.model;

import com.example.mycli.Alogic.entity.Tourney;
import lombok.*;
import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    private List<Tourney> tourneyList;



}
