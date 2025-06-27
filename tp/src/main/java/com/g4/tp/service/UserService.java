package com.g4.tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4.tp.model.entities.User;
import com.g4.tp.repository.IUserRepository;

@Service
public class UserService implements IUserService {
    // Business logic related to User
    @Autowired
    private IUserRepository userRepository;

    public User createUser(User user) {
        
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
        
        return userRepository.save(user);

    }

    @Override
    public void deleteUser(Long id) {
      try{
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting user with id: " + id, e);
        }
    }
     
    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElse(null);
    }
    
    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        
        return userRepository.save(existingUser);
    }

    @Override
    public User findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        
        return user;
    }
}
