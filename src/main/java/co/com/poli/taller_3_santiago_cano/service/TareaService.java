package co.com.poli.taller_3_santiago_cano.service;

import co.com.poli.taller_3_santiago_cano.persistence.entity.Tarea;
import co.com.poli.taller_3_santiago_cano.persistence.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TareaService {
    private final TareaRepository tareaRepository;

    public List<Tarea> findAll(){
        return this.tareaRepository.findAll();
    }

    public Tarea findById(Integer id){
        Optional<Tarea> tarea = this.tareaRepository.findById(id);
        return tarea.orElse(null);
    }

    public Tarea createTarea(Tarea tarea){
        return this.tareaRepository.save(tarea);
    }

    @Transactional
    public Tarea updateTarea(Tarea tarea, Integer id){
        Tarea tarea1 = findById(id);
        if(tarea1 != null){
            tarea1.setNombre(tarea.getNombre());
            this.tareaRepository.save(tarea1);
        }
        return tarea1;
    }

    @Transactional
    public Tarea deleteTarea(Integer id){
        Tarea tarea = findById(id);
        if(tarea != null){
            this.tareaRepository.delete(tarea);
        }
        return tarea;
    }
}
