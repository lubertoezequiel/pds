package com.g4.tp.service;

import java.util.List;

import com.g4.tp.model.entities.Sport;


public interface ISportService {
    Sport createSport(Sport sport);
    
    List<Sport> getAllSports();
    
    Sport getSportById(int id);
    
    Sport updateSport(int id, Sport sport);
    
    void deleteSport(int id);
    
    Sport findByName(String name);

}
