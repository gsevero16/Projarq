package persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import domain.entities.Rota;

public interface IRotaCRUD extends JpaRepository<Rota, Integer> {
    
}
