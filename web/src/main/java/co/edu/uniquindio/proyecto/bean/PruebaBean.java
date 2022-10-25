package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Prueba;
import co.edu.uniquindio.proyecto.servicios.PruebaServicioImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ViewScoped
@Named
public class PruebaBean implements Serializable {

    @Autowired
    private PruebaServicioImpl pruebaServicioImpl;

    @Getter
    @Setter
    private List<Prueba> pruebasByProfesor;

    @Autowired
    private SeguridadBean seguridadBean;

    @PostConstruct
    public void init() {

        /*
        if (seguridadBean.getProfesorSesion() != null) {
            try {
                pruebasByProfesor = pruebaServicioImpl.buscarPruebasByProfesor(seguridadBean.getProfesorSesion());
            } catch (Exception ex) {
                Logger.getLogger(PruebaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         */
        if (seguridadBean.getProfesorSesion() != null) {
            try {
                pruebasByProfesor = pruebaServicioImpl.pruebaList();
            } catch (Exception ex) {
                Logger.getLogger(PruebaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
