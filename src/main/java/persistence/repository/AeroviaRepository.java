package persistence.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import Interface.IAeroviaRep;
import domain.entities.Aerovia;
import persistence.CRUD.IAeroviaCRUD;

public class AeroviaRepository implements IAeroviaRep {
    private IAeroviaCRUD aeroviaCRUD;

    @Autowired
    public AeroviaRepository(IAeroviaCRUD aeroviaCRUD){
        this.aeroviaCRUD=aeroviaCRUD;
        }

    @Override
    public Optional<Aerovia> findAerovia(int aeroviaId) {
        return this.aeroviaCRUD.findById(aeroviaId);
        
    }
    
}
