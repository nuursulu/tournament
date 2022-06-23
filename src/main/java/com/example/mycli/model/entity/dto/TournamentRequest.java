package com.example.mycli.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TournamentRequest {

    private Integer maxParticipants;
    private Integer matchesNumber;
}

