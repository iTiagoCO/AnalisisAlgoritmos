package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Profesor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfesorServicio {

    boolean getAdminEmail(String email) throws Exception;
    Profesor registrarProfesor(Profesor u) throws Exception;
    Profesor login(String email, String password) throws Exception;
    public List<Profesor> listarProfesores() throws  Exception;
}
