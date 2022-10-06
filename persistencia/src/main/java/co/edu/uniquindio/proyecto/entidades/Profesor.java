package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profesor extends Persona implements Serializable {

    @OneToMany(mappedBy = "profesor")
    @ToString.Exclude
    private List<Prueba> prueba;
}
