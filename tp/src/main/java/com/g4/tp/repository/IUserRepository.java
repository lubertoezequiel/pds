package com.g4.tp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.tp.model.entities.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
