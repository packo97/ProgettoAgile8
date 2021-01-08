package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.Esame_medicoDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.RicevutaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.Esame_medicoDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.RicevutaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Esame_medico;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Ricevuta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Esame_medicoServiceImpl implements Esame_medicoService{

    @Autowired
    private Esame_medicoDAO esame_medicoDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale, String descrizione) {
        Esame_medicoDTO r = new Esame_medicoDTO();
        r.setContent(bytes);
        r.setDottore(dottore);
        r.setAnimale(animale);
        r.setDescrizione(descrizione);
        Esame_medico esame = modelMapper.map(r, Esame_medico.class);
        esame_medicoDAO.save(esame);
        System.out.println("sto salvando"+ dottore.getCodice_fiscale());
        return true;
    }

    @Override
    public List<Esame_medicoDTO> findAllByAnimale(AnimaleDTO dto) {
        Animale animale = modelMapper.map(dto, Animale.class);
        return esame_medicoDAO.findAllByAnimale(animale).stream().map(esame_medico -> modelMapper.map(esame_medico, Esame_medicoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Esame_medicoDTO findAllById(Long id) {
        Esame_medico esame_medico = esame_medicoDAO.findAllById(id);
        Esame_medicoDTO dto = modelMapper.map(esame_medico, Esame_medicoDTO.class);
        return dto;
    }
}
