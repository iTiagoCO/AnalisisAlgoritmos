package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Pregunta;

import java.util.List;
import java.util.Optional;

public interface PreguntaServicio {

    Pregunta registrarPregunta(Pregunta u) throws Exception;
    Pregunta actualizarPregunta(Pregunta u) throws Exception;
    Pregunta obtenerPregunta (Integer codigo);
    void eliminarPregunta(Integer codigo) throws Exception;
    List<Pregunta> preguntasList() throws Exception;
    List<Pregunta> findByComplejidad(Integer complejidad) throws Exception;
       
}
