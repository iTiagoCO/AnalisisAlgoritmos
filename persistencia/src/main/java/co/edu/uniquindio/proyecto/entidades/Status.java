package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer cod;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(length = 100, nullable = false)
    private String css;

    @OneToMany(mappedBy = "status")
    @ToString.Exclude
    private List<Estudiante> estudianteList;
}
