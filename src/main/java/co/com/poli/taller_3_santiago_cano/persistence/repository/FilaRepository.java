package co.com.poli.taller_3_santiago_cano.persistence.repository;

import co.com.poli.taller_3_santiago_cano.persistence.entity.Fila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilaRepository extends JpaRepository<Fila, Integer> {
}
