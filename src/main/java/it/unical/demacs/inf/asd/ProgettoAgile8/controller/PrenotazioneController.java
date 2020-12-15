package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping(path = "/prenotazioniByPaziente")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByPaziente(@RequestBody PazienteDTO paziente){
        List<PrenotazioneDTO> lista = prenotazioneService.getPrenotazioniByPaziente(paziente);
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/prenotazioniByDoctor")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByDoctor(@RequestBody DottoreDTO dottore){
        List<PrenotazioneDTO> lista = prenotazioneService.getPrenotazioniByDoctor(dottore, true);
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/richiesteByDoctor")
    public ResponseEntity<List<PrenotazioneDTO>> getRichiesteByDoctor(@RequestBody DottoreDTO dottore){
        List<PrenotazioneDTO> lista = prenotazioneService.getPrenotazioniByDoctor(dottore, false);
        return ResponseEntity.ok(lista);
    }


    @GetMapping(path = "/urgentiNonAccettati")
    public ResponseEntity<List<PrenotazioneDTO>> getUrgentiNonConfermate(){
       List<PrenotazioneDTO> lista = prenotazioneService.getUrgentiNonConfermate();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/inAttesa")
    public ResponseEntity<List<PrenotazioneDTO>> getInAttesa(){
        List<PrenotazioneDTO> lista = prenotazioneService.getInAttesa();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/accettati")
    public ResponseEntity<List<PrenotazioneDTO>> getAccettati(){
        List<PrenotazioneDTO> lista = prenotazioneService.getAccettate();
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/prenotazione")
    public ResponseEntity<PrenotazioneDTO> add(@RequestBody PrenotazioneDTO prenotazione){
        System.out.println(prenotazione);
        PrenotazioneDTO p = prenotazioneService.addPrenotazione(prenotazione);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(path = "/prenotazione/{id}")
    public HttpStatus delete(/*@RequestBody PrenotazioneDTO prenotazione*/@PathVariable Long id){
        System.out.println("deleete prenotazione");
        prenotazioneService.deletePrenotazione(id);
        return HttpStatus.OK;
    }




}
