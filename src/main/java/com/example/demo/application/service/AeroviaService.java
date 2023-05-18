package com.example.demo.application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interface.IAeroviaRep;
import com.example.demo.Interface.IOcupacaoAeroviaRep;
import com.example.demo.domain.DTO.PercentualOcupacaoDTO;
import com.example.demo.domain.DTO.RelatorioDTO;
import com.example.demo.domain.entities.Aerovia;
import com.example.demo.domain.entities.OcupacaoAerovia;

@Service
public class AeroviaService{
    private IAeroviaRep iAeroviaRep;
    private IOcupacaoAeroviaRep iOcupacaoAeroviaRep;
    
    @Autowired
    public AeroviaService(IAeroviaRep iAeroviaRep, IOcupacaoAeroviaRep iOcupacaoAeroviaRep){
        this.iAeroviaRep = iAeroviaRep;
        this.iOcupacaoAeroviaRep=iOcupacaoAeroviaRep;
    }
    public List<Integer> consultaSlotsLivres(int aeroviaId, LocalDate data, float horario, float velCruzeiro){
        Aerovia aerovia = iAeroviaRep.findAerovia(aeroviaId).get();

        List<Integer> slotsTodos = new ArrayList<>(Arrays.asList(25000, 26000, 27000, 28000, 29000, 30000, 31000, 32000, 33000, 34000));
        List<Float> slotsHorarios = new ArrayList<>();

        float tempoVoo = aerovia.distancia / velCruzeiro;

        tempoVoo = tempoVoo + horario;
        
        slotsHorarios.add((float) Math.floor(horario));

        while(tempoVoo > horario){
            slotsHorarios.add((float) Math.floor(horario));
            tempoVoo--;
        } 
        
        slotsHorarios.add((float) Math.ceil(horario));

        List<OcupacaoAerovia> ocupadas = this.iOcupacaoAeroviaRep.findOcupacaoAerovias(aeroviaId, data, slotsHorarios);

        List<Integer> altitudesOcupadas = ocupadas.stream().map( ocupaAerovia -> ocupaAerovia.altitude_slot).collect(Collectors.toList());

        return slotsTodos.stream().filter(slotsLivres -> !altitudesOcupadas.contains(slotsLivres)).collect(Collectors.toList());
    }

    public RelatorioDTO consultaPorcentagemOcupacao(int aeroviaId, LocalDate data){
        List<OcupacaoAerovia> ocupacaoAerovias = this.iOcupacaoAeroviaRep.findAllByDataOcupacaoAerovias(aeroviaId, data);
        
        List<PercentualOcupacaoDTO> listaOcupacao = new ArrayList<>();

        List<Integer> slotsTodos = new ArrayList<>(Arrays.asList(25000, 26000, 27000, 28000, 29000, 30000, 31000, 32000, 33000, 34000));
        
        for(Integer slotAltitude: slotsTodos){
            List<OcupacaoAerovia> ocupadasPorSlot = ocupacaoAerovias.stream().filter(ocupacaoAerovia -> ocupacaoAerovia.altitude_slot == slotAltitude).collect(Collectors.toList());
            float porcentagem = Float.parseFloat(String.format("%.2f", ((ocupadasPorSlot.size() * 100) / 24.0)));
            listaOcupacao.add(new PercentualOcupacaoDTO( slotAltitude, porcentagem));
            ocupadasPorSlot.clear();
        }

        return new RelatorioDTO(listaOcupacao);
    }
}