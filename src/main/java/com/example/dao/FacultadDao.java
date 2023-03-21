package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Facultad;

public interface FacultadDao extends JpaRepository<Facultad, Integer> {
    
}
