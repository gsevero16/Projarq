package com.example.demo.persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.entities.OcupacaoAerovia;

public interface IOcupacaoAeroviaCRUD extends JpaRepository<OcupacaoAerovia, Integer> {
    
}
