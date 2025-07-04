package com.g4.tp.service;

import java.util.List;

import com.g4.tp.DTOs.UserDTO;
import com.g4.tp.model.entities.User;

public interface IUserService {
    int createUser(UserDTO user);
    void deleteUser(int id);
    User getUserById(int id);
    User updateUser(int id, User user);
    User findByEmail(String email);
    List<User> findAll();
}
