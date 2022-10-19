package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfesorRepo extends JpaRepository<Profesor,Integer> {
    Optional<Profesor> findByEmail(String email);

    Optional<Object> findByEmailAndPassword(String email, String password);
}
