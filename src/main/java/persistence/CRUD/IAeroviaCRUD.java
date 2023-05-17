package persistence.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import domain.entities.Aerovia;

public interface IAeroviaCRUD extends JpaRepository<Aerovia, Integer> {
    
}
