package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.SegretariaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.AnimaleService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;


@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnimaleController {

    @Autowired
    private AnimaleService animaleService;


    @PostMapping(path = "/animale")
    public ResponseEntity<AnimaleDTO> add(@RequestBody AnimaleDTO animaleDTO){
        AnimaleDTO p = animaleService.addAnimale(animaleDTO);
        return ResponseEntity.ok(p);
    }

    @PostMapping(path = "/animaleByPaziente")
    public ResponseEntity<List<AnimaleDTO>> animaleByPaziente(@RequestBody PazienteDTO paziente){
        List<AnimaleDTO> lista = animaleService.findAllByPaziente(paziente);
        return ResponseEntity.ok(lista);
    }

}
