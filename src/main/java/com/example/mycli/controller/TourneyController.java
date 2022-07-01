package com.example.mycli.controller;

import com.example.mycli.entity.Tourney;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.exceptions.tournamentException.TournamentException;
import com.example.mycli.service.TourneyService;

import com.example.mycli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TourneyController {

    private final TourneyService tourneyService;
    private final UserService userService;

    @Autowired
    public TourneyController(TourneyService tourneyService, UserService userService) {
        this.tourneyService = tourneyService;
        this.userService = userService;
    }
    @GetMapping("/tournaments")
    public List<Tourney> get() {
        return tourneyService.findAll();
    }

    @GetMapping("/tournaments/{id}")
    public Tourney get(@PathVariable Long id) throws TournamentException {
        return tourneyService.retrieve(id);
    }

    @PostMapping("/tournaments")
    public Tourney create(@RequestBody Tourney tourney)  throws TournamentException {
        return tourneyService.createTourney(tourney);
    }

    @PostMapping("/tournaments/{id}")
    public void addParticipiant(@PathVariable Long id, Principal principal) {
        tourneyService.addParticipiant(id,userService.findByLogin(principal));
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
}
