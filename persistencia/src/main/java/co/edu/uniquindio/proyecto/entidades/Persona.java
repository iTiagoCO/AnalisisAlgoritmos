package co.edu.uniquindio.proyecto.entidades;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
@AllArgsConstructor
/**
 * Declaracion entidad Persona
 */
public class Persona implements Serializable{

    @Id
    @PositiveOrZero
    @EqualsAndHashCode.Include
    private Integer cod;

    @Column(length =150,nullable = false)
    @Length(min = 5, max = 150 , message = "El nombre debe tener minimo 5 caracteres y maximo 150")
    @NotBlank
    private String nombre;

    @Column(length =80,nullable = false, unique = true)
    @Email(message = "Ingrese un email válido")
    @NotBlank
    private String email;

    @Column(length =100,nullable = false)
    @Length(max=100)
    @NotBlank
    private String password;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(cod, persona.cod);
    }

    @Override
    public int hashCode() {
        return 0;
    }


}
