package application;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.service.RotaService;
import domain.DTO.RotaDTO;
import domain.entities.Rota;
import persistence.repository.RotaRepository;

@Component
public class ConsultaRota {
    private RotaService rotaService;

    @Autowired
    public ConsultaRota(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    public List<RotaDTO> buscaRotaDestino(String destino, String origem){
        List<Rota> rotasSelecionadas = this.rotaService.consultaRota(destino, origem);
        List<RotaDTO> rotasSelecionadasDto = new ArrayList<>();
        rotasSelecionadas.forEach( rota -> rotasSelecionadasDto.add(new RotaDTO(rota)));
        return rotasSelecionadasDto;
    }
    

}
