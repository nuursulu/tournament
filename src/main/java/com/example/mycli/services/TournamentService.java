package com.example.mycli.services;


import com.example.mycli.exceptions.exception.TournamentException;
import com.example.mycli.model.entity.Participant;
import com.example.mycli.model.entity.Tournament;
import com.example.mycli.model.entity.dto.TournamentRequest;
import com.example.mycli.repository.MatchRepository;
import com.example.mycli.repository.ParticipantRepository;
import com.example.mycli.repository.TournamentRepository;
import com.example.mycli.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;

    public TournamentService(TournamentRepository tournamentRepository, MatchRepository matchRepository, ParticipantRepository participantRepository) {
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
        this.participantRepository = participantRepository;
    }

    public List<Tournament> findAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament findTournamentById(Long id) {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(id);

        return tournamentOptional.orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
    }

    public Tournament createTournament(Tournament tournament) {
        if (tournament.getMaxParticipants() == null || tournament.getMaxParticipants() % 8 != 0) {
            throw new TournamentException("Max number of participants should be multiple of 8!");
        }

        return tournamentRepository.save(tournament);
    }

    public Tournament createTournament(TournamentRequest tournamentRequest) {
        if (tournamentRequest.getMaxParticipants() == null || tournamentRequest.getMaxParticipants() % 8 != 0) {
            throw new TournamentException("Max number of participants should be multiple of 8!");
        }

        Tournament tournament = new Tournament();
        tournament.setMaxParticipants(tournamentRequest.getMaxParticipants());
        tournament.setMatchesNumber(tournamentRequest.getMatchesNumber());
        return tournamentRepository.save(tournament);
    }

    public String startTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
        }

        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournament.setOnHold(false);
                    List<Participant> participants = tournament.getParticipants();
                    Collections.shuffle(participants);
                    tournament.setParticipants(participants);
                    Utils.matchParticipants(tournament, matchRepository, participants, participantRepository);

                    return "Tournament started successfully!\n";
                }).orElseThrow(() -> new TournamentException("Participant not found!"));
    }

    public String holdTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
        }

        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournament.setOnHold(true);

                    return "Tournament is now on hold!";
                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
    }

    public Tournament updateTournament(Long id, Tournament tournamentUpdated) {
        if (!tournamentRepository.existsById(id)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
        }

        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournament.setMaxParticipants(tournamentUpdated.getMaxParticipants());
                    tournament.setMatchesNumber(tournamentUpdated.getMatchesNumber());
                    tournament.setOnHold(tournamentUpdated.isOnHold());
                    tournament.setParticipants(tournamentUpdated.getParticipants());

                    return tournamentRepository.save(tournament);
                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
    }

    public String removeTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(id));
        }

        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournamentRepository.delete(tournament);

                    return "Tournament deleted successfully!";
                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(id)));
    }
}
