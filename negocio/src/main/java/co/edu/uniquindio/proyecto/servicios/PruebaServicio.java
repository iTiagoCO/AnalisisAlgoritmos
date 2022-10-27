package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.entidades.Prueba;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface PruebaServicio {

    //public void start();
    //public long remainMillis();
    public Prueba registrarPrueba(Prueba u) throws Exception;
    List<Prueba> pruebaList();
    public List<Prueba> buscarPruebasByProfesor(Profesor profesor) throws Exception;
    public List<Prueba> buscarPruebasByEstudiante (String email) throws Exception;
}
