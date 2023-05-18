package com.example.demo.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ocupacao_aerovia")

public class OcupacaoAerovia {

   // public static final String data = null;
   // public static final int id_aerovia = 0;
    public static final float horaSlot = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    
    public LocalDate data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_aerovia")
	public Aerovia aerovia;
	public int altitude_slot;
	public int horario_slot;
	
    public OcupacaoAerovia() { }      

    public OcupacaoAerovia(LocalDate data, Aerovia aerovia, int altitude, int horario) {
        this.data = data;
        this.aerovia = aerovia;
        this.altitude_slot = altitude;
        this.horario_slot = horario;
    }

}
