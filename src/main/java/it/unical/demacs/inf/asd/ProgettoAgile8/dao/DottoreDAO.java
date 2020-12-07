package it.unical.demacs.inf.asd.ProgettoAgile8.dao;

import it.unical.demacs.inf.asd.ProgettoAgile8.entities.Dottore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DottoreDAO extends JpaRepository<Dottore, Long> {
}
