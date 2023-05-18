package com.example.demo.domain.DTO;
import java.util.List;

import com.example.demo.domain.entities.Aerovia;
import com.example.demo.domain.entities.Rota;

public class RotaDTO {
    private String nome;
    private int id;
    private List<Aerovia> aerovias;

    
    public RotaDTO(String nome, int id, List<Aerovia> aerovias){
        this.nome = nome;
        this.id = id;
        this.aerovias = aerovias;
    }

    public RotaDTO(Rota rota){
        this.nome = rota.nome;
        this.id = rota.id;
        this.aerovias = rota.aerovias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public List<Aerovia> getAerovias() {
        return aerovias;
    }

    public void setAerovias(List<Aerovia> aerovias) {
        this.aerovias = aerovias;
    }

}