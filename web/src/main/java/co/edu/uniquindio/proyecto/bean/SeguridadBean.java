package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.servicios.EstudianteServicio;
import co.edu.uniquindio.proyecto.servicios.ProfesorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter
    @Setter
    private Estudiante estudiante;
    @Getter
    @Setter
    private boolean autenticado;
    @Autowired
    private ProfesorServicio profesorServicio;
    @Autowired
    private EstudianteServicio estudianteServicio;
    @Getter
    @Setter
    private boolean admin;
    @Getter
    @Setter
    private String email, password;
    @Getter
    @Setter
    private Profesor profesorSesion;

    public String iniciarSesion() throws Exception {
        if (!email.isEmpty() && !password.isEmpty()) {

            try {
                if (esProfe()) {
                    profesorSesion = profesorServicio.login(email, password);
                    admin = true;
                } else {
                    estudiante = estudianteServicio.login(email, password);
                    autenticado = true;
                }
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }


    private boolean esProfe() {
        try {
            return profesorServicio.getAdminEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }


    public boolean estaLogueado() {
        return autenticado || admin;
    }
}
