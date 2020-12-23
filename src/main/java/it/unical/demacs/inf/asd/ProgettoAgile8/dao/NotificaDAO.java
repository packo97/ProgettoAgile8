package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaDAO extends JpaRepository<Notifica, Long> {
    List<Notifica> findAllByPazienteAndSegretariaIsFalse(Paziente paziente);
    List<Notifica> findAllBySegretariaIsFalse();
}
