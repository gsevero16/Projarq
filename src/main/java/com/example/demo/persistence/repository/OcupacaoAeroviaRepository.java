package com.example.demo.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Interface.IOcupacaoAeroviaRep;
import com.example.demo.domain.entities.OcupacaoAerovia;
import com.example.demo.persistence.CRUD.IOcupacaoAeroviaCRUD;

@Component
public class OcupacaoAeroviaRepository implements IOcupacaoAeroviaRep {
    private IOcupacaoAeroviaCRUD ocupacaoAeroviaCRUD;

    @Autowired
    public OcupacaoAeroviaRepository(IOcupacaoAeroviaCRUD ocupacaoAeroviaCRUD){
        this.ocupacaoAeroviaCRUD=ocupacaoAeroviaCRUD;
    }

    @Override
    public List<OcupacaoAerovia> findOcupacaoAerovias(int id_aerovia, LocalDate data, List<Float> horarios) {
        return ocupacaoAeroviaCRUD.findAll().stream()
        .filter(e-> e.id == id_aerovia)
        .filter(e-> e.data.equals(data))
        .filter(e -> horarios.contains((float)e.horaSlot))
        .collect(Collectors.toList());
    }

    @Override
    public List<OcupacaoAerovia> findAllByDataOcupacaoAerovias(int id_aerovia, LocalDate data) {
        return ocupacaoAeroviaCRUD.findAll().stream()
        .filter(e->e.id == id_aerovia)
        .filter(e-> e.data.equals(data))
        .collect(Collectors.toList());
    }

    @Override
    public OcupacaoAerovia ocupacaoAerovia(OcupacaoAerovia ocupacaoAerovia) {
        return ocupacaoAeroviaCRUD.save(ocupacaoAerovia);
    }

    @Override
    public void excluiOcupacaoAerovia(OcupacaoAerovia ocupacaoAerovia) {
        this.ocupacaoAeroviaCRUD.delete(ocupacaoAerovia);
    }
    
}
