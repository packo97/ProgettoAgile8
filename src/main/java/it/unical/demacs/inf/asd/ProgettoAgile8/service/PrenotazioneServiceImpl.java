package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PrenotazioneDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PrenotazioneServiceImpl implements PrenotazioneService{

    @Autowired
    private PrenotazioneDAO prenotazioneDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrenotazioneDTO addPrenotazione(PrenotazioneDTO dto) {
        System.out.println("Aggiunta Prenotazione");
        Prenotazione prenotazione = modelMapper.map(dto, Prenotazione.class);
        Prenotazione saved = prenotazioneDAO.save(prenotazione);
        return modelMapper.map(saved, PrenotazioneDTO.class);

    }
}
