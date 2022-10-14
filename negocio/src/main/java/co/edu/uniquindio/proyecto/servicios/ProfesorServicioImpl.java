package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.OpcionRepo;
import co.edu.uniquindio.proyecto.repositorios.ProfesorRepo;
import org.springframework.stereotype.Service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorServicioImpl implements ProfesorServicio {

    private final ProfesorRepo profeRepo;
    private final CategoriaRepo categoriaRepo;
    private final OpcionRepo opcionRepo;


    public ProfesorServicioImpl(ProfesorRepo profeRepo, CategoriaRepo categoriaRepo, OpcionRepo opcionRepo) {
        this.profeRepo = profeRepo;
        this.categoriaRepo = categoriaRepo;
        this.opcionRepo = opcionRepo;
    }

    @Override
    public boolean getAdminEmail(String email) throws Exception {
        Optional<Profesor> buscado = profeRepo.findByEmail(email);

        if (buscado.isPresent()) {
            return true;
        }
        return false;
    }


    public Profesor registrarProfesor(Profesor u) throws Exception {

        Optional<Profesor> buscado = profeRepo.findById(u.getCod());
        if (buscado.isPresent()) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "El codigo del usuario ya existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            throw new Exception("El codigo del usuario ya existe");
        }
        return profeRepo.save(u);
    }

    private Optional<Profesor> buscarPorEmail(String email) {
        return profeRepo.findByEmail(email);
    }

    @Override
    public Profesor login(String email, String password) throws Exception {
        return (Profesor) profeRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticación son incorrectos"));
    }


    @Override
    public List<Profesor> listarProfesores() {
        return profeRepo.findAll();
    }

    @Override
    public List<Opcion> obtenerOpciones(int parseInt) {
        return opcionRepo.findAll();
    }

    @Override
    public Opcion obtenerOpcion(Integer codigo) throws Exception {
        return opcionRepo.findById(codigo).orElse(null);
    }

    @Override
    public Categorias obtenerCategoria(Integer cod) {
        return categoriaRepo.findById(cod).orElse(null);
    }

    @Override
    public List<Categorias> obtenerCategorias() {
        return categoriaRepo.findAll();
    }
}
