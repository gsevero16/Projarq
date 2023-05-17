package persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.entities.PlanoDeVoo;

public interface IPlanoVooCRUD extends JpaRepository<PlanoDeVoo, Integer> {
    
}
