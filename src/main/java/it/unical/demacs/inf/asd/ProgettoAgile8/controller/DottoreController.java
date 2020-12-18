package it.unical.demacs.inf.asd.ProgettoAgile8.controller;




import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DottoreController {

  // CHANGE TO USE DTO
  @Autowired
  private DottoreService dottoreService;

  // usare dto nel response e anche nel requestbody
  @PostMapping(path = "/dottore")
  public ResponseEntity<DottoreDTO> add(@RequestBody DottoreDTO dottore) throws NoSuchAlgorithmException {
    DottoreDTO d = dottoreService.addDottore(dottore);
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
    return ResponseEntity.ok(dottoreService.getDottoreByEmail(email));
  }

}
