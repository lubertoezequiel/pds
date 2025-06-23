package com.g4.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.tp.model.User;
import com.g4.tp.repository.IUserRepository;

@Service
public class UserService {
    // Business logic related to User
    @Autowired
    private IUserRepository userRepository;

    public User createUser(User user) {
        // Here you can add any business logic before saving the user
        return userRepository.save(user);
    }
}
