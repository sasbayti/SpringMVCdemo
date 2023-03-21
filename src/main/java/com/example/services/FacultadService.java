package com.example.services;

import java.util.List;

import com.example.entities.Facultad;

public interface FacultadService {
    public List<Facultad> findAll();
    public Facultad findById(int idFacultad);
    public void save(Facultad facultad);
    public void deleteById(int idFacultad);
}
