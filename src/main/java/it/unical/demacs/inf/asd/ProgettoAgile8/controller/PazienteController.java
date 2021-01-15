package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.Filtro;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;


import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;
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
  public ResponseEntity<RecuperaPasswordDTO> modificaPassword(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO) {
    String testo="La sua password è stata modificata correttamente!";
    String oggetto="Modifica Password";
    PazienteDTO paziente = pazienteService.getPazienteByEmail(recuperaPasswordDTO.getEmail());
    NotificaDTO notifica = inserisciNotifica(paziente.getId(),testo,oggetto,"paziente","",null);
    notificaService.save(notifica);
    pazienteService.modificaPassword(recuperaPasswordDTO);
    return ResponseEntity.ok(recuperaPasswordDTO);
  }

  @GetMapping("/paziente/{email}")
  public ResponseEntity<PazienteDTO> get(@PathVariable("email") String email) {
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

  public static NotificaDTO inserisciNotifica(Long id, String testo, String oggetto, String ricevitore, String dottore, Long dottoreId){
    NotificaDTO notifica = new NotificaDTO();
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
