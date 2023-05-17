package application;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.service.AeroviaService;
import domain.DTO.RelatorioDTO;

@Component
public class ConsultaRelatorio {
    private AeroviaService aeroviaService;

    @Autowired
    public ConsultaRelatorio(AeroviaService aeroviaService){
        this.aeroviaService = aeroviaService;
    }
    public RelatorioDTO consultaRelatorio(int aerviaId, LocalDate data){
        return this.aeroviaService.consultaPorcentagemOcupacao(aerviaId, null);
    }
}
