package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.AnimaleDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrescrizioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.RicevutaDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.service.RicevutaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RicevutaController {

    @Autowired
    private RicevutaService ricevutaService;

    @PostMapping(path = "/ricevuta")
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
            ricevutaService.uploadFile(bytes, dottoreDTO, animaleDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Aggiunta PDF");
        return HttpStatus.OK;
    }

    @PostMapping(value = "/ricevuteByAnimale")
    public ResponseEntity<List<RicevutaDTO>> getByAnimale(@RequestBody AnimaleDTO dto) {
        System.out.println("by animale");
        List<RicevutaDTO> ricevute = ricevutaService.findAllByAnimale(dto);

        return ResponseEntity.ok(ricevute);
    }

    @GetMapping(value = "/ricevutaPDF/{id}")
    public ResponseEntity<Resource> getPdfById(@PathVariable("id") Long id) {
        System.out.println("get pdf");
        // Load file from database
        RicevutaDTO ricevuta = ricevutaService.findAllById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "test" + "\"")
                .body(new ByteArrayResource(ricevuta.getContent()));
    }

}
