package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpcionRepo extends JpaRepository<Opcion,Integer> {


}
