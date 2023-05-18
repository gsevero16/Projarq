package com.example.demo.persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.entities.Rota;

public interface IRotaCRUD extends JpaRepository<Rota, Integer> {
    
}
