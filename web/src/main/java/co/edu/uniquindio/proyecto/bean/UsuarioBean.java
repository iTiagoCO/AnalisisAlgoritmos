package co.edu.uniquindio.proyecto.bean;
import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.servicios.EstudianteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;


@Component
@ViewScoped
public class UsuarioBean implements Serializable {

    @Getter @Setter
    private Estudiante estudiante;

    @Getter
    @Setter
    private String email, password,nombre;
    @Getter
    @Setter
    private Integer edad;

    @Autowired(required = false)
    private EstudianteServicio estudianteServicio;


    public UsuarioBean(EstudianteServicio estudianteServicio) {
        this.estudianteServicio = estudianteServicio;
    }


    @PostConstruct
    public void inicializar(){
    estudiante = new Estudiante();

    }


    public void registrarUsuario(){
        try {
            estudianteServicio.registrarEstudiante(estudiante);
            FacesMessage msg= new FacesMessage (FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("register-bean", fm);
        }
    }
}
