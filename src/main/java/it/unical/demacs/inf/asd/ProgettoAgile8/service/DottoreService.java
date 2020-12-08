package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.security.NoSuchAlgorithmException;

public interface DottoreService {

    DottoreDTO addDottore(DottoreDTO dottore) throws NoSuchAlgorithmException;
    Boolean login(String email, String password);

}
