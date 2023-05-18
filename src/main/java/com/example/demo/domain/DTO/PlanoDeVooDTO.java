package com.example.demo.domain.DTO;

import java.time.LocalDate;

public class PlanoDeVooDTO {
    public int rotaId;
    public LocalDate data;
    public float horarioPartida;
    public float velocidadeCruzeiro;
    public int altitude;

    public PlanoDeVooDTO(int rotaId, LocalDate data, float horarioPartida, float velocidadeCruzeiro, int altitude) {
        this.rotaId = rotaId;
        this.data = data;
        this.horarioPartida = horarioPartida;
        this.velocidadeCruzeiro = velocidadeCruzeiro;
        this.altitude = altitude;
    }
}
