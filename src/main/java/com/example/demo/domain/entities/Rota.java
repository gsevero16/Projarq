package com.example.demo.domain.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="rota")
public class Rota {

/*
 *     public static final int id = 0;
    public static final List<Aerovia> aerovias = null;
    public String nome;
    private Aeroporto origem;
    private Aeroporto destino;
    private double distancia;
 */
    //public listaRotaLivre(){}
@ManyToMany
 @JoinTable(
    name = "rota_aerovia",
    joinColumns =  @JoinColumn(name = "id_rota"),
    inverseJoinColumns = @JoinColumn(name = "id_aerovia"))
    public List<Aerovia> aerovias;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    
    public String nome;

    @ManyToOne
    @JoinColumn(name = "id_origem")
    public Aeroporto origem;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    public Aeroporto destino;

    public Rota(){
        
    }

}
