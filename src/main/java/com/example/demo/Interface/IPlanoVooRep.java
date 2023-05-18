package com.example.demo.Interface;
import java.util.List;

import com.example.demo.domain.entities.PlanoDeVoo;

public interface IPlanoVooRep {
    List<PlanoDeVoo> findPlanoDeVoos();
    PlanoDeVoo savePlanoDeVoo(PlanoDeVoo planoDeVoo);
    PlanoDeVoo findPlanoDeVooById(int planoDeVooId);
    void deletaPlanoDeVoo(PlanoDeVoo planoDeVoo);
    
}
