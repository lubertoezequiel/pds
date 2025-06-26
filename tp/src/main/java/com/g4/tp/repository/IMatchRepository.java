package com.g4.tp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.tp.model.entities.Match;

public interface IMatchRepository extends JpaRepository<Match, Long> {
    // TODO: Agregar métodos de búsqueda específicos como:
    // List<Match> findBySport(String sport);
    // List<Match> findByState(String state);

}