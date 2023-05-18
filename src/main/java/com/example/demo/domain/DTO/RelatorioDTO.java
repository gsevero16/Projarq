package com.example.demo.domain.DTO;

import java.util.List;

public class RelatorioDTO {
    public List<PercentualOcupacaoDTO> listaOcupacao;
    
    public RelatorioDTO(List<PercentualOcupacaoDTO> listaOcupacao){
        this.listaOcupacao = listaOcupacao;
    }

}
