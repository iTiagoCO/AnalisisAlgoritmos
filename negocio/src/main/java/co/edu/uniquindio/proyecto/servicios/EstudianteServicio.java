package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EstudianteServicio {

    Estudiante login(String email, String password) throws Exception;

    Estudiante obtenerUsuario(int parseInt);

    Optional<Estudiante> buscarPorEmail(String email);

    Estudiante registrarEstudiante(Estudiante u) throws Exception;

    List<Estudiante> listarEstudiantes();
}
