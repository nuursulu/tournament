package com.example.mycli.Alogic.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
