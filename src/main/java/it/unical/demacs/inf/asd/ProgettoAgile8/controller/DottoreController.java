package it.unical.demacs.inf.asd.ProgettoAgile8.controller;




import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DottoreController {

  // CHANGE TO USE DTO
  @Autowired
  private DottoreService dottoreService;

  // usare dto nel response e anche nel requestbody
  @PostMapping(path = "/dottore")
  public ResponseEntity<DottoreDTO> add(@RequestBody DottoreDTO dottore) {
    DottoreDTO d = dottoreService.addDottore(dottore);
    return ResponseEntity.ok(d);
  }
  /*
  @PostMapping(path = "/loginDottore")
  public ResponseEntity<Boolean> get(@RequestBody String email, @RequestBody String password) {
    Boolean p = dottoreService.login(email, password);
    return ResponseEntity.ok(p);
  }
*/
  @PostMapping(path = "/loginDottore")
  public ResponseEntity<Boolean> login(@RequestBody DatiLogin datiLogin) {
    Boolean p = dottoreService.login(datiLogin.getEmail(), datiLogin.getPassword());
    return ResponseEntity.ok(p);
  }

}
