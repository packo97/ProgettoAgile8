package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;

public interface DottoreService {

    DottoreDTO addDottore(DottoreDTO dottore);
    Boolean login(String email, String password);

}
