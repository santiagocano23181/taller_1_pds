package co.com.poli.taller_3_santiago_cano.service;

import co.com.poli.taller_3_santiago_cano.mapper.UsuarioInDTOToUsuario;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Tarea;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Usuario;
import co.com.poli.taller_3_santiago_cano.persistence.repository.UsuarioRepository;
import co.com.poli.taller_3_santiago_cano.service.DTO.UsuarioInDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioInDTOToUsuario usuarioInDTOToUsuario;

    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    public Usuario findById(Integer id){
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public Usuario createUsuario(UsuarioInDTO usuarioInDTO){
        Usuario usuario = usuarioInDTOToUsuario.map(usuarioInDTO);
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(Usuario usuario, Integer id){
        Usuario usuario1 = findById(id);
        if(usuario1 != null){
            usuario1.setActivo(usuario.getActivo());
            usuario1.setPerfil(usuario.getPerfil());
            usuario1.setDependencia(usuario.getDependencia());
            usuario1.setFechaNacimineto(usuario.getFechaNacimineto());
            usuario1.setFilas(usuario.getFilas());
            this.usuarioRepository.save(usuario1);
        }
        return usuario1;
    }

    @Transactional
    public Usuario deleteUsuario(Integer id){
        Usuario usuario = findById(id);
        if(usuario != null){
            this.usuarioRepository.deleteFilaByUsuario(id);
            this.usuarioRepository.deleteById(id);
        }
        return usuario;
    }
}
