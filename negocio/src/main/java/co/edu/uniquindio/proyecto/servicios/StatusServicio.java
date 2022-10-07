package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.entidades.Status;

import java.util.List;

public interface StatusServicio {

    public List<Status> listarStatus() throws  Exception;
}
