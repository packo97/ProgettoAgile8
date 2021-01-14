package it.unical.demacs.inf.asd.ProgettoAgile8.controller;




import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.Filtro;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
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
public class DottoreController {

  // CHANGE TO USE DTO
  @Autowired
  private DottoreService dottoreService;
  @Autowired
  private NotificaService notificaService;

  @PostMapping( path= "/inviaEmailDottore")
  public ResponseEntity<RecuperaPasswordDTO> inviaCodice(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO) {
    String codice = SendEmail.getInstance().sendMailCodice(recuperaPasswordDTO.getEmail());
    recuperaPasswordDTO.setCodice(codice);
    return ResponseEntity.ok(recuperaPasswordDTO);
  }

  @PostMapping( path= "/modificaPasswordDottore")
  public ResponseEntity<RecuperaPasswordDTO> modificaPassword(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO) {

    String testo="La sua password è stata modificata correttamente!";
    String oggetto="Modifica Password";
    NotificaDTO notifica = inserisciNotifica(null,testo,oggetto,"dottore","",dottoreService.getDottoreByEmail(recuperaPasswordDTO.getEmail()).getId());
    notificaService.save(notifica);

    dottoreService.modificaPassword(recuperaPasswordDTO);

    return ResponseEntity.ok(recuperaPasswordDTO);
  }


  // usare dto nel response e anche nel requestbody
  @PostMapping(path = "/dottore")
  public ResponseEntity<DottoreDTO> add(@RequestBody DottoreDTO dottore) throws NoSuchAlgorithmException {
    DottoreDTO d = dottoreService.addDottore(dottore);
    System.out.println("sono qui");
    return ResponseEntity.ok(d);
  }

  @GetMapping(path = "/dottore")
  public ResponseEntity<List<DottoreDTO>> get(){
    List<DottoreDTO> lista = dottoreService.getAll();
    return ResponseEntity.ok(lista);
  }


  @PostMapping(path = "/loginDottore")
  public ResponseEntity<Boolean> login(@RequestBody DatiLogin datiLogin) {
    Boolean p = dottoreService.login(datiLogin.getEmail(), datiLogin.getPassword());
    return ResponseEntity.ok(p);
  }

  @GetMapping("/dottore/{email}")
  public ResponseEntity<DottoreDTO> get(@PathVariable("email") String email) {
    System.out.println("Get dottore by Email");
    System.out.println(email);
    return ResponseEntity.ok(dottoreService.getDottoreByEmail(email));
  }

  @PutMapping(path = "/dottore")
  public ResponseEntity<DottoreDTO> update(@RequestBody DottoreDTO dottoreDTO){
    DottoreDTO d = dottoreService.updateDottore(dottoreDTO);
    return ResponseEntity.ok(d);
  }

  @PutMapping(path = "/updatePasswordDottore")
  public HttpStatus updatePassword(@RequestBody Filtro filtro){
    String passwordVecchia = filtro.getPasswordVecchia();
    String passwordNuova = filtro.getPasswordNuova();
    DottoreDTO dto = filtro.getDottore();
    if(dottoreService.controllaPassword(passwordVecchia, dto)){
      dottoreService.updatePassword(passwordNuova, dto);
      return HttpStatus.OK;
    }
    else
      return HttpStatus.BAD_REQUEST;

  }
  @PostMapping("/uploadImageDottore")
  public HttpStatus uplaodImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("dottoreID") Long dottoreID) throws IOException {
    byte[] bytes = file.getBytes();
    dottoreService.updateImg(bytes,dottoreID);
    return HttpStatus.OK;
  }



public static NotificaDTO inserisciNotifica(Long id, String testo, String oggetto,String ricevitore, String dottore, Long dottoreId){
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
