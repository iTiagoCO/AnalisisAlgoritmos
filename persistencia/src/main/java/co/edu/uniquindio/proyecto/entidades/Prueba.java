package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
/**
 * Declaracion entidad Usuario con extends de persona y el constructor de la
 * superclase.
 */
public class Prueba implements Serializable {

    @Id
    @PositiveOrZero
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Profesor profesor;
    
    @Column(nullable = true)
    private int estatus;

    @ManyToMany(mappedBy = "pruebas")
    @ToString.Exclude
    private List<Pregunta> preguntaList;

    @OneToMany(mappedBy = "prueba")
    @ToString.Exclude
    private List<Prueba_Estudiante> prueba_estudianteList;

    @ManyToOne
    @ToString.Exclude
    private Calificacion calificacion;

   

 
}
