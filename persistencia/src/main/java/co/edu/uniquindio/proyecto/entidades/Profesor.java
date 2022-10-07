package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@ToString(callSuper = true)
public class Profesor extends Persona implements Serializable {

    @OneToMany(mappedBy = "profesor")
    @ToString.Exclude
    private List<Prueba> prueba;

    public Profesor(int cod, String nombre, String email, String password) {
        super(cod, nombre, email, password);
    }
}
