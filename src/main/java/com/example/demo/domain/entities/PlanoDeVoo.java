package com.example.demo.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="planodevoo")
public class PlanoDeVoo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int id;
    public float horarioPartida;
    public LocalDate data;
    public float altitude;
    public float velocidadeCruzeiro;

    @ManyToOne
    @JoinColumn( name = "id_rota")
    public Rota rota;

    public PlanoDeVoo() { }
    //public autorizaPlanoVoo(){ }
    public PlanoDeVoo(float horarioPartida, LocalDate data, int altitude, float velocidadeCruzeiro, Rota rota) {
        this.horarioPartida = horarioPartida;
        this.data = data;
        this.altitude = altitude;
        this.velocidadeCruzeiro = velocidadeCruzeiro;
        this.rota = rota;
    }



}