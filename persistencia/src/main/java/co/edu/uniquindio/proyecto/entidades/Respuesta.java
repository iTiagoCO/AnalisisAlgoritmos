package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Respuesta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer cod;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Opcion opcion;


    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Prueba_Estudiante prueba_estudiante;
}
