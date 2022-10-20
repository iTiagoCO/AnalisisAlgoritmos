package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    @PositiveOrZero
    @Column(nullable = false)
    private Integer edad;




    @PositiveOrZero
    @Column()
    private Integer promedio;

    @ManyToOne
    @ToString.Exclude
    private Status status;



    public Estudiante() {
    }


}
