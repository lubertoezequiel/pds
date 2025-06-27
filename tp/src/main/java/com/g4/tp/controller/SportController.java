package com.g4.tp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.DTOs.SportDTO;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.service.ISportService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/sport")
public class SportController {

    @Autowired
    private ISportService sportService;
  
    @PostMapping("/create")
    public ResponseEntity<?> createSport(@RequestBody SportDTO sport) {
        Sport sportEntity = convertToEntity(sport);
        
        try {
            Sport createdSport = sportService.createSport(sportEntity);
            SportDTO createdSportDTO = convertToDTO(createdSport);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSportDTO); 
        } catch (Exception e) {
            Map<String, String> error = Map.of("error", "Sport already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error); 
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSports() {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(sportService.getAllSports());
        } catch (Exception e) {
            Map<String, String> error = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getSportById(@RequestParam Long id) {
        try {
            Sport sport = sportService.getSportById(id);
            if (sport == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Sport not found"));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(convertToDTO(sport));
        } catch (Exception e) {
            Map<String, String> error = Map.of("error", "Error retrieving sport");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getSportByName(@RequestParam String name) {
        try {
            Sport sport = sportService.findByName(name);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(convertToDTO(sport));
        } catch (IllegalArgumentException e) {
            Map<String, String> error = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (RuntimeException e) {
            Map<String, String> error = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateSport(@PathVariable String id, @RequestBody SportDTO entity) {
        if (id == null || id.isEmpty()) {
            Map<String, String> error = Map.of("error", "ID cannot be null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        try {
            Sport updatedSport = sportService.updateSport(Long.valueOf(id), convertToEntity(entity));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(convertToDTO(updatedSport));
        } catch (NumberFormatException e) {
            Map<String, String> error = Map.of("error", "Invalid ID format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (RuntimeException e) {
            Map<String, String> error = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSport(@PathVariable Long id) {
        try {
            sportService.deleteSport(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            Map<String, String> error = Map.of("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }    
    // Helper methods to convert between DTO and Entity

    private SportDTO convertToDTO(Sport sport) {
        SportDTO sportDTO = new SportDTO(sport.getName(), sport.getDescription(), sport.getRequiredPlayers());
        return sportDTO;
    }

    private Sport convertToEntity(SportDTO sportDTO) {
        Sport sport = new Sport(sportDTO.getName(), sportDTO.getDescription(), sportDTO.getRequiredPlayers());
        return sport;
    }

}