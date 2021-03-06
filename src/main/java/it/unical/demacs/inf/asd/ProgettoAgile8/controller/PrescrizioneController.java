package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import it.unical.demacs.inf.asd.ProgettoAgile8.core.ListaItemPrescrizione;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrescrizioneService;
import it.unical.demacs.inf.asd.ProgettoAgile8.utility.PdfCreator;

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
public class PrescrizioneController {

    @Autowired
    private PrescrizioneService prescrizioneService;

    @PostMapping(path = "/prescrizione")
    public ResponseEntity<PrescrizioneDTO> add(@RequestParam("file") MultipartFile file, @RequestParam("dottore") String dottore, @RequestParam("animale") String animale){
        try {
            JSONObject jsonDottore = new JSONObject(dottore);
            JSONObject jsonAnimale = new JSONObject(animale);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            DottoreDTO dottoreDTO = objectMapper.readValue(jsonDottore.toString(), DottoreDTO.class);
            AnimaleDTO animaleDTO = objectMapper.readValue(jsonAnimale.toString(), AnimaleDTO.class);
            byte[] bytes =  file.getBytes();
            PrescrizioneDTO p = prescrizioneService.uploadFile(bytes, dottoreDTO, animaleDTO);
            return ResponseEntity.ok(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/prescrizioniByAnimale")
    public ResponseEntity<List<PrescrizioneDTO>> getByAnimale(@RequestBody AnimaleDTO dto){
        List<PrescrizioneDTO> prescrizioni = prescrizioneService.findAllByAnimale(dto);
        return ResponseEntity.ok(prescrizioni);
    }

    @GetMapping(value = "/prescrizionePDF/{id}")
    public ResponseEntity<Resource> getPdfById(@PathVariable("id") Long id) {
        PrescrizioneDTO prescrizione = prescrizioneService.findAllById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "test" + "\"")
                .body(new ByteArrayResource(prescrizione.getContent()));
    }

    @PostMapping(value = "/creaPrescrizione")
    public ResponseEntity<Resource> creaPrescrizione(@RequestBody ListaItemPrescrizione listaItemPrescrizione) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "test" + "\"")
                .body(new ByteArrayResource(PdfCreator.creaPrescrizionePDF(listaItemPrescrizione)));
    }

}
