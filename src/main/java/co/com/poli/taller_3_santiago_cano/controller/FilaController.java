package co.com.poli.taller_3_santiago_cano.controller;

import co.com.poli.taller_3_santiago_cano.exceptions.FilaException;
import co.com.poli.taller_3_santiago_cano.exceptions.UsuarioException;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Fila;
import co.com.poli.taller_3_santiago_cano.service.FilaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fila")
public class FilaController {
    private final FilaService filaService;

    @GetMapping
    public List<Fila> findAll() {
        return filaService.findAll();
    }

    @GetMapping("/{id}")
    public Fila findById(@PathVariable("id") Integer id) {
        return filaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createFila(@RequestBody Fila fila) {
        int duracion = fila.getDuracion();
        if(duracion < 1 || duracion > 60){
            throw  new FilaException("La duracion debe estar en un rango de 1 y 60", HttpStatus.BAD_REQUEST);
        }
        Fila fila1 = filaService.createFila(fila);
        return ResponseEntity.ok(fila1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFila(@PathVariable("id") Integer id, @RequestBody Fila fila) {
        int duracion = fila.getDuracion();
        if(duracion < 1 || duracion > 60){
            throw  new FilaException("La duracion debe estar en un rango de 1 y 60", HttpStatus.BAD_REQUEST);
        }
        Fila fila1 = this.filaService.updateFila(fila, id);
        if (Objects.isNull(fila1)) {
            throw new FilaException("No se encontró la fila", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFila(@PathVariable("id") Integer id) {
        Fila fila = this.filaService.deleteFila(id);
        if (Objects.isNull(fila)) {
            throw new FilaException("No se encontró la fila", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
