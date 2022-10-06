package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepo extends JpaRepository<Profesor,Integer> {
    Optional<Profesor> findByEmail(String email);

    Optional<Object> findByEmailAndPassword(String email, String password);
}
