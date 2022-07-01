package com.example.mycli.repository;


import com.example.mycli.entity.Tourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourneyRepository extends JpaRepository<Tourney, Long> {
}
