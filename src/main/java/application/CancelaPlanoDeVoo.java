package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.service.PlanoDeVooService;
import domain.entities.PlanoDeVoo;

@Component
public class CancelaPlanoDeVoo {
    private PlanoDeVooService servicoPlano;

    @Autowired
    public CancelaPlanoDeVoo(PlanoDeVooService servicoPlano){
        this.servicoPlano = servicoPlano;
    }

    public PlanoDeVoo cancelaPlano(int id){
        return this.servicoPlano.cancelaPlanoDeVoo(id);
    }
}
