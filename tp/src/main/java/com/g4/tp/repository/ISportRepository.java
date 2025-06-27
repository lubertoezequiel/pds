package com.g4.tp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4.tp.model.entities.Sport;

public interface ISportRepository extends JpaRepository<Sport, Integer> {
    Sport findByName(String name);

}