package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.service.PlanoDeVooService;
import domain.DTO.PlanoDeVooDTO;
import domain.entities.PlanoDeVoo;

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