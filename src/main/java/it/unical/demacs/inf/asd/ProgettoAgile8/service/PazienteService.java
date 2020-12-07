package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

public interface PazienteService {
    // usare dto

    PazienteDTO addPaziente(PazienteDTO paziente);
    Boolean login(String email, String password);
}
