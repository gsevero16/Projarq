package persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.entities.OcupacaoAerovia;

public interface IOcupacaoAeroviaCRUD extends JpaRepository<OcupacaoAerovia, Integer> {
    
}
