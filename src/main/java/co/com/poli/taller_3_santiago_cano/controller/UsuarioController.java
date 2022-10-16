package co.com.poli.taller_3_santiago_cano.controller;

import co.com.poli.taller_3_santiago_cano.exceptions.UsuarioException;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Usuario;
import co.com.poli.taller_3_santiago_cano.service.DTO.UsuarioInDTO;
import co.com.poli.taller_3_santiago_cano.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll() {
        return this.usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findByID(@PathVariable("id") Integer id) {
        return this.usuarioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioInDTO usuarioInDTO) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = usuarioInDTO.getFechaNacimineto().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            throw new UsuarioException("El usuario es menor de edad", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = this.usuarioService.createUsuario(usuarioInDTO);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = usuario.getFechaNacimineto().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            throw new UsuarioException("El usuario es menor de edad", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario1 = this.usuarioService.updateUsuario(usuario, id);

        if (Objects.isNull(usuario1)) {
            throw new UsuarioException("No se encontró el usuario", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuario1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Integer id) {
        Usuario usuario = this.usuarioService.deleteUsuario(id);
        if (Objects.isNull(usuario)) {
            throw new UsuarioException("No se encontró el usuario", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
