package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntaRepo extends JpaRepository<Pregunta,Integer> {


    @Query("select pregunta from Pregunta pregunta where pregunta.complejidad = :complejidad")
    public List<Pregunta> buscarPruebasComplejidad(Integer complejidad) throws Exception;
}
