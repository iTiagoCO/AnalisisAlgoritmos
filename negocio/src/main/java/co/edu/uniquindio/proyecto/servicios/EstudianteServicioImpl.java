package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.repositorios.EstudianteRepo;

import javax.annotation.PostConstruct;
import java.util.List;

public class EstudianteServicioImpl implements EstudianteServicio{

    private List<Estudiante> usuarios;
    private final EstudianteRepo estudianteRepo;

    @PostConstruct
    public void init() {

        usuarios = estudianteRepo.findAll();
    }
    public EstudianteServicioImpl(EstudianteRepo estudianteRepo) {

        this.estudianteRepo = estudianteRepo;
    }

    @Override
    public Estudiante login(String email, String password) throws Exception {
        return estudianteRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrectos"));
}

    @Override
    public Estudiante obtenerUsuario(int parseInt) {
        return estudianteRepo.getById(parseInt);
    }
}
