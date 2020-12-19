package it.unical.demacs.inf.asd.ProgettoAgile8.controller;

import it.unical.demacs.inf.asd.ProgettoAgile8.service.PrescrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restex")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrescrizioneController {

    @Autowired
    private PrescrizioneService prescrizioneService;

    @PostMapping(path = "/prescrizione")
    public HttpStatus add(@RequestBody Byte[] pdf){
        System.out.println("Aggiunta PDF");
        return HttpStatus.OK;
    }
}
