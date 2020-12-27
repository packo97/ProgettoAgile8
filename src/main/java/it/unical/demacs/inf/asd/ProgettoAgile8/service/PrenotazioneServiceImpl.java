package it.unical.demacs.inf.asd.ProgettoAgile8.service;

import it.unical.demacs.inf.asd.ProgettoAgile8.dao.DottoreDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PazienteDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dao.PrenotazioneDAO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PazienteDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.dto.PrenotazioneDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService{

    @Autowired
    private PrenotazioneDAO prenotazioneDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PrenotazioneDTO addPrenotazione(PrenotazioneDTO dto) {
        System.out.println("Aggiunta Prenotazione");
        Prenotazione prenotazione = modelMapper.map(dto, Prenotazione.class);
        System.out.println(prenotazione);
        Prenotazione saved = prenotazioneDAO.save(prenotazione);
        return modelMapper.map(saved, PrenotazioneDTO.class);

    }

    @Override
    public void deletePrenotazione(/*PrenotazioneDTO dto,*/ Long id) {
        //Prenotazione p = modelMapper.map(dto, Prenotazione.class);
        prenotazioneDAO.deleteById(id);
        //prenotazioneDAO.delete(p);
    }

    @Override
    public PrenotazioneDTO updatePrenotazione(PrenotazioneDTO prenotazioneToSave) {
        return prenotazioneDAO.findById(prenotazioneToSave.getId()).map(
                prenotazione -> {
                                prenotazione.setConfermato(prenotazioneToSave.isConfermato());
                                prenotazione.setData_visita(prenotazioneToSave.getData_visita());
                                return modelMapper.map(prenotazioneDAO.save(prenotazione), PrenotazioneDTO.class);
                }
        ).orElseThrow(() -> new RuntimeException());

    }

    @Override
    public List<PrenotazioneDTO> getUrgentiNonConfermate(){
        return prenotazioneDAO.getUrgentiNonConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }


    @Override
    public List<PrenotazioneDTO> getUrgentiNonConfermateByDoctor(DottoreDTO dto){
        Dottore dottore= modelMapper.map(dto, Dottore.class);
        List<PrenotazioneDTO> unsorted = prenotazioneDAO.findAllByConfermatoAndUrgenteAndDottore(false,true,dottore).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());

        unsorted.sort(new Comparator<PrenotazioneDTO>() {
            @Override
            public int compare(PrenotazioneDTO t1, PrenotazioneDTO t2) {
                if(t1.getData_visita() != null && t2.getData_visita() != null){
                    if (t1.getData_visita().isAfter(t2.getData_visita()))
                        return 1;
                    if (t1.getData_visita().isBefore(t2.getData_visita()))
                        return -1;
                }

                return 0;
            }
        });
        return unsorted;

        //return prenotazioneDAO.getUrgentiNonConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PrenotazioneDTO> getAccettate() {
        return prenotazioneDAO.getConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PrenotazioneDTO> getInAttesa(){
        return prenotazioneDAO.getNonConfermate(LocalDateTime.now()).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Prenotazione getById(Long id) {
        return prenotazioneDAO.findAllById(id);
    }

    //è corretto utilizzare due volte il model mapper
    @Override
    public List<PrenotazioneDTO> getPrenotazioniByPaziente(PazienteDTO dto) {
        Paziente paziente= modelMapper.map(dto, Paziente.class);
        return prenotazioneDAO.findAllByPaziente(paziente).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<PrenotazioneDTO> getPrenotazioniByDoctorAndDate(DottoreDTO dto, LocalDateTime date, Boolean confermato) {
        Dottore dottore = modelMapper.map(dto, Dottore.class);
        List<PrenotazioneDTO> unsorted = prenotazioneDAO.findAllByDottoreAndConfermato(dottore, confermato).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());


        List<PrenotazioneDTO> filtered = unsorted.stream().filter(p -> p.getData_visita().getDayOfYear() == date.getDayOfYear()).collect(Collectors.toList());

        filtered.sort(new Comparator<PrenotazioneDTO>() {
            @Override
            public int compare(PrenotazioneDTO t1, PrenotazioneDTO t2) {
                if(t1.getData_visita() != null && t2.getData_visita() != null){
                    if (t1.getData_visita().isAfter(t2.getData_visita()))
                        return 1;
                    if (t1.getData_visita().isBefore(t2.getData_visita()))
                        return -1;
                }

                return 0;
            }
        });
        return filtered;
    }

    @Override
    public List<PrenotazioneDTO> getPrenotazioniByDoctor(DottoreDTO dto, Boolean confermato) {
        Dottore dottore = modelMapper.map(dto, Dottore.class);
        return prenotazioneDAO.findAllByDottoreAndConfermato(dottore, confermato).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PrenotazioneDTO> getRichiesteByDoctor(DottoreDTO dto,Boolean confermato) {
        Dottore dottore = modelMapper.map(dto, Dottore.class);
        List<PrenotazioneDTO> unsorted = prenotazioneDAO.findAllByDottoreAndConfermatoAndUrgente(dottore, confermato, false).stream().map(prenotazione -> modelMapper.map(prenotazione, PrenotazioneDTO.class)).collect(Collectors.toList());
        unsorted.sort(new Comparator<PrenotazioneDTO>() {
            @Override
            public int compare(PrenotazioneDTO t1, PrenotazioneDTO t2) {
                if(t1.getData_visita() != null && t2.getData_visita() != null){
                    if (t1.getData_visita().isAfter(t2.getData_visita()))
                        return 1;
                    if (t1.getData_visita().isBefore(t2.getData_visita()))
                        return -1;
                }

                return 0;
            }
        });
        return unsorted;
    }



}
