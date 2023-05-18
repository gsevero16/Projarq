package com.example.demo.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interface.IRotaRep;
import com.example.demo.domain.entities.Rota;

@Service
public class RotaService{
    private IRotaRep rotaRep;
    @Autowired
    public RotaService(IRotaRep rotaRep){
        this.rotaRep=rotaRep;
    }
    public List<Rota> consultaRota(String origem, String destino){
        return rotaRep.findRotas().stream()
        .filter(rota -> rota.destino.nome.toLowerCase().equals(destino.toLowerCase())
        && rota.origem.nome.toLowerCase().equals(origem.toLowerCase()))
.collect(Collectors.toList());
    }
}