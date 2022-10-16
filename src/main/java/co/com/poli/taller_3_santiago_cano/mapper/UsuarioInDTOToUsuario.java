package co.com.poli.taller_3_santiago_cano.mapper;

import co.com.poli.taller_3_santiago_cano.persistence.entity.Usuario;
import co.com.poli.taller_3_santiago_cano.service.DTO.UsuarioInDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInDTOToUsuario implements IMapper<UsuarioInDTO, Usuario> {
    @Override
    public Usuario map(UsuarioInDTO in) {
        Usuario usuario = new Usuario();
        usuario.setDependencia(in.getDependencia());
        usuario.setFechaNacimineto(in.getFechaNacimineto());
        usuario.setPerfil(in.getPerfil());
        usuario.setFilas(in.getFilas());
        usuario.setActivo(Boolean.FALSE);
        return usuario;
    }
}
