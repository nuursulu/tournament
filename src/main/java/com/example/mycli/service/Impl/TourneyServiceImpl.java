package com.example.mycli.service.Impl;

import com.example.mycli.entity.Tourney;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.TourneyRepository;
import com.example.mycli.service.TourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TourneyServiceImpl implements TourneyService {

    private final TourneyRepository tourneyRepository;

    @Autowired
    public TourneyServiceImpl(TourneyRepository tourneyRepository) {
        this.tourneyRepository = tourneyRepository;
    }

    @Override
    public Tourney createTourney(Tourney tourney) {
        Tourney myTourney = new Tourney();
        myTourney.setName(tourney.getName());
        myTourney.setStatus(tourney.getStatus());
        return tourneyRepository.save(myTourney);
    }

    @Override
    public List<Tourney> findAll() {
        return tourneyRepository.findAll();
    }

    @Override
    public Tourney retrieve(Long tournamentId) {
        return tourneyRepository.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(tournamentId)));
    }

    @Override
    public void addParticipiant(Long tournamentId, UserEntity user) {
        Tourney tourney = retrieve(tournamentId);
        tourney.addPlayer(user);
        tourneyRepository.save(tourney);
    }
    @Override
    public void start(Long tournamentId){

        this.scheduleTournament();
    }
    @Override
    public void scheduleTournament(int teams, int round) {
        if (((teams%2 != 0) && (round != teams - 1))||(teams <= 0))
            throw new IllegalArgumentException();
        int[] cycle = new int[teams];
        int n = teams /2;
        for (int i = 0; i < n; i++) {
            cycle[i] = i + 1;
            cycle[teams - i - 1] = cycle[i] + n;
        }

        for(int d = 1; d <= round; d++) {
            System.out.println(String.format("Round %d", d));
            for (int i = 0; i < n; i++) {
                System.out.println(String.format("team %d - team %d",cycle[i],cycle[teams - i - 1]));
            }
            int temp = cycle[1];
            for (int i = 1; i < teams - 1; i++) {
                int pr = cycle[i+1];
                cycle[i+1] = temp;
                temp = pr;
            }
            cycle[1] = temp;
        }
    }

}
