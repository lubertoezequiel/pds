package com.g4.tp.service;

import com.g4.tp.model.entities.Sport;


import java.util.List;


public interface ISportService {
    Sport createSport(Sport sport);
    
    List<Sport> getAllSports();
    
    Sport getSportById(Long id);
    
    Sport updateSport(Long id, Sport sport);
    
    void deleteSport(Long id);
    
    Sport findByName(String name);

}
