package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.Nuovimessaggi;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.DottoreService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private DottoreService dottoreService;

    @GetMapping(path= "/notificheBySegretaria")
    public ResponseEntity<List<NotificaDTO>> ricercaNotificheSegretaria(){
        List<NotificaDTO> lista = notificaService.findAllBySegretaria();
        notificaService.setAllVistaBySegretaria();
        return ResponseEntity.ok(lista);
    }


    @GetMapping(path= "/notificheByPaziente/{email}")
    public ResponseEntity<List<NotificaDTO>> ricercaNotifichePaziente(@PathVariable("email") String email){
        PazienteDTO p = pazienteService.getPazienteByEmail(email);
        List<NotificaDTO> lista = notificaService.findAllByPaziente(p);
        notificaService.setAllVista(p);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path= "/notificheByDottore/{email}")
    public ResponseEntity<List<NotificaDTO>> ricercaNotificheDottore(@PathVariable("email") String email){
        DottoreDTO dottore = dottoreService.getDottoreByEmail(email);
        List<NotificaDTO> lista = notificaService.findAllByDottore(dottore.getId());
        notificaService.setAllVistaByDottore(dottore.getId());
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path= "/newNotificheByPaziente/{email}")
    public ResponseEntity<Nuovimessaggi> nuoveNotifiche(@PathVariable("email") String email){
        PazienteDTO p = pazienteService.getPazienteByEmail(email);
        if(p!=null) {
            List<NotificaDTO> lista = notificaService.findAllByPaziente(p);
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

    @GetMapping(path= "/newNotificheBySegretaria")
    public ResponseEntity<Nuovimessaggi> nuoveNotificheSegretaria(){
        List<NotificaDTO> lista = notificaService.findAllBySegretaria();
        Nuovimessaggi nuovimessaggi = new Nuovimessaggi();
        if (lista.size()>0 && lista.get(0).getVista() == true)
            nuovimessaggi.setNuoviMessaggi("false");
        else if (lista.size()== 0)
            nuovimessaggi.setNuoviMessaggi("false");
        else
            nuovimessaggi.setNuoviMessaggi("true");
        return ResponseEntity.ok(nuovimessaggi);
    }
    @GetMapping(path= "/newNotificheByDottore/{email}")
    public ResponseEntity<Nuovimessaggi> nuoveNotificheDottore(@PathVariable("email") String email){
        DottoreDTO p = dottoreService.getDottoreByEmail(email);
        if(p!=null) {
            List<NotificaDTO> lista = notificaService.findAllByDottore(p.getId());
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
        Optional<Notifica> n = notificaService.findById(id);
        notificaService.deletePrenotazione(id);
        return HttpStatus.OK;
    }
}
