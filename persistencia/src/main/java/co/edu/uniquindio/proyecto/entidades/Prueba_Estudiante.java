package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prueba_Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int cod;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private Boolean sePresento;

    @ManyToOne
    @ToString.Exclude
    private Estudiante estudiante;

    @ManyToOne
    @ToString.Exclude
    private Prueba prueba;


    @OneToMany(mappedBy = "prueba_estudiante")
    @ToString.Exclude
    private List<Respuesta> respuestaList;


}
