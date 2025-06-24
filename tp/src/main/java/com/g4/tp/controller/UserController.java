package com.g4.tp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4.tp.model.User;
import com.g4.tp.model.UserDTO;
import com.g4.tp.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
   
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hola usuario";
    }
    
    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO user) {
        System.out.println("Creating user: " + user.getName() + 
                           " with email: " + user.getEmail()+ 
                           " and password: " + user.getPassword());
                           
        User usuario = new User(user.getName(), user.getEmail(), user.getPassword());
    
        userService.createUser(usuario);

        return user;
    }
    
}
