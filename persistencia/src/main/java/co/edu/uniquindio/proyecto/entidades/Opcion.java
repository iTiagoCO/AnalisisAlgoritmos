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
    private Integer valor;

    @Column(length = 100,nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Pregunta pregunta;


    public Opcion(Integer cod, Integer valor, Boolean esCorrecta, Pregunta pregunta) {
        this.cod = cod;
        this.valor = valor;
        this.esCorrecta = esCorrecta;
        this.pregunta = pregunta;
    }

    public Opcion() {

    }
}
