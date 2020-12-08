package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.security.NoSuchAlgorithmException;

public interface PazienteService {
    // usare dto

    PazienteDTO addPaziente(PazienteDTO paziente) throws NoSuchAlgorithmException;
    Boolean login(String email, String password);
}
