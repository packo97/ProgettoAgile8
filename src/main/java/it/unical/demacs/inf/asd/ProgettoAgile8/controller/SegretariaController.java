package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.Filtro;
import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.SegretariaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SegretariaController {

    @Autowired
    private SegretariaService segretariaService;
    @Autowired
    private NotificaService notificaService;

    @PostMapping( path= "/inviaEmailSegretaria")
    public ResponseEntity<RecuperaPasswordDTO> inviaCodice(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO) {
        String codice = SendEmail.getInstance().sendMailCodice(recuperaPasswordDTO.getEmail());
        recuperaPasswordDTO.setCodice(codice);
        return ResponseEntity.ok(recuperaPasswordDTO);
    }

    @PostMapping( path= "/modificaPasswordSegretaria")
    public ResponseEntity<RecuperaPasswordDTO> modificaPassword(@RequestBody RecuperaPasswordDTO recuperaPasswordDTO){
        segretariaService.modificaPassword(recuperaPasswordDTO);
        String testo="La sua password è stata modificata correttamente!";
        String oggetto="Modifica Password";
        NotificaDTO notifica = inserisciNotifica(null,testo,oggetto,"segretaria","",null);
        notificaService.save(notifica);
        return ResponseEntity.ok(recuperaPasswordDTO);
    }

    @PostMapping(path = "/segretaria")
    public ResponseEntity<SegretariaDTO> add(@RequestBody SegretariaDTO segretaria) throws NoSuchAlgorithmException {
        SegretariaDTO s = segretariaService.getSegretariaByEmail(segretaria.getEmail());
        if(s==null) {
            SegretariaDTO seg = segretariaService.addSegretaria(segretaria);
            return ResponseEntity.ok(seg);
        }
        else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path = "/loginSegretaria")
    public ResponseEntity<Boolean> login(@RequestBody DatiLogin datiLogin) {
        Boolean p = segretariaService.login(datiLogin.getEmail(), datiLogin.getPassword());
        return ResponseEntity.ok(p);
    }

    @GetMapping("/segretaria/{email}")
    public ResponseEntity<SegretariaDTO> get(@PathVariable("email") String email) {
        return ResponseEntity.ok(segretariaService.getSegretariaByEmail(email));
    }

    @PutMapping(path = "/segretaria")
    public ResponseEntity<SegretariaDTO> update(@RequestBody SegretariaDTO segretariaDTO){
        SegretariaDTO s = segretariaService.updateSegretaria(segretariaDTO);
        return ResponseEntity.ok(s);
    }

    @PutMapping(path = "/updatePasswordSegretaria")
    public HttpStatus updatePassword(@RequestBody Filtro filtro){
        String passwordVecchia = filtro.getPasswordVecchia();
        String passwordNuova = filtro.getPasswordNuova();
        SegretariaDTO dto = filtro.getSegretaria();
        if(segretariaService.controllaPassword(passwordVecchia, dto)){
            segretariaService.updatePassword(passwordNuova, dto);
            return HttpStatus.OK;
        }
        else
            return HttpStatus.BAD_REQUEST;

    }

    @PostMapping("/uploadImageSegretaria")
    public HttpStatus uplaodImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("segretariaID") Long segretariaID) throws IOException {
        byte[] bytes = file.getBytes();
        segretariaService.updateImg(bytes,segretariaID);
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
