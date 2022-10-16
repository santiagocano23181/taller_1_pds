package co.com.poli.taller_3_santiago_cano.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha_nacimineto")
    private Date fechaNacimineto;
    @Column
    private Boolean activo;
    @Column
    private Dependencia dependencia;
    @ElementCollection(targetClass = Perfil.class)
    @JoinTable(name = "perfiles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "pefil", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Perfil> perfil;
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Fila> filas;
}
