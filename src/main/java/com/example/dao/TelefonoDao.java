package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Telefono;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer> {
    
}
