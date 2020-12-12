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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService{

    @Autowired
    private PrenotazioneDAO prenotazioneDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrenotazioneDTO addPrenotazione(PrenotazioneDTO dto) {
        System.out.println("Aggiunta Prenotazione");
        Prenotazione prenotazione = modelMapper.map(dto, Prenotazione.class);
        System.out.println(prenotazione);
        Prenotazione saved = prenotazioneDAO.save(prenotazione);
        return modelMapper.map(saved, PrenotazioneDTO.class);

    }

    @Override
    public void deletePrenotazione(/*PrenotazioneDTO dto,*/ Long id) {
        //Prenotazione p = modelMapper.map(dto, Prenotazione.class);
        prenotazioneDAO.deleteById(id);
        //prenotazioneDAO.delete(p);
    }


    @Override
    public List<PrenotazioneDTO> getUrgentiNonConfermate(){
        return prenotazioneDAO.getUrgentiNonConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PrenotazioneDTO> getAccettate() {
        return prenotazioneDAO.getConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PrenotazioneDTO> getInAttesa(){
        return prenotazioneDAO.getNonConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }




}
