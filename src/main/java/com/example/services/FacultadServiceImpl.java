package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.FacultadDao;
import com.example.entities.Facultad;

@Service
public class FacultadServiceImpl implements FacultadService {

    @Autowired
    private FacultadDao facultadDao;

    @Override
    public List<Facultad> findAll() {
        return facultadDao.findAll();
    }

    @Override
    public Facultad findById(int idFacultad) {
        return facultadDao.findById(idFacultad).get();
    }

    @Override
    public void save(Facultad facultad) {
        facultadDao.save(facultad);
    }

    @Override
    public void deleteById(int idFacultad) {
        facultadDao.deleteById(idFacultad);
    }
    
}
