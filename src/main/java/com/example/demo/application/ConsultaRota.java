package com.example.demo.application;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.service.RotaService;
import com.example.demo.domain.DTO.RotaDTO;
import com.example.demo.domain.entities.Rota;
import com.example.demo.persistence.repository.RotaRepository;

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
