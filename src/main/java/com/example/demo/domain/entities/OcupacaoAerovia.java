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
@Table(name="ocupacaoaerovia")

public class OcupacaoAerovia {

   // public static final String data = null;
   // public static final int aeroviaId = 0;
    public static final float horaSlot = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    
    public LocalDate data;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "aeroviaId")
	public Aerovia aerovia;
	public int slot_altitude;
	public int slot_horario;
	
    public OcupacaoAerovia() { }      

    public OcupacaoAerovia(LocalDate data, Aerovia aerovia, int slot_altitude, int slot_horario) {
        this.data = data;
        this.aerovia = aerovia;
        this.slot_altitude = slot_altitude;
        this.slot_horario = slot_horario;
    }

}
