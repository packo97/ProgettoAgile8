package it.unical.demacs.inf.asd.ProgettoAgile8.controller;


import it.unical.demacs.inf.asd.ProgettoAgile8.core.Filtro;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.NotificaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.NotificaService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrenotazioneService;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Data;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private NotificaService notificaService;

    @PostMapping(path = "/prenotazioniByPaziente")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByPaziente(@RequestBody PazienteDTO paziente){
        List<PrenotazioneDTO> lista = prenotazioneService.getPrenotazioniByPaziente(paziente);
        Collections.reverse(lista);
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/prenotazioniByDoctor")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByDoctor(@RequestBody DottoreDTO dottore){
        List<PrenotazioneDTO> lista = prenotazioneService.getPrenotazioniByDoctor(dottore, true);
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/prenotazioniByDoctorAndDate")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByDoctor(@RequestBody Filtro filtro){
        DottoreDTO dottore = filtro.getDottore();
        LocalDateTime date = filtro.getData();
        List<PrenotazioneDTO> lista = prenotazioneService.getPrenotazioniByDoctorAndDate(dottore,date, true);
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/richiesteByDoctor")
    public ResponseEntity<List<PrenotazioneDTO>> getRichiesteByDoctor(@RequestBody DottoreDTO dottore){
        List<PrenotazioneDTO> lista = prenotazioneService.getRichiesteByDoctor(dottore, false);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/urgentiNonAccettati")
    public ResponseEntity<List<PrenotazioneDTO>> getUrgentiNonConfermate(){
       List<PrenotazioneDTO> lista = prenotazioneService.getUrgentiNonConfermate();
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/urgentiNonAccettatiByDoctor")
    public ResponseEntity<List<PrenotazioneDTO>> getUrgentiNonConfermateByDoctor(@RequestBody DottoreDTO dottore){
        List<PrenotazioneDTO> lista = prenotazioneService.getUrgentiNonConfermateByDoctor(dottore);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/inAttesa")
    public ResponseEntity<List<PrenotazioneDTO>> getInAttesa(){
        List<PrenotazioneDTO> lista = prenotazioneService.getInAttesa();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/accettati")
    public ResponseEntity<List<PrenotazioneDTO>> getAccettati(){
        List<PrenotazioneDTO> lista = prenotazioneService.getAccettate();
        return ResponseEntity.ok(lista);
    }

    @PostMapping(path = "/prenotazione")
    public ResponseEntity<PrenotazioneDTO> add(@RequestBody PrenotazioneDTO prenotazione){
        PrenotazioneDTO p = prenotazioneService.addPrenotazione(prenotazione);
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(path = "/prenotazione/{id}")
    public HttpStatus delete(@PathVariable Long id){
        Prenotazione p = prenotazioneService.getById(id);
        if(p.isConfermato()==true && p.getData_visita()!=null) {
            String testo = "La prenotazione del paziente " + p.getPaziente().getNome() + " " + p.getPaziente().getCognome() + " riguardante " + p.getDescrizione() + " e stabilita per la data " + Data.convertiData(p.getData_visita().toString()) + " è stata annullata.";
            String oggetto = "Annullamento prenotazione";
            NotificaDTO notifica = inserisciNotifica(p.getPaziente().getId(), testo, oggetto, "segretaria", p.getDottore().getNome() + " " + p.getDottore().getCognome(), p.getDottore().getId());
            notificaService.save(notifica);
            notifica = inserisciNotifica(p.getPaziente().getId(), testo, oggetto, "dottore", p.getDottore().getNome() + " " + p.getDottore().getCognome(), p.getDottore().getId());
            notificaService.save(notifica);
            notifica = inserisciNotifica(p.getPaziente().getId(), testo, oggetto, "paziente", p.getDottore().getNome() + " " + p.getDottore().getCognome(), p.getDottore().getId());
            notifica.setTesto("La prenotazione riguardante " + p.getDescrizione() + " e stabilita per la data " + Data.convertiData(p.getData_visita().toString()) + " è stata annullata.");
            notificaService.save(notifica);
            SendEmail.getInstance().sendMail(oggetto,testo, p.getPaziente().getEmail());
        }
        prenotazioneService.deletePrenotazione(id);
        return HttpStatus.OK;
    }

    @PutMapping(path =  "/prenotazione")
    public ResponseEntity<PrenotazioneDTO> update(@RequestBody PrenotazioneDTO prenotazione){
        Prenotazione prenotazioneVecchia = prenotazioneService.getById(prenotazione.getId());
        if(prenotazione.isConfermato() == prenotazioneVecchia.isConfermato() && prenotazione.getData_visita()!=null){
            if(!prenotazione.getData_visita().equals(prenotazioneVecchia.getData_visita())) {
                String testo="La data della sua prenotazione riguardante "+ prenotazione.getDescrizione() +", ha subito una variazione da "
                        + Data.convertiData(prenotazioneVecchia.getData_visita().toString())+" a "+
                        Data.convertiData(prenotazione.getData_visita().toString())+".";
                String oggetto="Variazione data prenotazione";
                SendEmail.getInstance().sendMail(oggetto,testo, prenotazione.getPaziente().getEmail());
                NotificaDTO notifica = inserisciNotifica(prenotazione.getPaziente().getId(),testo,oggetto,"paziente", prenotazione.getDottore().getNome()+" "+prenotazione.getDottore().getCognome(), prenotazione.getDottore().getId());
                notificaService.save(notifica);
            }
        }
        if(prenotazione.isConfermato()==false && prenotazioneVecchia.isConfermato()==true){
            if(prenotazione.isUrgente()==true ){
                String testo="La sua prenotazione riguardante "+ prenotazione.getDescrizione() +" e stabilita in data "+ Data.convertiData(prenotazioneVecchia.getData_visita().toString())
                        +" è stata annullata ma comunque inserita fra quelle urgenti, riceverà un altra email quando verrà riconfermata.";
                String oggetto="Annullamento prenotazione";
                SendEmail.getInstance().sendMail(oggetto,testo, prenotazione.getPaziente().getEmail());
                NotificaDTO notifica = inserisciNotifica(prenotazione.getPaziente().getId(),testo,oggetto,"paziente",prenotazione.getDottore().getNome()+" "+prenotazione.getDottore().getCognome(), prenotazione.getDottore().getId());
                notificaService.save(notifica);
                testo="La prenotazione riguardante "+ prenotazione.getDescrizione() +" e stabilita in data "+ Data.convertiData(prenotazioneVecchia.getData_visita().toString())
                        +" del paziente "+prenotazione.getPaziente().getNome() +" "+prenotazione.getPaziente().getCognome()+" è stata annullata";
                notifica.setRicevitore("dottore");
                notifica.setTesto(testo);
                notificaService.save(notifica);
            }
            else if(prenotazione.isUrgente()==false ){
                String testo="La sua prenotazione riguardante "+ prenotazione.getDescrizione() +" e stabilita in data "+ Data.convertiData(prenotazioneVecchia.getData_visita().toString())
                        +" è stata annullata, riceverà un altra email quando verrà riconfermata.";
                String oggetto="Annullamento prenotazione";
                SendEmail.getInstance().sendMail(oggetto,testo, prenotazione.getPaziente().getEmail());
                NotificaDTO notifica = inserisciNotifica(prenotazione.getPaziente().getId(),testo,oggetto,"paziente",prenotazione.getDottore().getNome()+" "+prenotazione.getDottore().getCognome(), prenotazione.getDottore().getId());
                notificaService.save(notifica);
                testo="La prenotazione riguardante "+ prenotazione.getDescrizione() +" e stabilita in data "+ Data.convertiData(prenotazioneVecchia.getData_visita().toString())
                        +" del paziente "+prenotazione.getPaziente().getNome() +" "+prenotazione.getPaziente().getCognome()+" è stata annullata";
                notifica.setRicevitore("dottore");
                notifica.setTesto(testo);
                notificaService.save(notifica);
            }
        }
        if(prenotazione.isConfermato()==false && prenotazioneVecchia.isConfermato()==false){
            if(prenotazione.isUrgente()==true && prenotazioneVecchia.isUrgente()==false)
            {
                String testo="La sua prenotazione riguardante "+ prenotazione.getDescrizione() +" non ancora programmata in una data è stata spostata tra le prenotazioni urgenti.";
                String oggetto="Prenotazione spostata in urgenti";
                SendEmail.getInstance().sendMail(oggetto,testo,prenotazione.getPaziente().getEmail());
                NotificaDTO notifica = inserisciNotifica(prenotazione.getPaziente().getId(),testo,oggetto,"paziente",prenotazione.getDottore().getNome()+" "+prenotazione.getDottore().getCognome(), prenotazione.getDottore().getId());
                notificaService.save(notifica);
            }
            else if(prenotazione.isUrgente()==false && prenotazioneVecchia.isUrgente()==true){
                String testo="La sua prenotazione riguardante "+ prenotazione.getDescrizione() +" non ancora programmata in una data è stata rimossa dalle prenotazioni urgenti.";
                String oggetto="Prenotazione rimossa dalle urgenti";
                SendEmail.getInstance().sendMail(oggetto,testo,prenotazione.getPaziente().getEmail());
                NotificaDTO notifica = inserisciNotifica(prenotazione.getPaziente().getId(),testo,oggetto,"paziente",prenotazione.getDottore().getNome()+" "+prenotazione.getDottore().getCognome(), prenotazione.getDottore().getId());
                notificaService.save(notifica);
            }

        }
        if(prenotazione.isConfermato()==true && prenotazioneVecchia.isConfermato()==false){
            String testo="La sua prenotazione riguardante "+ prenotazione.getDescrizione() +", è stata confermata per la data "+Data.convertiData(prenotazione.getData_visita().toString());
            String oggetto="Prenotazione Confermata";
            SendEmail.getInstance().sendMail(oggetto,testo, prenotazione.getPaziente().getEmail());
            NotificaDTO notifica = inserisciNotifica(prenotazione.getPaziente().getId(),testo,oggetto,"paziente",prenotazione.getDottore().getNome()+" "+prenotazione.getDottore().getCognome(), prenotazione.getDottore().getId());
            notificaService.save(notifica);
        }
        PrenotazioneDTO prenotazioneNuova = prenotazioneService.updatePrenotazione(prenotazione);
        return ResponseEntity.ok(prenotazioneNuova);
    }

    @PostMapping(path =  "/controlloStessoOrarioDottoreDiverso")
    public ResponseEntity<PrenotazioneDTO> controlloStessoOrarioDottoreDiverso(@RequestBody PrenotazioneDTO prenotazione){
        boolean risultato = prenotazioneService.controlloStessoOrarioDottoreDiverso(prenotazione);
        if(risultato)
            return ResponseEntity.badRequest().body(prenotazione);
        else
            return ResponseEntity.ok().body(prenotazione);
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
