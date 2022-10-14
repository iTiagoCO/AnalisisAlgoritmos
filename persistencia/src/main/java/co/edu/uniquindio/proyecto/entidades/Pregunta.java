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
public class Pregunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer cod;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String titulo;


    @Column(length = 100, nullable = false)
    private String descripcion;


    @Column(nullable = false)
    private Boolean esVisible;

    @OneToMany(mappedBy = "pregunta")
    @ToString.Exclude
    private List<Opcion> opcionList;


    @ManyToMany
    @ToString.Exclude
    private List<Prueba> pruebas;

    @ManyToOne
    @ToString.Exclude
    private Categorias categoria;


    public Pregunta(Integer cod, String titulo, String descripcion, Boolean esVisible, List<Opcion> opcionList, List<Prueba> pruebas, Categorias categoria) {
        this.cod = cod;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.esVisible = esVisible;
        this.opcionList = opcionList;
        this.pruebas = pruebas;
        this.categoria = categoria;
    }

    public Pregunta() {

    }
}
