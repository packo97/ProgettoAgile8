package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;

import java.time.LocalDateTime;
import java.util.List;

public interface PrenotazioneService {

    PrenotazioneDTO addPrenotazione(PrenotazioneDTO prenotazione);

    List<PrenotazioneDTO> getUrgentiNonConfermate();

    List<PrenotazioneDTO> getUrgentiNonConfermateByDoctor(DottoreDTO dto);

    List<PrenotazioneDTO> getAccettate();

    List<PrenotazioneDTO> getInAttesa();

    Prenotazione getById(Long id);

    List<PrenotazioneDTO> getPrenotazioniByPaziente(PazienteDTO dto);

    List<PrenotazioneDTO> getPrenotazioniByDoctorAndDate(DottoreDTO dto, LocalDateTime date, Boolean confermato);

    List<PrenotazioneDTO> getPrenotazioniByDoctor(DottoreDTO dto, Boolean confermato);

    List<PrenotazioneDTO> getRichiesteByDoctor(DottoreDTO dto, Boolean confermato);

    void deletePrenotazione(Long id);

    PrenotazioneDTO updatePrenotazione(PrenotazioneDTO prenotazione);

    boolean controlloStessoOrarioDottoreDiverso(PrenotazioneDTO prenotazioneToSave);
}
