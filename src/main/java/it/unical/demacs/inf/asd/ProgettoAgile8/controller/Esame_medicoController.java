package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.Esame_medicoDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.Esame_medicoService;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Esame_medicoController {

    @Autowired
    private Esame_medicoService esame_medicoService;

    @PostMapping(path = "/esame")
    public ResponseEntity<Esame_medicoDTO> add(@RequestParam("file") MultipartFile file, @RequestParam("dottore") String dottore, @RequestParam("animale") String animale, @RequestParam("descrizione") String descrizione){
        try {
            JSONObject jsonDottore = new JSONObject(dottore);
            JSONObject jsonAnimale = new JSONObject(animale);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            DottoreDTO dottoreDTO = objectMapper.readValue(jsonDottore.toString(), DottoreDTO.class);
            AnimaleDTO animaleDTO = objectMapper.readValue(jsonAnimale.toString(), AnimaleDTO.class);
            byte[] bytes =  file.getBytes();
            return ResponseEntity.ok(esame_medicoService.uploadFile(bytes, dottoreDTO, animaleDTO, descrizione));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/esamiByAnimale")
    public ResponseEntity<List<Esame_medicoDTO>> getByAnimale(@RequestBody AnimaleDTO dto) {
        List<Esame_medicoDTO> esami = esame_medicoService.findAllByAnimale(dto);
        return ResponseEntity.ok(esami);
    }

    @GetMapping(value = "/esamePDF/{id}")
    public ResponseEntity<Resource> getPdfById(@PathVariable("id") Long id) {
        Esame_medicoDTO esame = esame_medicoService.findAllById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "test" + "\"")
                .body(new ByteArrayResource(esame.getContent()));
    }

}
