package com.g4.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.Match;
import com.g4.tp.repository.IMatchRepository;

@Service
public class MatchService {
    // Business logic related to Match
    @Autowired
    private IMatchRepository matchRepository;

    public Match createMatch(Match match) {
        System.out.println("Creating match: " + match.getSport() +
                " on date: " + match.getDate() +
                " duration: " + match.getDuration() + " minutes");
        // Here you can add any business logic before saving the match
        // TODO: Implementar lógica de matching strategy
        // TODO: Implementar notificaciones con Observer pattern
        return matchRepository.save(match);
    }

    // TODO: Agregar métodos para:
    // - Unir jugadores al partido
    // - Cambiar estados del partido
    // - Buscar partidos disponibles
    // - Implementar estrategias de matching
}
