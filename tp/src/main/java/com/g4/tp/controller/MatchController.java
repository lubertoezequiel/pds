package com.g4.tp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.DTOs.MatchDTO;
import com.g4.tp.mapper.LocationMapper;
import com.g4.tp.mapper.MatchMapper;
import static com.g4.tp.mapper.MatchMapper.convertToDTO;
import com.g4.tp.model.entities.Match;
import com.g4.tp.service.IMatchService;



@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private LocationMapper locationMapper;
    
    @Autowired
    private IMatchService matchService;



    @PostMapping("/create")
    public ResponseEntity<?> createMatch(@RequestBody MatchDTO match) {
        try {
            Match matchEntity = matchMapper.convertToEntity(match);
            Match savedMatch = matchService.createMatch(matchEntity); 
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMatch);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid match data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error creating match: " + e.getMessage());
        }
    }
    
    @GetMapping("/getById")
    public ResponseEntity<?> getMatchById(@RequestParam int id) {
        try {
            Match match = matchService.getMatchById(id);
            if (match == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Match not found with ID: " + id);
            }
            MatchDTO matchDTO = convertToDTO(match);
            matchDTO.setLocation(locationMapper.convertToLocationDTO(match.getLocation()));
            return ResponseEntity.status(HttpStatus.OK).body(matchDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error retrieving match: " + e.getMessage());
        }

    }
    @PostMapping("addPlayer")
    public ResponseEntity<?> addPlayer(@RequestParam int userId , @RequestParam int matchId) {
        try{
            matchService.joinMatch(userId, matchId);
            Map<String, String> message = Map.of("message", "Player added successfully to the match");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }    
        
    }
    @GetMapping("/getProximityMatches")
    public ResponseEntity<?> getProximityMatches(@RequestParam int userId) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(matchService.getMatchesByProximityByUserId(userId, 400));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error retrieving proximity matches: " + e.getMessage());
        }
    }
}