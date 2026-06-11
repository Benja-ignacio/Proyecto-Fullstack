package cl.duoc.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.logistica.model.Logistic;

@Repository
public interface LogisticRepository extends JpaRepository<Logistic, Long> {

}
