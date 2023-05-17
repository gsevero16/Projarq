package Interface;
import java.util.List;

import domain.entities.PlanoDeVoo;

public interface IPlanoVooRep {
    List<PlanoDeVoo> findPlanoDeVoos();
    PlanoDeVoo savePlanoDeVoo(PlanoDeVoo planoDeVoo);
    PlanoDeVoo findPlanoDeVooById(int planoDeVooId);
    void deletaPlanoDeVoo(PlanoDeVoo planoDeVoo);
    
}
