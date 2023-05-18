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
    public LocalDate data;
    public float horariopartida;
    public float altitude;
    public float velocidadecruzeiro;

    @ManyToOne
    @JoinColumn(name = "id_rota")
    public Rota rota;
    public PlanoDeVoo() { }
    //public autorizaPlanoVoo(){ }
    public PlanoDeVoo(Rota rota, LocalDate data,int altitude, float horariopartida, float velocidadecruzeiro) {
        this.rota = rota;
        this.data = data;
        this.horariopartida = horariopartida;
        this.altitude = altitude;
        this.velocidadecruzeiro = velocidadecruzeiro;
        
    }



}