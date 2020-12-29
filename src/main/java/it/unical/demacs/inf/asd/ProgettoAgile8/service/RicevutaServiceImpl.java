package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dao.RicevutaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.RicevutaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prescrizione;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Ricevuta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RicevutaServiceImpl implements RicevutaService{


    @Autowired
    private RicevutaDAO ricevutaDAO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public boolean uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale) {
        RicevutaDTO r = new RicevutaDTO();
        r.setContent(bytes);
        r.setDottore(dottore);
        r.setAnimale(animale);
        Ricevuta ricevuta = modelMapper.map(r, Ricevuta.class);
        ricevutaDAO.save(ricevuta);
        return true;
    }

    @Override
    public List<RicevutaDTO> findAllByAnimale(AnimaleDTO dto) {
        Animale animale = modelMapper.map(dto, Animale.class);
        return ricevutaDAO.findAllByAnimale(animale).stream().map(ricevuta -> modelMapper.map(ricevuta, RicevutaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public RicevutaDTO findAllById(Long id) {
        Ricevuta ricevuta = ricevutaDAO.findAllById(id);
        RicevutaDTO dto = modelMapper.map(ricevuta, RicevutaDTO.class);
        return dto;
    }
}
