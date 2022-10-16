package co.com.poli.taller_3_santiago_cano.persistence.repository;

import co.com.poli.taller_3_santiago_cano.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Filas WHERE usuario_id=:idUsuario", nativeQuery = true)
    void deleteFilaByUsuario(@Param("idUsuario") Integer id);
}
