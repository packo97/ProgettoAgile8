package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PrescrizioneService {

    boolean uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale);
    List<PrescrizioneDTO> findAllByAnimale(AnimaleDTO dto);
    PrescrizioneDTO findAllById(Long id);

}
