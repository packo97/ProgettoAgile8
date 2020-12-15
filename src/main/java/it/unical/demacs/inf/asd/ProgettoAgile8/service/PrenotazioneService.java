package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;

import java.util.List;

public interface PrenotazioneService {

    PrenotazioneDTO addPrenotazione(PrenotazioneDTO prenotazione);

    List<PrenotazioneDTO> getUrgentiNonConfermate();
    List<PrenotazioneDTO> getAccettate();
    List<PrenotazioneDTO> getInAttesa();
    List<PrenotazioneDTO> getPrenotazioniByPaziente(PazienteDTO dto);
    List<PrenotazioneDTO> getPrenotazioniByDoctor(DottoreDTO dto, Boolean confermato);

    void deletePrenotazione(/*PrenotazioneDTO prenotazione*/ Long id);
}
