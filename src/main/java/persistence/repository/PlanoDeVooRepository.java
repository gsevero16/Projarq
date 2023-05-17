package persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Interface.IPlanoVooRep;
import domain.entities.PlanoDeVoo;
import persistence.CRUD.IPlanoVooCRUD;

@Component
public class PlanoDeVooRepository implements IPlanoVooRep{
    private IPlanoVooCRUD planoVooCRUD;

    @Autowired
    public PlanoDeVooRepository(IPlanoVooCRUD planoVooCRUD){
        this.planoVooCRUD=planoVooCRUD;
    }

    @Override
    public List<PlanoDeVoo> findPlanoDeVoos() {
        return this.planoVooCRUD.findAll();
    }

    @Override
    public PlanoDeVoo savePlanoDeVoo(PlanoDeVoo planoDeVoo) {
        return this.planoVooCRUD.save(planoDeVoo);
    }

    @Override
    public PlanoDeVoo findPlanoDeVooById(int planoDeVooId) {
        return this.planoVooCRUD.findById(planoDeVooId).get();
    }

    @Override
    public void deletaPlanoDeVoo(PlanoDeVoo planoDeVoo) {
        this.planoVooCRUD.delete(planoDeVoo);
    }
    
}
