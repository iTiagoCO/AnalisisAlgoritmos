package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepo extends JpaRepository<Pregunta,Integer> {
}
