package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;


import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Sicurezza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class PazienteServiceImpl implements PazienteService{

    @Autowired
    private PazienteDAO pazienteDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PazienteDTO addPaziente(PazienteDTO dto) throws NoSuchAlgorithmException {
        System.out.println("Aggiunto Paziente");
        String password= dto.getPassword();
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String passwordSicura = Sicurezza.getSecurePassword(password, saltString.getBytes(StandardCharsets.UTF_8));
        System.out.println(passwordSicura);
        dto.setPassword(passwordSicura);
        dto.setSalt(saltString);

        Paziente paziente = modelMapper.map(dto, Paziente.class);
        Paziente saved = pazienteDAO.save(paziente);
        return modelMapper.map(saved, PazienteDTO.class);

    }

    @Override
    public Boolean login(String email, String password){
        String salt = pazienteDAO.findAllByEmail(email).getSalt();
        Paziente p = pazienteDAO.findAllByEmailAndPassword(email,Sicurezza.getSecurePassword(password,salt.getBytes(StandardCharsets.UTF_8)));
        if(p==null)
            return false;
        else return true;
    }

    @Override
    public PazienteDTO getPazienteByEmail(String email) {
        Paziente paziente = pazienteDAO.findAllByEmail(email);
        return modelMapper.map(paziente, PazienteDTO.class);
    }
}
