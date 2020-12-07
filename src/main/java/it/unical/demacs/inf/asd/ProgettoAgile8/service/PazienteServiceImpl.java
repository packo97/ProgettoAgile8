package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class PazienteServiceImpl implements PazienteService{

    @Autowired
    private PazienteDAO pazienteDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PazienteDTO addPaziente(PazienteDTO dto) {
        System.out.println("Aggiunto Paziente");
        Paziente paziente = modelMapper.map(dto, Paziente.class);
        Paziente saved = pazienteDAO.save(paziente);
        return modelMapper.map(saved, PazienteDTO.class);

    }

    @Override
    public Boolean login(String email, String password) {
        Paziente p = pazienteDAO.findAllByEmailAndPassword(email,password);
        if(p==null)
            return false;
        else return true;
    }
}
