package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.Nuovimessaggi;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrenotazioneService;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificaController {

    @Autowired
    private NotificaService notificaService;

    @Autowired
    private PazienteService pazienteService;



    @GetMapping(path= "/notificheBySegretaria")
    public ResponseEntity<List<NotificaDTO>> ricercaNotificheSegretaria(){
        List<NotificaDTO> lista = notificaService.findAllBySegretaria();
        notificaService.setAllVistaBySegretaria();
        Collections.reverse(lista);
        return ResponseEntity.ok(lista);
    }


    @GetMapping(path= "/notificheByPaziente/{email}")
    public ResponseEntity<List<NotificaDTO>> ricercaNotifichePaziente(@PathVariable("email") String email){
        PazienteDTO p = pazienteService.getPazienteByEmail(email);
        System.out.println(p);
        List<NotificaDTO> lista = notificaService.findAllByPaziente(p);
        notificaService.setAllVista(p);
        Collections.reverse(lista);
        return ResponseEntity.ok(lista);
    }
    @GetMapping(path= "/newNotificheByPaziente/{email}")
    public ResponseEntity<Nuovimessaggi> nuoveNotifiche(@PathVariable("email") String email){
        PazienteDTO p = pazienteService.getPazienteByEmail(email);
        if(p!=null) {
            System.out.println(p);
            List<NotificaDTO> lista = notificaService.findAllByPaziente(p);
            Collections.reverse(lista);
            Nuovimessaggi nuovimessaggi = new Nuovimessaggi();
            if (lista.size()>0 && lista.get(0).getVista() == true)
                nuovimessaggi.setNuoviMessaggi("false");
            else if (lista.size()== 0)
                nuovimessaggi.setNuoviMessaggi("false");
            else
                nuovimessaggi.setNuoviMessaggi("true");
            return ResponseEntity.ok(nuovimessaggi);
        }
        Nuovimessaggi nuovimessaggi = new Nuovimessaggi();
        nuovimessaggi.setNuoviMessaggi("false");
        return ResponseEntity.ok(nuovimessaggi);
    }
    @DeleteMapping(path = "/cancellaMessaggio/{id}")
    public HttpStatus delete(@PathVariable Long id){
        System.out.println("deleete messaggio");
        Optional<Notifica> n = notificaService.findById(id);
        notificaService.deletePrenotazione(id);
        SendEmail.getInstance().sendMailDelete(/*prenotazione.getPaziente().getEmail()*/ "niko97142@gmail.com");
        return HttpStatus.OK;
    }
}
