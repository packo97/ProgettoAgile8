package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.dto.DottoreDTO;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Paziente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DottoreDAO extends JpaRepository<Dottore, Long>, JpaSpecificationExecutor<Dottore> {

    List<Dottore> findAll();

    Dottore findAllByEmailAndPassword(String email, String password);

    Dottore findAllByEmail(String email);

    @Modifying
    @Query("update Dottore d set d.nome = ?1, d.cognome = ?2, d.codice_fiscale= ?3, d.numero_telefono= ?4 where d.id = ?5")
    void updateDottore(String nome, String cognome, String codice_fiscale, String numero_telefono, Long id);

    @Modifying
    @Query("update Dottore d set d.password = ?1 where d.id = ?2")
    void updatePassword1(String passwordNuova, Long id);

    @Modifying
    @Query("update Dottore d set d.img = ?1 where d.id = ?2")
    void updateImg(byte[] img, Long id);

    @Modifying
    @Query("update Dottore p set p.password = ?1, p.salt = ?2 where p.email = ?3")
    void updatePassword2(String password, String salt, String email);

}
