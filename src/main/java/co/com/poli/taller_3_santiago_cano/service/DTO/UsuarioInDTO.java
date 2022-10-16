package co.com.poli.taller_3_santiago_cano.service.DTO;

import co.com.poli.taller_3_santiago_cano.persistence.entity.Dependencia;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Fila;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Perfil;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsuarioInDTO {
    private Integer id;
    private Date fechaNacimineto;
    private Dependencia dependencia;
    private List<Perfil> perfil;
    private List<Fila> filas;
}
