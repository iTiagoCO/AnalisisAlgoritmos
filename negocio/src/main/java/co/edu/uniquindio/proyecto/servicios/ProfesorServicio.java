package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProfesorServicio {

    boolean getAdminEmail(String email) throws Exception;
    Profesor registrarProfesor(Profesor u) throws Exception;
    Profesor login(String email, String password) throws Exception;
    public List<Profesor> listarProfesores() throws  Exception;

    public List<Opcion> obtenerOpciones(int parseInt);
    Opcion obtenerOpcion (Integer codigo) throws Exception;

    Categorias obtenerCategoria (Integer codigo) throws Exception;
    public List<Categorias> obtenerCategorias();
}
