package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PazienteDAO extends JpaRepository<Paziente, Long>, JpaSpecificationExecutor<Paziente> {

    Paziente findAllByEmailAndPassword(String email, String password);

    Paziente findAllByEmail(String email);
    Paziente findAllById(Long id);

    List<Paziente> findAllBy();

    List<Paziente> findAllByNomeContains(String valoreRicerca);

    @Modifying
    @Query("update Paziente p set p.nome = ?1, p.cognome = ?2, p.codice_fiscale= ?3, p.numero_telefono= ?4 where p.id = ?5")
    void updatePaziente(String nome, String cognome, String codice_fiscale, String numero_telefono, Long id);

    @Modifying
    @Query("update Paziente p set p.password = ?1 where p.id = ?2")
    void updatePassword(String passwordNuova, Long id);

    @Modifying
    @Query("update Paziente p set p.img = ?1 where p.id = ?2")
    void updateImg(byte[] img, Long id);

    @Modifying
    @Query("update Paziente p set p.password = ?1, p.salt = ?2 where p.email = ?3")
    void updatePassword(String password, String salt, String email);

}
