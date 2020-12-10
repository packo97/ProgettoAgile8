package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/prenotazione")
    public ResponseEntity<PrenotazioneDTO> add(@RequestBody PrenotazioneDTO prenotazione){
        System.out.println(prenotazione);
        PrenotazioneDTO p = prenotazioneService.addPrenotazione(prenotazione);
        return ResponseEntity.ok(p);
    }




}
