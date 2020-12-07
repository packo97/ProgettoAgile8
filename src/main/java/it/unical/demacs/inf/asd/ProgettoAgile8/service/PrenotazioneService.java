package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;

public interface PrenotazioneService {

    PrenotazioneDTO addPrenotazione(PrenotazioneDTO prenotazione);
}
