package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.service.PlanoDeVooService;
import com.example.demo.domain.DTO.PlanoDeVooDTO;
import com.example.demo.domain.entities.PlanoDeVoo;

@Component
public class AutorizaPlanoDeVoo{
    private PlanoDeVooService planoDeVooService;

    @Autowired
    public AutorizaPlanoDeVoo(PlanoDeVooService planoDeVooService){
        this.planoDeVooService = planoDeVooService;
    }

    public PlanoDeVoo autorizaPlanoDeVoo(PlanoDeVooDTO planoDeVooDTO){
        return this.planoDeVooService.autorizarPlanoDeVoo(planoDeVooDTO);
    }
}