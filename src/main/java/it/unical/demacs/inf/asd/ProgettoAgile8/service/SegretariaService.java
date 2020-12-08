package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;

public interface SegretariaService {
    SegretariaDTO addSegretaria(SegretariaDTO paziente);

    Boolean login(String email, String password);
}
