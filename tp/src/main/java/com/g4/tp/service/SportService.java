package com.g4.tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.Sport;
import com.g4.tp.repository.ISportRepository;

@Service
public class SportService implements ISportService {
    // Business logic related to Sport
    @Autowired
    private ISportRepository sportRepository;

    @Override
    public Sport createSport(Sport sport) {

        if (sportRepository.findByName(sport.getName()) != null) {
            throw new RuntimeException("Sport already exists with name: " + sport.getName());
        }
        
        // Here you can add any business logic before saving the sport
        return sportRepository.save(sport);
    }

    @Override
    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    @Override
    public Sport getSportById(Long id) {
        return sportRepository.findById(id).orElse(null);
    }
    
    @Override
    public Sport findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        
        Sport sport = sportRepository.findByName(name);
        if (sport == null) {
            throw new RuntimeException("Sport not found with name: " + name);
        }
        
        return sport;
    }

    @Override
    public Sport updateSport(Long id, Sport sport) {
        Sport existingSport = sportRepository.findById(id).orElse(null);
        if (existingSport == null) {
            throw new RuntimeException("Sport not found with id: " + id);
        }
        
        existingSport.setName(sport.getName());
        existingSport.setDescription(sport.getDescription());
        existingSport.setRequiredPlayers(sport.getRequiredPlayers());
        
        return sportRepository.save(existingSport);
    }

    @Override
    public void deleteSport(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSport'");
    }
}