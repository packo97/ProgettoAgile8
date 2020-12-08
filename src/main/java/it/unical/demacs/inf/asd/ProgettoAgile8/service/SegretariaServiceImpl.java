package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.SegretariaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Segretaria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SegretariaServiceImpl implements SegretariaService{

    @Autowired
    private SegretariaDAO segretariaDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SegretariaDTO addSegretaria(SegretariaDTO dto) {
        System.out.println("Aggiunta Segretaria");
        Segretaria segretaria = modelMapper.map(dto, Segretaria.class);
        Segretaria saved = segretariaDAO.save(segretaria);
        return modelMapper.map(saved, SegretariaDTO.class);
    }

    @Override
    public Boolean login(String email, String password) {
        Segretaria d = segretariaDAO.findAllByEmailAndPassword(email,password);
        if(d==null)
            return false;
        else return true;
    }
}
