package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;


import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Sicurezza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
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

  @Autowired
  private NotificaService notificaService;

  @PostMapping( path= "/inviaEmailPaziente")
  public ResponseEntity<RecuperaPasswordDTO> inviaCodice(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO) {
    String codice = SendEmail.getInstance().sendMailCodice(recuperaPasswordDTO.getEmail());
    recuperaPasswordDTO.setCodice(codice);
    return ResponseEntity.ok(recuperaPasswordDTO);
  }

  @PostMapping( path= "/modificaPasswordPaziente")
  public ResponseEntity<RecuperaPasswordDTO> modificaPassword(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO) throws NoSuchAlgorithmException {

    pazienteService.modificaPassword(recuperaPasswordDTO);

    String testo="La sua password è stata modificata correttamente!";
    String oggetto="Modifica Password";
    Notifica notifica = inserisciNotifica(pazienteService.getPazienteByEmail(recuperaPasswordDTO.getEmail()).getId(),testo,oggetto,"paziente","",null);
    notificaService.save(notifica);


    return ResponseEntity.ok(recuperaPasswordDTO);
  }

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
  public static Notifica inserisciNotifica(Long id, String testo, String oggetto,String ricevitore, String dottore, Long dottoreId){
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
  }
