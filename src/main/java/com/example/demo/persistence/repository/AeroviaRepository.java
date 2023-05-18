package com.example.demo.persistence.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Interface.IAeroviaRep;
import com.example.demo.domain.entities.Aerovia;
import com.example.demo.persistence.CRUD.IAeroviaCRUD;

@Component
public class AeroviaRepository implements IAeroviaRep {
    private IAeroviaCRUD aeroviaCRUD;

    @Autowired
    public AeroviaRepository(IAeroviaCRUD aeroviaCRUD){
        this.aeroviaCRUD=aeroviaCRUD;
        }

    @Override
    public Optional<Aerovia> findAerovia(int id_aerovia) {
        return this.aeroviaCRUD.findById(id_aerovia);
        
    }
    
}
