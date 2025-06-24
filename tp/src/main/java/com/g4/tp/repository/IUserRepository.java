package com.g4.tp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.tp.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

}
