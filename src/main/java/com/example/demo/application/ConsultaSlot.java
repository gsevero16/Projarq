package com.example.demo.application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.application.service.AeroviaService;

@Component
public class ConsultaSlot {
    private AeroviaService aeroviaService;
    @Autowired
    public ConsultaSlot(AeroviaService aeroviaService){
        this.aeroviaService=aeroviaService;
    }
    public List<Integer> consultaSlotsLivres(int aeroviaId, LocalDate data, Float horario, float velocidadeCruzeiro){
        return this.aeroviaService.consultaSlotsLivres(aeroviaId, data, aeroviaId, velocidadeCruzeiro);
    }
}
