package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Opcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer cod;

    @PositiveOrZero
    @Column(nullable = false)
    private int valor;

    @Column(nullable = false)
    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Pregunta pregunta;

    @OneToMany(mappedBy = "opcion")
    @ToString.Exclude
    private List<Respuesta> respuestaList;
}
