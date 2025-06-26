package com.g4.tp.service;

import com.g4.tp.model.entities.User;

public interface IUserService {
    User createUser(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    User updateUser(Long id, User user);
}
