package com.g4.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.Sport;
import com.g4.tp.repository.ISportRepository;

@Service
public class SportService {
    // Business logic related to Sport
    @Autowired
    private ISportRepository sportRepository;

    public Sport createSport(Sport sport) {
        System.out.println("Creating sport: " + sport.getName() +
                " with description: " + sport.getDescription() +
                " and required players: " + sport.getRequiredPlayers());
        // Here you can add any business logic before saving the sport
        return sportRepository.save(sport);
    }
}