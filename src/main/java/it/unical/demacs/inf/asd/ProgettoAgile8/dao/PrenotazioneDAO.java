package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrenotazioneDAO extends JpaRepository<Prenotazione, Long> {

    @Query("select p from Prenotazione p where p.confermato=true and p.urgente=true order by p.data_visita")
    List<Prenotazione> getUrgentiConfermate(@Param("data") LocalDateTime data);

    @Query("select p from Prenotazione p where p.confermato=false and p.urgente=true  order by p.data_visita")
    List<Prenotazione> getUrgentiNonConfermate(@Param("data") LocalDateTime data);

    @Query("select p from Prenotazione p where p.confermato=true and p.urgente=false  order by p.data_visita")
    List<Prenotazione> getConfermate(@Param("data") LocalDateTime data);

    @Query("select p from Prenotazione p where p.confermato=false and p.urgente=false order by p.data_visita")
    List<Prenotazione> getNonConfermate(@Param("data") LocalDateTime data);


    //List<Prenotazione> findByConfermatoTrueAndUrgenteFalseAndData_visitaGreaterThan(LocalDate current);

}
