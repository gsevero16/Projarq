package persistence.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Interface.IRotaRep;
import domain.entities.Rota;
import persistence.CRUD.IRotaCRUD;

@Component
public class RotaRepository implements IRotaRep{
    private IRotaCRUD rotaCRUD;

    @Autowired
    public RotaRepository(IRotaCRUD rotaCRUD){
        this.rotaCRUD=rotaCRUD;
    }

    @Override
    public Rota findById(int rotaId) {
        return this.rotaCRUD.findById(rotaId).get();
    }

    @Override
    public List<Rota> findRotas() {
        return this.rotaCRUD.findAll();
    }
    
}
