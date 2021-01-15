package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.RecuperaPasswordDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;


import it.unical.demacs.inf.asd.ProgettoAgile8.utility.SendEmail;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.Sicurezza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PazienteServiceImpl implements PazienteService{

    @Autowired
    private PazienteDAO pazienteDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PazienteDTO addPaziente(PazienteDTO dto) throws NoSuchAlgorithmException {
        String password= dto.getPassword();
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String passwordSicura = Sicurezza.getSecurePassword(password, saltString.getBytes(StandardCharsets.UTF_8));
        dto.setPassword(passwordSicura);
        dto.setSalt(saltString);
        Paziente paziente = modelMapper.map(dto, Paziente.class);
        Paziente saved = pazienteDAO.save(paziente);
        return modelMapper.map(saved, PazienteDTO.class);
    }

    @Override
    public Boolean login(String email, String password){
        Paziente p1 = pazienteDAO.findAllByEmail(email);
        if(p1==null)
            return false;
        String salt= p1.getSalt();
        Paziente p = pazienteDAO.findAllByEmailAndPassword(email,Sicurezza.getSecurePassword(password,salt.getBytes(StandardCharsets.UTF_8)));
        if(p==null)
            return false;
        else return true;
    }

    @Override
    public PazienteDTO getPazienteByEmail(String email) {
        Paziente paziente = pazienteDAO.findAllByEmail(email);
        if(paziente!=null)
            return modelMapper.map(paziente, PazienteDTO.class);

        return null;
    }

    @Override
    public List<PazienteDTO> getPazienti(){
        List<PazienteDTO> pazienti = pazienteDAO.findAllBy().stream().map(paziente -> modelMapper.map(paziente, PazienteDTO.class)).collect(Collectors.toList());
        return pazienti;
    }

    @Override
    public List<PazienteDTO> ricerca(String valoreRicerca) {
        return pazienteDAO.findAllByNomeContains(valoreRicerca).stream().map(paziente -> modelMapper.map(paziente, PazienteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PazienteDTO findAllById(Long id) {
        return modelMapper.map(pazienteDAO.findAllById(id), PazienteDTO.class);
    }


    @Override
    @Transactional
    public void modificaPassword(RecuperaPasswordDTO recuperaPasswordDTO) throws NoSuchAlgorithmException {
        byte[] salt = Sicurezza.getSalt();
        String saltString = salt.toString();
        String password = Sicurezza.getSecurePassword(recuperaPasswordDTO.getNuovaPassword(),saltString.getBytes(StandardCharsets.UTF_8));
        pazienteDAO.updatePassword(password,saltString,recuperaPasswordDTO.getEmail());
        SendEmail.getInstance().sendMailPasswordCambiata(recuperaPasswordDTO.getEmail());
    }

    @Override
    @Transactional
    public PazienteDTO updatePaziente(PazienteDTO dto) {
        Paziente p = modelMapper.map(dto, Paziente.class);
        pazienteDAO.updatePaziente(p.getNome(), p.getCognome(), p.getCodice_fiscale(), p.getNumero_telefono(), p.getId());
        return dto;
    }

    @Override
    public boolean controllaPassword(String passwordVecchia, PazienteDTO dto) {
        Paziente p = pazienteDAO.findAllByEmail(dto.getEmail());
        String salt = p.getSalt();
        String hashPasswordDB = p.getPassword();
        String hashPasswordInserita = Sicurezza.getSecurePassword(passwordVecchia, salt.getBytes(StandardCharsets.UTF_8));
        return hashPasswordDB.equals(hashPasswordInserita);
    }

    @Override
    @Transactional
    public void updatePassword(String passwordNuova, PazienteDTO dto) {
        Paziente p = pazienteDAO.findAllByEmail(dto.getEmail());
        String salt = p.getSalt();
        String hashPasswordInserita = Sicurezza.getSecurePassword(passwordNuova, salt.getBytes(StandardCharsets.UTF_8));
        pazienteDAO.updatePassword(hashPasswordInserita, dto.getId());
    }

    @Override
    @Transactional
    public void updateImg(byte[] img, Long pazienteID) {
        pazienteDAO.updateImg(img,pazienteID);
    }
}
