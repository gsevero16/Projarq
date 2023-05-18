package com.example.demo.domain.entities;

enum TipoAeronave {
PARTICULAR (1), //AERONAVE PARTICULAR DE PEQUENO PORTE
PASSAGEIROS (2), //AERONAVE COMERCIAL DE PASSAGEIROS
CARGA (3); // AERONAVE COMERCIAL DE CARGA

private final int valor;
TipoAeronave (int valorOpcao) {
    valor = valorOpcao;
}
public int getValor(){
    return valor;
    }
}
