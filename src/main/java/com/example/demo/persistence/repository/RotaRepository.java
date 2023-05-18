package com.example.demo.persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Interface.IRotaRep;
import com.example.demo.domain.entities.Rota;
import com.example.demo.persistence.CRUD.IRotaCRUD;

@Component
public class RotaRepository implements IRotaRep{
    private IRotaCRUD rotaCRUD;

    @Autowired
    public RotaRepository(IRotaCRUD rotaCRUD){
        this.rotaCRUD=rotaCRUD;
    }

    @Override
    public Rota findById(int id_rota) {
        return this.rotaCRUD.findById(id_rota).get();
    }

    @Override
    public List<Rota> findRotas() {
        return this.rotaCRUD.findAll();
    }
    
}
