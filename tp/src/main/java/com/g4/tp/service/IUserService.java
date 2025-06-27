package com.g4.tp.service;

import com.g4.tp.DTOs.UserDTO;
import com.g4.tp.model.entities.User;

public interface IUserService {
    int createUser(UserDTO user);
    void deleteUser(int id);
    User getUserById(int id);
    User updateUser(int id, User user);
    User findByEmail(String email);
}
