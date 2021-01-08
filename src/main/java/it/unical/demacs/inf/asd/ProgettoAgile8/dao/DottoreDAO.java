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
    @Query("update Dottore p set p.password = ?1, p.salt = ?2 where p.email = ?3")
    void updatePassword(String password, String salt, String email);

}
