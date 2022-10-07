package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Status;
import co.edu.uniquindio.proyecto.repositorios.StatusRepo;

import java.util.List;

public class StatusServicioImpl implements StatusServicio{

private final StatusRepo statusRepo;

    public StatusServicioImpl(StatusRepo statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Override
    public List<Status> listarStatus() throws Exception {
        return statusRepo.findAll();
    }
}
