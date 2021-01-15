package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Ricevuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RicevutaDAO extends JpaRepository<Ricevuta, Long>, JpaSpecificationExecutor<Ricevuta> {

    List<Ricevuta> findAllByAnimale(Animale animale);

    Ricevuta findAllById(Long id);
}
