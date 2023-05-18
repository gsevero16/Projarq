package com.example.demo.Interface;

import java.util.Optional;
import com.example.demo.domain.entities.Aerovia;

public interface IAeroviaRep {
    Optional<Aerovia> findAerovia(int aeroviaId);
    
}
