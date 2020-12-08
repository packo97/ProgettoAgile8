package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.DatiLogin;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PazienteService;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.SegretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SegretariaController {

    // CHANGE TO USE DTO
    @Autowired
    private SegretariaService segretariaService;

    // usare dto nel response e anche nel requestbody
    @PostMapping(path = "/segretaria")
    public ResponseEntity<SegretariaDTO> add(@RequestBody SegretariaDTO paziente) throws NoSuchAlgorithmException {
        SegretariaDTO p = segretariaService.addSegretaria(paziente);
        return ResponseEntity.ok(p);
    }

    @PostMapping(path = "/loginSegretaria")
    public ResponseEntity<Boolean> login(@RequestBody DatiLogin datiLogin) {
        Boolean p = segretariaService.login(datiLogin.getEmail(), datiLogin.getPassword());
        return ResponseEntity.ok(p);
    }
}
