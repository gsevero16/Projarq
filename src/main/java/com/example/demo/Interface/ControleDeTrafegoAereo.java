package com.example.demo.Interface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.application.AutorizaPlanoDeVoo;
import com.example.demo.application.CancelaPlanoDeVoo;
//import com.example.demo.application.ConsultaAeronave;
import com.example.demo.application.ConsultaRelatorio;
import com.example.demo.application.ConsultaRota;
import com.example.demo.application.ConsultaSlot;
import com.example.demo.application.service.ConsultaPlanoDeVoo;
import com.example.demo.domain.DTO.PlanoDeVooDTO;
import com.example.demo.domain.DTO.RelatorioDTO;
import com.example.demo.domain.DTO.RotaDTO;
import com.example.demo.domain.entities.Aerovia;
import com.example.demo.domain.entities.PlanoDeVoo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ControleDeTrafegoAereo")
public class ControleDeTrafegoAereo {
	//private ConsultaAeronave consultaAeronave;
	private ConsultaRota consultaRotas;
    private ConsultaSlot consultaSlot;
    private ConsultaRelatorio consultaRelatorio;
    private ConsultaPlanoDeVoo consultaPlanoDeVoo;
	private AutorizaPlanoDeVoo autorizaPlanoDeVoo;
	private CancelaPlanoDeVoo cancelaPlanoDeVoo;

    @Autowired
    public ControleDeTrafegoAereo(
           // ConsultaAeronave consultaAeronave,
			ConsultaRota ConsultaRotas, 
			ConsultaSlot ConsultaSlot,
            ConsultaPlanoDeVoo consultaPlanoDeVoo, 
			ConsultaRelatorio consultaRelatorio,
			AutorizaPlanoDeVoo autorizaPlanoDeVoo,
            CancelaPlanoDeVoo cancelaPlanoDeVoo) {
           // this.consultaAeronave = consultaAeronave;
            this.consultaRotas = ConsultaRotas;
            this.consultaSlot = ConsultaSlot;
            this.consultaRelatorio = consultaRelatorio;
            this.consultaPlanoDeVoo = consultaPlanoDeVoo;
            this.autorizaPlanoDeVoo = autorizaPlanoDeVoo;
            this.cancelaPlanoDeVoo = cancelaPlanoDeVoo;
    }

    @GetMapping("/rotas")
    @CrossOrigin(origins = "*")
    public List<RotaDTO> consultaRotas(@RequestParam("destino") String destino,
            @RequestParam("origem") String origem) {
        return this.consultaRotas(destino, origem);
    }

    @GetMapping("/altitudesLivres/{aeroviaId}")
    @CrossOrigin(origins = "*")
    public List<Integer> consultaAltitudesLivres(@PathVariable int aeroviaId, @RequestParam("data") String data,
            @RequestParam("horario") float horario, @RequestParam("velocidade") float velCruzeiro) {
        LocalDate dataObj = LocalDate.parse(data);
        return this.consultaSlot.consultaSlotsLivres(aeroviaId, dataObj, null, velCruzeiro);
    }

    @PostMapping("/verificaPlanoDeVoo")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Aerovia>> verificaPlanoDeVoo(@RequestBody PlanoDeVooDTO planoVoo) {
        List<Aerovia> lista = this.consultaPlanoDeVoo.verificaPlanoDeVoo(planoVoo);

        if (!lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lista);
        }
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping("/AutorizaPlanoDeVoo")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PlanoDeVoo> AutorizaPlanoDeVoo(@RequestBody PlanoDeVooDTO planoVoo) {
        PlanoDeVoo plano = this.autorizaPlanoDeVoo.autorizaPlanoDeVoo(planoVoo);
        if (plano != null) {
            return ResponseEntity.status(HttpStatus.OK).body(plano);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @DeleteMapping("/cancelaPlanoDeVoo/{planoId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PlanoDeVoo> cancelaPlanoDeVoo(@PathVariable int planoId) {
        PlanoDeVoo plano = this.cancelaPlanoDeVoo.cancelaPlano(planoId);

        if (plano == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(plano);
        }
        return ResponseEntity.status(HttpStatus.OK).body(plano);
    }

    @GetMapping("/geraRelatorio/{aeroviaId}")
    @CrossOrigin(origins = "*")
    public RelatorioDTO geraRelatorio(@PathVariable int aeroviaId, @RequestParam("data") String data){
        LocalDate dataObj = LocalDate.parse(data);
        return this.consultaRelatorio.consultaRelatorio(aeroviaId, dataObj);
    }
}

