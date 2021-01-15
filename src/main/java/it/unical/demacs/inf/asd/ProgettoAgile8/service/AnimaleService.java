package it.unical.demacs.inf.asd.ProgettoAgile8.service;


import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.util.List;

public interface AnimaleService {

    List<AnimaleDTO> findAllByPaziente(PazienteDTO paziente);

    AnimaleDTO addAnimale(AnimaleDTO animaleDTO);

    AnimaleDTO updateAnimale(AnimaleDTO animaleDTO);

    void deleteAnimale(Long id);
}
