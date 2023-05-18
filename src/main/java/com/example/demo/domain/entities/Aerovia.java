package com.example.demo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="aerovia")
public class Aerovia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    public int id;
    public String nome;
    public float distancia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_origem")
    public Aeroporto origem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_destino")
    public Aeroporto destino;

    public Aerovia(){ }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public void setDestino(Aeroporto destino) {
        this.destino = destino;
    }

    public Aeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    
}
