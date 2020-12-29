package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dao.AnimaleDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AnimaleServiceImpl implements AnimaleService{


    @Autowired
    private AnimaleDAO animaleDAO;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<AnimaleDTO> findAllByPaziente(PazienteDTO pazienteDTO) {
        Paziente paziente = modelMapper.map(pazienteDTO, Paziente.class);
        return animaleDAO.findAllByPaziente(paziente).stream().map(animale -> modelMapper.map(animale,AnimaleDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AnimaleDTO addAnimale(AnimaleDTO animaleDTO) {
        Paziente paziente = modelMapper.map(animaleDTO.getPaziente(), Paziente.class);
        if(animaleDAO.findAllByPaziente(paziente)==null) {
            Animale animale = modelMapper.map(animaleDTO, Animale.class);
            Animale saved = animaleDAO.save(animale);
            return modelMapper.map(saved, AnimaleDTO.class);
        }else{
            animaleDAO.updateAnimale(animaleDTO.getNome(),animaleDTO.getData_nascita(),animaleDTO.getTipo(),animaleDTO.getGenere(),animaleDTO.getPeso(),animaleDTO.getAltezza(),paziente,animaleDTO.getId());
            return animaleDTO;
        }

    }
}
