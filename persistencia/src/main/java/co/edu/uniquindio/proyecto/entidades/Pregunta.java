package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pregunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer cod;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String titulo;


    @Column(length = 300, nullable = false)
    private String descripcion;


    @Column(nullable = false)
    private Boolean esVisible;


    @PositiveOrZero
    @Column(nullable = false)
    private Integer complejidad;

    @OneToMany(mappedBy = "pregunta")
    @ToString.Exclude
    private List<Opcion> opcionList;


    @ManyToMany
    @ToString.Exclude
    private List<Prueba> pruebas;

    @ManyToOne
    @ToString.Exclude
    private Categorias categoria;


}
