package com.example.demo.Interface;
import java.util.List;
import com.example.demo.domain.entities.Rota;

public interface IRotaRep {
    Rota findById(int rotaId);
    List<Rota> findRotas();
    
}
