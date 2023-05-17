package Interface;
import java.util.List;
import domain.entities.Rota;

public interface IRotaRep {
    Rota findById(int rotaId);
    List<Rota> findRotas();
    
}
