package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrazioneController {

  // CHANGE TO USE DTO
  @Autowired
  private PazienteService pazienteService;

  // usare dto nel response e anche nel requestbody
  @PostMapping(path = "/paziente")
  public ResponseEntity<PazienteDTO> add(@RequestBody PazienteDTO paziente) {
    //usare dto
    System.out.println("controller " + paziente.getNome() + paziente.getCognome());
    PazienteDTO p = pazienteService.addPaziente(paziente);
    return ResponseEntity.ok(p);
  }
/*
  @GetMapping("/paziente")
  public ResponseEntity<String> get() {
    System.out.println("controller");
    return ResponseEntity.ok(String.format("Hellooooo %s!!!", "a"));
  }
*/
  }
