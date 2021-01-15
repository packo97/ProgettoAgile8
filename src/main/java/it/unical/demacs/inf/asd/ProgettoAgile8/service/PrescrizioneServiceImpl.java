package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PrescrizioneDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prescrizione;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PrescrizioneServiceImpl implements PrescrizioneService{


    @Autowired
    private PrescrizioneDAO prescrizioneDAO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PrescrizioneDTO uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale) {
        PrescrizioneDTO p = new PrescrizioneDTO();
        p.setContent(bytes);
        p.setDottore(dottore);
        p.setAnimale(animale);
        Prescrizione prescrizione = modelMapper.map(p, Prescrizione.class);
        Prescrizione p1 = prescrizioneDAO.save(prescrizione);
        return modelMapper.map(p1,PrescrizioneDTO.class);
    }

    @Override
    public List<PrescrizioneDTO> findAllByAnimale(AnimaleDTO dto) {
        Animale animale = modelMapper.map(dto, Animale.class);
        return prescrizioneDAO.findAllByAnimale(animale).stream().map(prescrizione -> modelMapper.map(prescrizione, PrescrizioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PrescrizioneDTO findAllById(Long id) {
        Prescrizione prescrizione = prescrizioneDAO.findAllById(id);
        PrescrizioneDTO dto = modelMapper.map(prescrizione, PrescrizioneDTO.class);
        return dto;
    }
}
