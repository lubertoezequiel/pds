package com.g4.tp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.DTOs.SportDTO;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.service.SportService;

@RestController
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private SportService sportService;


    
    @PostMapping("/create")
    public ResponseEntity<?> createSport(@RequestBody SportDTO sport) {
        Sport sportEntity = convertToEntity(sport);
        
        try {
            Sport createdSport = sportService.createSport(sportEntity);
            SportDTO createdSportDTO = convertToDTO(createdSport);
            return ResponseEntity.status(201).body(createdSportDTO); 
        } catch (Exception e) {
            Map<String, String> error = Map.of("error", "Sport already exists");
            return ResponseEntity.status(409).body(error); 
        }
    }
    @GetMapping


    private SportDTO convertToDTO(Sport sport) {
        SportDTO sportDTO = new SportDTO(sport.getName(), sport.getDescription(), sport.getRequiredPlayers());
        return sportDTO;
    }

    private Sport convertToEntity(SportDTO sportDTO) {
        Sport sport = new Sport(sportDTO.getName(), sportDTO.getDescription(), sportDTO.getRequiredPlayers());
        return sport;
    }

}