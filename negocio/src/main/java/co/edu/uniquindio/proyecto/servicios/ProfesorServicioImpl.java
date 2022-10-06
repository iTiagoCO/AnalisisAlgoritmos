package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.repositorios.ProfesorRepo;
import org.springframework.stereotype.Service;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServicioImpl implements ProfesorServicio {

    private final ProfesorRepo profeRepo;

    public ProfesorServicioImpl(ProfesorRepo profeRepo) {
        this.profeRepo = profeRepo;
    }

    @Override
    public boolean getAdminEmail(String email) throws Exception {
        Optional<Profesor> buscado = profeRepo.findByEmail(email);

        if(buscado.isPresent()){
            return true;
        }
        return false;
    }


    public Profesor registrarProfesor(Profesor u) throws Exception {

        Optional<Profesor> buscado = profeRepo.findById(u.getCod());
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


        return profeRepo.save(u);
    }

    private Optional<Profesor> buscarPorEmail(String email){
        return profeRepo.findByEmail(email);
    }

    @Override
    public Profesor login(String email, String password) throws Exception {
        return (Profesor) profeRepo.findByEmailAndPassword(email,password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrectos"));
    }


    @Override
    public List<Profesor> listarProfesores() {
        return profeRepo.findAll();
    }
}
