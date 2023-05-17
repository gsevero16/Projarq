package application.service;

import com.dev.trabProjarq.Aplicacao.DTO.PlanoVooDTO;
import com.dev.trabProjarq.dominio.entities.*;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.domain.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PlanoDeVooService {
    private IPlanosRep planosRep;
    private IRotasRep rotasRep;
    private IOcupacaoAeroviaRep ocupacaoRep;

    @Autowired
    public PlanoDeVooService(IPlanosRep planosRep, IRotasRep rotasRep, IOcupacaoAeroviaRep ocupacaoRep) {
        this.planosRep = planosRep;
        this.rotasRep = rotasRep;
        this.ocupacaoRep = ocupacaoRep;
    }

    public List<Aerovia> verificarPlanoDeVoo(PlanoVooDTO propostaPlano) {
        Rota rotaEscolhida = this.rotasRep.findById(propostaPlano.rotaId);

        List<Aerovia> trechosComProblemas = new ArrayList<>();

        for (Aerovia aerovia: rotaEscolhida.aerovias) {
            List<Float> slotsHorarios = new ArrayList<>();

            float tempoVoo = aerovia.distancia / propostaPlano.velCruzeiro;

            tempoVoo = tempoVoo + propostaPlano.horarioPartida;
            
            slotsHorarios.add((float) Math.floor(propostaPlano.horarioPartida));

            while(tempoVoo > propostaPlano.horarioPartida){
                slotsHorarios.add((float) Math.floor(propostaPlano.horarioPartida));
                tempoVoo--;
            }

            List<OcupacaoAerovia> ocupadas = this.ocupacaoRep.findOcupadasSlots(
                aerovia.id, 
                propostaPlano.data, 
                slotsHorarios
            );
            for(OcupacaoAerovia ocupacao: ocupadas) {
                if (ocupacao.slot_altitude == propostaPlano.altitude) {
                    trechosComProblemas.add(aerovia);
                }
            }
        }

        return trechosComProblemas;
    }

    public PlanoDeVoo cancelarPlanoDeVoo(int id) {
        PlanoDeVoo plano = this.planosRep.findPlanoById(id);
        if(plano != null){
            Rota rota = plano.rota;
            List<Aerovia> aerovias = rota.aerovias;

            for(Aerovia aerovia: aerovias) {
                List<Float> slotsHorarios = new ArrayList<>();

                float tempoVoo = aerovia.distancia / plano.velCruzeiro;

                for (int i = 0; i < tempoVoo; i++) {
                    slotsHorarios.add((float) Math.floor(plano.horarioPartida+i));
                }
                List<OcupacaoAerovia> slotsOcupados = this.ocupacaoRep.findOcupadasSlots(aerovia.id, plano.data, slotsHorarios).stream()
                        .filter(o -> o.slot_altitude == plano.altitude)
                        .collect(Collectors.toList());

                for (OcupacaoAerovia slot : slotsOcupados) {
                    this.ocupacaoRep.removeOcupacao(slot);
                }
            }
            this.planosRep.removePlano(plano);
        }

        return plano;
    }

    public PlanoDeVoo autorizarPlanoDeVoo(PlanoVooDTO planoVoo) {
        if(this.verificarPlanoDeVoo(planoVoo).isEmpty()){
            Rota rota = this.rotasRep.findById(planoVoo.rotaId);
            PlanoDeVoo planoDeVoo = new PlanoDeVoo(planoVoo.horarioPartida, planoVoo.data, planoVoo.altitude, planoVoo.velCruzeiro, rota);
            for(Aerovia aerovia: rota.aerovias) {
                List<Float> slotsHorarios = new ArrayList<>();

                float tempoVoo = aerovia.distancia / planoDeVoo.velCruzeiro;

                for(int i=0; i<tempoVoo; i++){
                    slotsHorarios.add((float) Math.floor(planoDeVoo.horarioPartida+ i));
                }

                for(float slot: slotsHorarios){
                    LocalDate date = planoVoo.data;
                    if(slot > 24){
                        slot = slot - 24;
                        date = date.plusDays(1);
                    }
                    OcupacaoAerovia ocupacaoAerovia = new OcupacaoAerovia(date, aerovia, (int)planoVoo.altitude, (int)slot);
                    this.ocupacaoRep.ocupa(ocupacaoAerovia);
                }
            }
            return this.planosRep.salvaPlano(planoDeVoo);
        }
        return null;
    }
}
