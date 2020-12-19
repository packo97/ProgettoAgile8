package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PazienteController {

  // CHANGE TO USE DTO
  @Autowired
  private PazienteService pazienteService;

  @GetMapping("/paziente/{email}")
  public ResponseEntity<PazienteDTO> get(@PathVariable("email") String email) {
    System.out.println("Get paziente by Email");
    return ResponseEntity.ok(pazienteService.getPazienteByEmail(email));
  }


  @PostMapping(path = "/paziente")
  public ResponseEntity<PazienteDTO> add(@RequestBody PazienteDTO paziente) throws NoSuchAlgorithmException {
    PazienteDTO p = pazienteService.addPaziente(paziente);
    return ResponseEntity.ok(p);
  }
  @PostMapping(path = "/loginPaziente")
  public ResponseEntity<Boolean> login(@RequestBody DatiLogin datiLogin) {
    Boolean p = pazienteService.login(datiLogin.getEmail(), datiLogin.getPassword());
    return ResponseEntity.ok(p);
  }

  @GetMapping(path= "/pazienti")
  public ResponseEntity<List<PazienteDTO>> get(){
    List<PazienteDTO> lista = pazienteService.getPazienti();
    return ResponseEntity.ok(lista);
  }

  @GetMapping(path= "/pazienti/{valoreRicerca}")
  public ResponseEntity<List<PazienteDTO>> ricerca(@PathVariable("valoreRicerca") String valoreRicerca){
    List<PazienteDTO> lista = pazienteService.ricerca(valoreRicerca);
    return ResponseEntity.ok(lista);
  }

  }
