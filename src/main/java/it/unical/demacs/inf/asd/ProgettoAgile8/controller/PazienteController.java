package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.Filtro;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
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
  public static Notifica inserisciNotifica(Long id, String testo, String oggetto, String ricevitore, String dottore, Long dottoreId){
    Notifica notifica = new Notifica();
    notifica.setPaziente(id);
    notifica.setVista(false);
    notifica.setTesto(testo);
    notifica.setOggetto(oggetto);
    notifica.setRicevitore(ricevitore);
    notifica.setData(LocalDateTime.now());
    notifica.setDottoreId(dottoreId);
    notifica.setDottore(dottore);
    return notifica;
  }

  @PostMapping("/uploadImagePaziente")
  public HttpStatus uplaodImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("pazienteID") Long pazienteID) throws IOException {
    byte[] bytes = file.getBytes();
    pazienteService.updateImg(bytes,pazienteID);
    return HttpStatus.OK;
  }
  @PutMapping(path = "/paziente")
  public ResponseEntity<PazienteDTO> update(@RequestBody PazienteDTO pazienteDTO){
    PazienteDTO p = pazienteService.updatePaziente(pazienteDTO);
    return ResponseEntity.ok(p);
  }

  @PutMapping(path = "/updatePasswordPaziente")
  public HttpStatus updatePassword(@RequestBody Filtro filtro){
    String passwordVecchia = filtro.getPasswordVecchia();
    String passwordNuova = filtro.getPasswordNuova();
    PazienteDTO dto = filtro.getPaziente();
    if(pazienteService.controllaPassword(passwordVecchia, dto)){
      pazienteService.updatePassword(passwordNuova, dto);
      return HttpStatus.OK;
    }
    else
      return HttpStatus.BAD_REQUEST;
  }

  }
