package it.unical.demacs.inf.asd.ProgettoAgile8.dao;


import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prescrizione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescrizioneDAO extends JpaRepository<Prescrizione, Long>, JpaSpecificationExecutor<Prescrizione> {

    List<Prescrizione> findAllByAnimale(Animale animale);

    Prescrizione findAllById(Long id);

}
