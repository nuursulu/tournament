package com.example.mycli.services;


import com.example.mycli.exceptions.exception.TournamentException;
import com.example.mycli.model.entity.Match;
import com.example.mycli.model.entity.Participant;
import com.example.mycli.model.entity.Tournament;
import com.example.mycli.model.entity.dto.ParticipantRequest;
import com.example.mycli.repository.ParticipantRepository;
import com.example.mycli.repository.TournamentRepository;
import com.example.mycli.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private final TournamentRepository tournamentRepository;
    private final ParticipantRepository participantRepository;

    public ParticipantService(TournamentRepository tournamentRepository, ParticipantRepository participantRepository) {
        this.tournamentRepository = tournamentRepository;
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAllParticipants(Long tournamentId) {
        if (!tournamentRepository.existsById(tournamentId)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(tournamentId));
        }

        return participantRepository.findByTournamentId(tournamentId);
    }

    public Participant addParticipant(Long tournamentId, Participant participant) {
        return tournamentRepository.findById(tournamentId)
                .map(tournament -> {
                    if (tournament.getParticipants().size() < tournament.getMaxParticipants()) {
                        participant.setActive(true);
                        participant.setMatch(new Match());
                        participant.setTournament(tournament);

                        return participantRepository.save(participant);
                    }

                    // TODO - size limit exceeded message
                    return null;
                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(tournamentId)));
    }
    public Participant addParticipant(Long tournamentId, ParticipantRequest participantRequest) {
        Participant participant = new Participant();
        return tournamentRepository.findById(tournamentId)
                .map(tournament -> {
                    if (tournament.getParticipants().size() < tournament.getMaxParticipants()) {
                        participant.setNickname(participantRequest.getNickname());
                        participant.setActive(true);
                        participant.setMatch(new Match());
                        participant.setTournament(tournament);

                        return participantRepository.save(participant);
                    }

                    // TODO - size limit exceeded message
                    return null;
                }).orElseThrow(() -> new TournamentException(Utils.tournamentNotFoundMessage(tournamentId)));
    }

    public Participant updateParticipant(Long tournamentId,
                                         Long participantId,
                                         Participant participantUpdated) {
        if (!tournamentRepository.existsById(tournamentId)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(tournamentId));
        }

        return participantRepository.findById(participantId)
                .map(participant -> {
                    participant.setId(participantUpdated.getId());
                    participant.setNickname(participantUpdated.getNickname());
                    participant.setActive(participantUpdated.isActive());

                    return participantRepository.save(participant);
                }).orElseThrow(() -> new TournamentException(Utils.participantNotFoundMessage()));
    }

    public String removeParticipant(Long tournamentId, Long participantId) {
        if (!tournamentRepository.existsById(tournamentId)) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(tournamentId));
        }

        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);

        if (tournamentOptional.isEmpty()) {
            throw new TournamentException(Utils.tournamentNotFoundMessage(tournamentId));
        }

        Tournament tournament = tournamentOptional.get();

        if (!tournament.isOnHold()) {
            return "Can't delete participant, because tournament is not on hold!";
        }

        return participantRepository.findById(participantId)
                .map(participant -> {
                    participantRepository.delete(participant);

                    return "Participant deleted successfully!";
                }).orElseThrow(() -> new TournamentException(Utils.participantNotFoundMessage()));
    }
}
