package com.example.demo.Interface;
import java.util.List;

import com.example.demo.domain.entities.OcupacaoAerovia;

import java.time.LocalDate;

public interface IOcupacaoAeroviaRep {
    List<OcupacaoAerovia> findOcupacaoAerovias(int aeroviaId, LocalDate date, List<Float> horarios);
    List<OcupacaoAerovia> findAllByDataOcupacaoAerovias (int aeroviaId, LocalDate data);
    OcupacaoAerovia ocupacaoAerovia(OcupacaoAerovia ocupacaoAerovia);
    void excluiOcupacaoAerovia(OcupacaoAerovia ocupacaoAerovia);
    
}
