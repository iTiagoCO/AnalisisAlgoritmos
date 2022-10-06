package co.edu.uniquindio.proyecto.entidades;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/**
 * Declaracion entidad Usuario con extends de persona y el constructor de la superclase.
 */
public class Prueba implements Serializable {

    @Id
    @PositiveOrZero
    @EqualsAndHashCode.Include
    private Integer cod;


    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Profesor profesor;


    @ManyToMany(mappedBy = "pruebas")
    @ToString.Exclude
    private List <Pregunta> preguntaList;


    @OneToMany(mappedBy = "prueba")
    @ToString.Exclude
    private List<Prueba_Estudiante> prueba_estudianteList;

    public Prueba() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
