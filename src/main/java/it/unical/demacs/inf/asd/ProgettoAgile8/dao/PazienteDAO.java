package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;



@Repository
public interface PazienteDAO extends JpaRepository<Paziente, Long>, JpaSpecificationExecutor<Paziente> {

    Paziente findAllByEmailAndPassword(String email, String password);

    Paziente findAllByEmail(String email);
    Paziente findAllById(Long id);

    List<Paziente> findAllBy();

    List<Paziente> findAllByNomeContains(String valoreRicerca);

    @Modifying
    @Query("update Paziente p set p.password = ?1, p.salt = ?2 where p.email = ?3")
    void updatePassword(String password, String salt, String email);

}
