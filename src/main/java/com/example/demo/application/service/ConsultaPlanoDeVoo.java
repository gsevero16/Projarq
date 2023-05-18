package com.example.demo.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.DTO.PlanoDeVooDTO;
import com.example.demo.domain.entities.Aerovia;

@Component
public class ConsultaPlanoDeVoo {
    private PlanoDeVooService planoDeVooService;

    @Autowired
    public ConsultaPlanoDeVoo(PlanoDeVooService planoDeVooService){
        this.planoDeVooService = planoDeVooService;
    }

    public List<Aerovia> verificaPlanoDeVoo(PlanoDeVooDTO plano){
        return this.planoDeVooService.avaliaPlanoDeVoo(plano);
    }
}
