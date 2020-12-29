package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.core.util.Json;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrescrizioneService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrescrizioneController {

    @Autowired
    private PrescrizioneService prescrizioneService;

    @PostMapping(path = "/prescrizione")
    public HttpStatus add(@RequestParam("file") MultipartFile file, @RequestParam("dottore") String dottore, @RequestParam("animale") String animale){
        try {
            System.out.println(dottore);
            System.out.println(animale);
            JSONObject jsonDottore = new JSONObject(dottore);
            JSONObject jsonAnimale = new JSONObject(animale);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            DottoreDTO dottoreDTO = objectMapper.readValue(jsonDottore.toString(), DottoreDTO.class);
            AnimaleDTO animaleDTO = objectMapper.readValue(jsonAnimale.toString(), AnimaleDTO.class);
            byte[] bytes =  file.getBytes();
            prescrizioneService.uploadFile(bytes, dottoreDTO, animaleDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Aggiunta PDF");
        return HttpStatus.OK;
    }

    @PostMapping(value = "/prescrizioniByAnimale")
    public ResponseEntity<List<PrescrizioneDTO>> getByAnimale(@RequestBody AnimaleDTO dto){
        System.out.println("by animale");
        List<PrescrizioneDTO> prescrizioni = prescrizioneService.findAllByAnimale(dto);

        return ResponseEntity.ok(prescrizioni);
    }



    @GetMapping(value = "/prescrizionePDF/{id}")
    public ResponseEntity<Resource> getPdfById(@PathVariable("id") Long id) {
        System.out.println("get pdf");
        // Load file from database
        PrescrizioneDTO prescrizione = prescrizioneService.findAllById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "test" + "\"")
                .body(new ByteArrayResource(prescrizione.getContent()));
    }

}
