package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Animale;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Esame_medico;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Ricevuta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Esame_medicoDAO extends JpaRepository<Esame_medico, Long>, JpaSpecificationExecutor<Esame_medico> {

    List<Esame_medico> findAllByAnimale(Animale animale);

    Esame_medico findAllById(Long id);

}
