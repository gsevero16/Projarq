package application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Interface.IOcupacaoAeroviaRep;
import Interface.IPlanoVooRep;
import Interface.IRotaRep;
import domain.DTO.PlanoDeVooDTO;
import domain.entities.Aerovia;
import domain.entities.OcupacaoAerovia;
import domain.entities.PlanoDeVoo;
import domain.entities.Rota;


@Service
public class PlanoDeVooService {
    private IPlanoVooRep iPlanoVooRep;
    private IRotaRep iRotaRep;
    private IOcupacaoAeroviaRep iOcupacaoAeroviaRep;

    @Autowired
    public PlanoDeVooService(IPlanoVooRep plano, IRotaRep rota, IOcupacaoAeroviaRep ocupacao) {
        this.iPlanoVooRep = plano;
        this.iRotaRep = rota;
        this.iOcupacaoAeroviaRep = ocupacao;
    }

    public List<Aerovia> avaliaPlanoDeVoo(PlanoDeVooDTO orcamento) {
        Rota rotaEscolhida = this.iRotaRep.findById(orcamento.rotaId);

        List<Aerovia> inconsistencias = new ArrayList<>();

        for (Aerovia aerovia: rotaEscolhida.aerovias) {
            List<Float> horarioSlots = new ArrayList<>();

            float tempoVoo = aerovia.distancia / orcamento.velocidadeCruzeiro;

            tempoVoo = tempoVoo + orcamento.horarioPartida;
            
            horarioSlots.add((float) Math.floor(orcamento.horarioPartida));

            while(tempoVoo > orcamento.horarioPartida){
                horarioSlots.add((float) Math.floor(orcamento.horarioPartida));
                tempoVoo--;
            }

            List<OcupacaoAerovia> ocupadas = this.iOcupacaoAeroviaRep.findOcupacaoAerovias(
                aerovia.id, 
                orcamento.data, 
                horarioSlots
            );
            for(OcupacaoAerovia ocupacao: ocupadas) {
                if (ocupacao.slot_altitude == orcamento.altitude) {
                    inconsistencias.add(aerovia);
                }
            }
        }

        return inconsistencias;
    }

    public PlanoDeVoo cancelaPlanoDeVoo(int id) {
        PlanoDeVoo plano = this.iPlanoVooRep.findPlanoDeVooById(id);
        if(plano != null){
            Rota rota = plano.rota;
            List<Aerovia> aerovias = rota.aerovias;

            for(Aerovia aerovia: aerovias) {
                List<Float> horarioSlots = new ArrayList<>();

                float tempoVoo = aerovia.distancia / plano.velocidadeCruzeiro;

                for (int i = 0; i < tempoVoo; i++) {
                    horarioSlots.add((float) Math.floor(plano.horarioPartida+i));
                }
                List<OcupacaoAerovia> slotsOcupados = this.iOcupacaoAeroviaRep.findOcupacaoAerovias(
                    aerovia.id, 
                    plano.data, 
                    horarioSlots)
                        .stream()
                        .filter(o -> o.slot_altitude == plano.altitude)
                        .collect(Collectors.toList());

                for (OcupacaoAerovia slot : slotsOcupados) {
                    this.iOcupacaoAeroviaRep.excluiOcupacaoAerovia(slot);
                }
            }
            this.iPlanoVooRep.deletaPlanoDeVoo(plano);
        }

        return plano;
    }

    public PlanoDeVoo autorizarPlanoDeVoo(PlanoDeVooDTO plano) {
        if(this.avaliaPlanoDeVoo(plano).isEmpty()){
            Rota rota = this.iRotaRep.findById(plano.rotaId);
            PlanoDeVoo planoDeVoo = new PlanoDeVoo(plano.horarioPartida, plano.data, plano.altitude, plano.velocidadeCruzeiro, rota);
            for(Aerovia aerovia: rota.aerovias) {
                List<Float> horarioSlots = new ArrayList<>();

                float tempoVoo = aerovia.distancia / planoDeVoo.velocidadeCruzeiro;

                for(int i=0; i<tempoVoo; i++){
                    horarioSlots.add((float) Math.floor(planoDeVoo.horarioPartida+ i));
                }

                for(float slot: horarioSlots){
                    LocalDate date = plano.data;
                    if(slot > 24){
                        slot = slot - 24;
                        date = date.plusDays(1);
                    }
                    OcupacaoAerovia ocupacaoAerovia = new OcupacaoAerovia(date, aerovia, (int)plano.altitude, (int)slot);
                    this.iOcupacaoAeroviaRep.ocupacaoAerovia(ocupacaoAerovia);
                }
            }
            return this.iPlanoVooRep.savePlanoDeVoo(planoDeVoo);
        }
        return null;
    }
}
