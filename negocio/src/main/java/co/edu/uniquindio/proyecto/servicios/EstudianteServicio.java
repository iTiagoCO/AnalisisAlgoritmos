package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EstudianteServicio {
    Estudiante login(String email, String password) throws Exception;

    Estudiante obtenerUsuario(int parseInt);

    Estudiante registrarEstudiante(Estudiante u) throws Exception;

    List<Estudiante> listarEstudiantes();
}
