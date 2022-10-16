package co.com.poli.taller_3_santiago_cano.service;

import co.com.poli.taller_3_santiago_cano.persistence.entity.Fila;
import co.com.poli.taller_3_santiago_cano.persistence.entity.Tarea;
import co.com.poli.taller_3_santiago_cano.persistence.repository.FilaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilaService {
    private final FilaRepository filaRepository;

    public List<Fila> findAll(){
        return this.filaRepository.findAll();
    }

    public Fila findById(Integer id){
        Optional<Fila> fila = this.filaRepository.findById(id);
        return fila.orElse(null);
    }

    public Fila createFila(Fila fila){
        return this.filaRepository.save(fila);
    }

    @Transactional
    public Fila updateFila(Fila fila, Integer id){
        Fila fila1 = findById(id);
        if(fila1 != null){
            fila1.setDuracion(fila.getDuracion());
            fila1.setTarea(fila.getTarea());
            this.filaRepository.save(fila1);
        }
        return fila1;
    }

    @Transactional
    public Fila deleteFila(Integer id){
        Fila fila = findById(id);
        if(fila != null){
            this.filaRepository.delete(fila);
        }
        return fila;
    }
}
