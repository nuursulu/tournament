package com.example.mycli.Alogic.service;

import com.example.mycli.Alogic.entity.Tourney;
import com.example.mycli.Alogic.entity.dto.TourneyRequest;
import com.example.mycli.Alogic.repository.MatchhRepository;
import com.example.mycli.Alogic.repository.TourneyRepository;
import com.example.mycli.exceptions.exception.TournamentException;
import com.example.mycli.model.entity.Tournament;
import com.example.mycli.repository.UserEntityRepository;
import com.example.mycli.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TourneyService {


    private final TourneyRepository tourneyRepository;
    private final MatchhRepository matchRepository;
    private final UserEntityRepository userEntityRepository;

    @Autowired
    public TourneyService(TourneyRepository tourneyRepository, MatchhRepository matchRepository, UserEntityRepository userEntityRepository) {
        this.tourneyRepository = tourneyRepository;
        this.matchRepository = matchRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public List<Tourney> findAllTourney() {
        return tourneyRepository.findAll();
    }

    public Tourney findTourneyById(Long id) {
        Optional<Tourney> tourneyOptional = tourneyRepository.findById(id);

        return tourneyOptional.orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
    }

    public Tourney createTournament(TourneyRequest tourneyRequest) {
        Tourney tourney = new Tourney();
        LocalDateTime startTime = LocalDateTime.now();
        tourney.setName(tourneyRequest.getName());
        tourney.setStartTime(startTime);
        tourneyRepository.save(tourney);
        return tourney;
    }
//    public Tourney joinTournament() {
//
//        return tourneyRepository.save(tourney);
//    }

    public String startTournament(Long id) {
//        if (!tourneyRepository.existsById(id)) {
//            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
//        }
//
//        return tourneyRepository.findById(id)
//                .map(tourney -> {
//                    tourney.setOnHold(false);
//                    List<UserEntity> participants = tourney.getParticipants();
//                    Collections.shuffle(participants);
//                    tourney.setParticipants(participants);
//                    Utils.matchParticipants(tourney, matchRepository, participants, userEntityRepository);
//
//                    return "Tournament started successfully!\n";
//                }).orElseThrow(() -> new TournamentException("Participant not found!"));
        return  "";
    }

    public String holdTournament(Long id) {
//        if (!tournamentRepository.existsById(id)) {
//            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
//        }
//
//        return tournamentRepository.findById(id)
//                .map(tournament -> {
//                    tournament.setOnHold(true);
//
//                    return "Tournament is now on hold!";
//                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
        return "";
    }

    public Tournament updateTournament(Long id, Tournament tournamentUpdated) {
//        if (!tournamentRepository.existsById(id)) {
//            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
//        }
//
//        return tournamentRepository.findById(id)
//                .map(tournament -> {
//                    tournament.setMaxParticipants(tournamentUpdated.getMaxParticipants());
//                    tournament.setMatchesNumber(tournamentUpdated.getMatchesNumber());
//                    tournament.setOnHold(tournamentUpdated.isOnHold());
//                    tournament.setParticipants(tournamentUpdated.getParticipants());
//
//                    return tournamentRepository.save(tournament);
//                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
        return null;
    }

    public String removeTournament(Long id) {
        if (!tourneyRepository.existsById(id)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
        }

        return tourneyRepository.findById(id)
                .map(tournament -> {
                    tourneyRepository.delete(tournament);
                    return "Tournament deleted successfully!";
                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
    }
}