package Interface;

import java.util.Optional;
import domain.entities.Aerovia;

public interface IAeroviaRep {
    Optional<Aerovia> findAerovia(int aeroviaId);
    
}
