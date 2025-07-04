package com.g4.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.tp.model.entities.Match;
import com.g4.tp.model.state.MatchStateEnum;

public interface IMatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findAllByStateEnumIn(List<MatchStateEnum> states);


}