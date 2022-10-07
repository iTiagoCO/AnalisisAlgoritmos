package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer cod;

    @NotBlank
    @Column(length = 50,nullable = false)
    private String titulo;


    @Column(length = 100,nullable = false)
    private String descripcion;


    @OneToMany(mappedBy = "calificacion")
    @ToString.Exclude
    private List<Prueba> pruebaList;
}
