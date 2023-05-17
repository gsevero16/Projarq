package Interface;
import java.util.List;

import org.springframework.cglib.core.Local;

import domain.entities.OcupacaoAerovia;

import java.time.LocalDate;

public interface IOcupacaoAeroviaRep {
    List<OcupacaoAerovia> findOcupacaoAerovias(int aeroviaId, LocalDate date, List<Float> horarios);
    List<OcupacaoAerovia> findAllByDataOcupacaoAerovias (int aeroviaId, LocalDate data);
    OcupacaoAerovia ocupacaoAerovia(OcupacaoAerovia ocupacaoAerovia);
    void excluiOcupacaoAerovia(OcupacaoAerovia ocupacaoAerovia);
    
}
