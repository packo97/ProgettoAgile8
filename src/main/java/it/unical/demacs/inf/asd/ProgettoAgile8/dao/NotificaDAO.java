package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Notifica;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaDAO extends JpaRepository<Notifica, Long> {
    List<Notifica> findAllByPazienteAndSegretariaIsFalse(Long Paziente);
    List<Notifica> findAllBySegretariaIsFalse();
    Notifica getAllById(Long id);
    @Modifying
    @Query("update Notifica n set n.vista = ?1 where n.paziente = ?2")
    void updateNotificheViste( Boolean value ,Long paziente);
}