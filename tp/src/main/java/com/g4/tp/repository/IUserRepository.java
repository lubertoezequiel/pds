package com.g4.tp.repository;

import com.g4.tp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    // Define methods for user repository operations, e.g., save, find, delete
    void saveUser(String user);
    String findUserById(String id);
    void deleteUserById(String id);
    // Add more methods as needed

}
