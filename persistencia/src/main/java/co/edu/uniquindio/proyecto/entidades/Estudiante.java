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

public class Estudiante extends Persona implements Serializable {

    @OneToMany(mappedBy = "estudiante")
    @ToString.Exclude
    private List<Prueba_Estudiante> prueba_estudianteList;

}
