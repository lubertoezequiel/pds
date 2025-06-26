package com.g4.tp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.DTOs.UserDTO;
import com.g4.tp.model.entities.User;
import com.g4.tp.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hola usuario";
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO user) {
        System.out.println("Creating user: " + user.getName() +
                " with email: " + user.getEmail() +
                " and password: " + user.getPassword());

        User usuario = new User(user.getName(), user.getEmail(), user.getPassword());

        userService.createUser(usuario);

        return user;
    }

}


private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getName(), user.getEmail(), user.getPassword());
        return userDTO;
}

private User convertToEntity(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getEmail(),userDTO.getPassword());
        return user;
}