package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.SegretariaDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Segretaria;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Sicurezza;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

@Service
public class SegretariaServiceImpl implements SegretariaService{

    @Autowired
    private SegretariaDAO segretariaDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SegretariaDTO addSegretaria(SegretariaDTO dto) throws NoSuchAlgorithmException {
        System.out.println("Aggiunta Segretaria");
        String password= dto.getPassword();
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String passwordSicura = Sicurezza.getSecurePassword(password, saltString.getBytes(StandardCharsets.UTF_8));
        System.out.println(passwordSicura);
        dto.setPassword(passwordSicura);
        dto.setSalt(saltString);
        Segretaria segretaria = modelMapper.map(dto, Segretaria.class);
        Segretaria saved = segretariaDAO.save(segretaria);
        return modelMapper.map(saved, SegretariaDTO.class);
    }

    @Override
    public Boolean login(String email, String password) {
        String salt = segretariaDAO.findAllByEmail(email).getSalt();
        Segretaria d = segretariaDAO.findAllByEmailAndPassword(email,Sicurezza.getSecurePassword(password,salt.getBytes(StandardCharsets.UTF_8)));
        if(d==null)
            return false;
        else return true;
    }
}
