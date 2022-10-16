package co.com.poli.taller_3_santiago_cano.controller;

import co.com.poli.taller_3_santiago_cano.exceptions.UsuarioException;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Tarea;
import co.com.poli.taller_3_santiago_cano.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/tarea")
@RequiredArgsConstructor
@RestController
public class TareaController {
    private final TareaService tareaService;

    @GetMapping
    public List<Tarea> findAll() {
        return this.tareaService.findAll();
    }

    @GetMapping("/{id}")
    public Tarea findById(@PathVariable("id") Integer id) {
        return this.tareaService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> createTarea(@RequestBody Tarea tarea) {
        Tarea tarea1 = this.tareaService.createTarea(tarea);
        return ResponseEntity.ok(tarea1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTarea(@PathVariable("id") Integer id, @RequestBody Tarea tarea) {
        Tarea tarea1 = this.tareaService.updateTarea(tarea, id);

        if (Objects.isNull(tarea1)) {
            throw new UsuarioException("No se encontró la tarea", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable("id") Integer id) {
        Tarea tarea = this.tareaService.deleteTarea(id);
        if (Objects.isNull(tarea)) {
            throw new UsuarioException("No se encontró la tarea", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
    }
}
