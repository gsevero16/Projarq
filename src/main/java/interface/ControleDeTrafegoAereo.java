package Interface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import application.AutorizaPlanoDeVoo;
import application.CancelaPlanoDeVoo;
import application.ConsultaAeronave;
import application.ConsultaRelatorio;
import application.ConsultaRota;
import application.ConsultaSlot;
import application.service.ConsultaPlanoDeVoo;
import domain.DTO.PlanoDeVooDTO;
import domain.DTO.RelatorioDTO;
import domain.DTO.RotaDTO;
import domain.entities.Aerovia;
import domain.entities.PlanoDeVoo;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication

public class ControleDeTrafegoAereo {
	private ConsultaAeronave consultaAeronave;
	private ConsultaRota consultaRota;
    private ConsultaSlot consultaSlot;
    private ConsultaRelatorio consultaRelatorio;
	private AutorizaPlanoDeVoo autorizaPlanoDeVoo;
    private ConsultaPlanoDeVoo consultaPlanoDeVoo;
	private CancelaPlanoDeVoo cancelaPlanoDeVoo;

    @Autowired
    public ControleDeTrafegoAereo(
			ConsultaRota ConsultaRota, 
			ConsultaSlot ConsultaSlot,
            ConsultaPlanoDeVoo consultaPlanoDeVoo, 
			AutorizaPlanoDeVoo autorizaPlanoDeVoo,
            CancelaPlanoDeVoo cancelaPlanoDeVoo, 
			ConsultaRelatorio consultaRelatorio) {
        this.consultaRelatorio = consultaRelatorio;
        this.consultaRota = ConsultaRota;
        this.consultaSlot = ConsultaSlot;
        this.consultaPlanoDeVoo = consultaPlanoDeVoo;
        this.autorizaPlanoDeVoo = autorizaPlanoDeVoo;
        this.cancelaPlanoDeVoo = cancelaPlanoDeVoo;
    }

    @GetMapping("/rotas")
    @CrossOrigin(origins = "*")
    public List<RotaDTO> consultaRotasDestinos(@RequestParam("destino") String destino,
            @RequestParam("origem") String origem) {
        return this.consultaRotasDestinos(destino, origem);
    }

    @GetMapping("/altitudes-livres/{aeroviaId}")
    @CrossOrigin(origins = "*")
    public List<Integer> consultaAltitudesLivres(@PathVariable int aeroviaId, @RequestParam("data") String data,
            @RequestParam("horario") float horario, @RequestParam("velocidade") float velCruzeiro) {
        LocalDate dataObj = LocalDate.parse(data);
        return this.consultaSlot.consultaSlotsLivres(aeroviaId, dataObj, null, velCruzeiro);
    }

    @PostMapping("/verifica-plano-voo")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Aerovia>> verificaPlanoDeVoo(@RequestBody PlanoDeVooDTO planoVoo) {
        List<Aerovia> lista = this.consultaPlanoDeVoo.verificaPlanoDeVoo(planoVoo);

        if (!lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lista);
        }
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping("/libera-plano")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PlanoDeVoo> liberarPlano(@RequestBody PlanoDeVooDTO planoVoo) {
        PlanoDeVoo plano = this.autorizaPlanoDeVoo.autorizaPlanoDeVoo(planoVoo);
        if (plano != null) {
            return ResponseEntity.status(HttpStatus.OK).body(plano);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/cancela-plano/{planoId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PlanoDeVoo> cancelaPlano(@PathVariable int planoId) {
        PlanoDeVoo plano = this.cancelaPlanoDeVoo.cancelaPlano(planoId);

        if (plano == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(plano);
        }
        return ResponseEntity.status(HttpStatus.OK).body(plano);
    }

    @GetMapping("/relatorio/{aeroviaId}")
    @CrossOrigin(origins = "*")
    public RelatorioDTO geraRelatorio(@PathVariable int aeroviaId, @RequestParam("data") String data){
        LocalDate dataObj = LocalDate.parse(data);
        return this.consultaRelatorio.consultaRelatorio(aeroviaId, dataObj);
    }
}

