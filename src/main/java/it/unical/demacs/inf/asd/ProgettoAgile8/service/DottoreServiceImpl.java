package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DottoreServiceImpl  implements DottoreService{


    @Autowired
    private DottoreDAO dottoreDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DottoreDTO addDottore(DottoreDTO dto) {
        System.out.println("Aggiunto Dottore");
        Dottore dottore = modelMapper.map(dto, Dottore.class);
        Dottore saved = dottoreDAO.save(dottore);
        return modelMapper.map(saved, DottoreDTO.class);
    }
}
