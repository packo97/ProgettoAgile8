package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.Esame_medicoDTO;

import java.util.List;

public interface Esame_medicoService {

    Esame_medicoDTO uploadFile(byte[] bytes, DottoreDTO dottore, AnimaleDTO animale, String descrizione);

    List<Esame_medicoDTO> findAllByAnimale(AnimaleDTO dto);

    Esame_medicoDTO findAllById(Long id);
}
