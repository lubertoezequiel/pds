package com.g4.tp.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.annotations.Any;
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

import com.g4.tp.DTOs.UserDTO;
import com.g4.tp.model.entities.SkillLevelEnum;
import com.g4.tp.model.entities.Sport;
import com.g4.tp.model.entities.User;
import com.g4.tp.service.ISportService;
import com.g4.tp.service.IUserService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private ISportService sportService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        
        User userEntity = convertToEntity(user);
     
        try {
            User createdUser = userService.createUser(userEntity);
            UserDTO createdUserDTO = convertToDTO(createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO); 
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error); 
        }
         
    }


    @GetMapping("/userByEmail")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
       try{
            User user = userService.findByEmail(email);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    

    }
     
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDTO entity) {
        
        if (id == null || id.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID cannot be null or empty");
        }

        try {
            userService.updateUser(Long.valueOf(id), convertToEntity(entity));
            Map<String, String> response = new HashMap<>();
            response.put("message", "User updated successfully");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (NumberFormatException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid ID format");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }


    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    try {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
    }
}
    

private UserDTO convertToDTO(User user) {
    UserDTO dto = new UserDTO();

    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setPassword(user.getPassword());

    // Convertir lista de Sports a array de IDs
    if (user.getPracticedSports() != null) {
        int[] ids = user.getPracticedSports().stream()
            .mapToInt(sport -> sport.getNumber().intValue())
            .toArray();
        dto.setPracticedSportIds(ids);
    }

    // Deporte favorito
    if (user.getFavoriteSport() != null) {
        dto.setFavoriteSportId(user.getFavoriteSport().getNumber().intValue());
    }

    // Skill level
    if (user.getSkillLevel() != null) {
        dto.setSkillLevel(user.getSkillLevel().name());
    }

    return dto;
}


    private User convertToEntity(UserDTO dto) {

        List<Sport> practicedSports = Arrays.stream(dto.getPracticedSportIds())
        .mapToObj(id -> sportService.getSportById((long) id))
        .collect(Collectors.toList());


        Sport favoriteSport = sportService.getSportById((long) dto.getFavoriteSportId());

        SkillLevelEnum skillLevel = SkillLevelEnum.valueOf(dto.getSkillLevel().toUpperCase());

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPracticedSports(practicedSports);
        user.setFavoriteSport(favoriteSport);
        user.setSkillLevel(skillLevel);

        return user;


    }
}


