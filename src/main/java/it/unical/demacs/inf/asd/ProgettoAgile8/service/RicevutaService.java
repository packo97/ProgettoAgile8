package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.RicevutaDTO;

import java.util.List;

public interface RicevutaService {

    boolean uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale);
    List<RicevutaDTO> findAllByAnimale(AnimaleDTO dto);
    RicevutaDTO findAllById(Long id);
}
