package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface DottoreService {

    DottoreDTO addDottore(DottoreDTO dottore) throws NoSuchAlgorithmException;
    Boolean login(String email, String password);
    List<DottoreDTO> getAll();
    DottoreDTO getDottoreByEmail(String email);

}
