package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.repositorios.EstudianteRepo;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Estudiante registrarEstudiante(Estudiante u) throws Exception {

        Optional<Estudiante> buscado = estudianteRepo.findById(u.getCod());
        if(buscado.isPresent()){

            FacesMessage msg= new FacesMessage (FacesMessage.SEVERITY_WARN, "Alerta", "El codigo del usuario ya existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            throw new Exception("El codigo del usuario ya existe");
        }

        buscado= buscarPorEmail(u.getEmail());
        if(buscado.isPresent()){
            FacesMessage msg= new FacesMessage (FacesMessage.SEVERITY_WARN, "Alerta", "El email del usuario ya existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            throw new Exception("El email del usuario ya existe");
        }

        return estudianteRepo.save(u);
    }

    private Optional<Estudiante> buscarPorEmail(String email){
        return estudianteRepo.findByEmail(email);
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepo.findAll();
    }
}
