package com.example.demo.persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.entities.Aerovia;

public interface IAeroviaCRUD extends JpaRepository<Aerovia, Integer> {
    
}
