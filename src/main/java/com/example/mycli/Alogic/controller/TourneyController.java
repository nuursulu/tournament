package com.example.mycli.Alogic.controller;


import com.example.mycli.Alogic.entity.Tourney;
import com.example.mycli.Alogic.entity.dto.TourneyRequest;
import com.example.mycli.Alogic.service.TourneyService;
import com.example.mycli.exceptions.exception.TournamentException;
import com.example.mycli.model.entity.Tournament;
import com.example.mycli.model.entity.dto.TournamentRequest;
import com.example.mycli.services.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appapi")
public class TourneyController {

    private TourneyService tourneyService;

    public TourneyController(TourneyService tournamentService) {
        this.tourneyService = tournamentService;
    }

    /**
     * Additional method, which returns all available tournaments.
     *
     * @return all available tournaments.
     */
    @GetMapping("/tournaments")
    public List<Tourney> get() {
        return tourneyService.findAllTourney();
    }

    /**
     * Gets tournament by id.
     *
     * @param id - tournaments' id.
     * @return tournament by id.
     * @throws TournamentException if there is no tournament with provided id.
     */
    @GetMapping("/tournaments/{id}")
    public Tourney get(@PathVariable Long id) throws TournamentException {
        return tourneyService.findTourneyById(id);
    }

    /**
     * Creates new tournament.
     *
     * @param - tournament to create.
     * @return created tournament.
     * @throws TournamentException if maxParticipants is not provided or is not multiple of 8.
     */
//    @PostMapping("/tournaments")
//    public Tournament create(@RequestBody Tournament tournament)  throws TournamentException {
//        return tournamentService.createTournament(tournament);
//    }
    @PostMapping("/tournaments")
    public Tourney create(@RequestBody TourneyRequest tourneyRequest)  throws TournamentException {
        return tourneyService.createTournament(tourneyRequest);
    }
//
//    /**
//     * Starts tournament with provided id.
//     *
//     * @param id - id of tournament to start.
//     * @return status message.
//     * @throws TournamentException if there is no tournament with provided id.
//     */
//    @PutMapping("/tournaments/start/{id}")
//    public String start(@PathVariable Long id) throws TournamentException {
//        return tournamentService.startTournament(id);
//    }
//
//    /**
//     * Additional method, which holds tournament with provided id.
//     *
//     * @param id - id of tournament to hold.
//     * @return status message.
//     * @throws TournamentException if there is no tournament with provided id.
//     */
//    @PutMapping("/tournaments/hold/{id}")
//    public String hold(@PathVariable Long id) throws TournamentException {
//        return tournamentService.holdTournament(id);
//    }
//
//    /**
//     * Additional method, which provides interface to update tournament's fields.
//     *
//     * @param id - id of tournament to update.
//     * @param tournamentUpdated - updated tournament.
//     * @return updated tournament.
//     * @throws TournamentException if there is no tournament with provided id.
//     */
//    @PutMapping("/tournaments/{id}")
//    public Tournament update(@PathVariable Long id,
//                             @RequestBody Tournament tournamentUpdated) throws TournamentException {
//        return tournamentService.updateTournament(id, tournamentUpdated);
//    }
//
//    /**
//     * Additional method, which provides interface to delete tournament by id.
//     *
//     * @param id - id of tournament to delete.
//     * @return status message.
//     * @throws TournamentException if there is no tournament with provided id.
//     */
//    @DeleteMapping("/tournaments/{id}")
//    public String remove(@PathVariable Long id) throws TournamentException {
//        return tournamentService.removeTournament(id);
//    }
//
}
