package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class OpcionConverter implements Converter<Opcion>, Serializable {

    @Autowired
    private ProfesorServicio profesorServicio;

    @Override
    public Opcion getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Opcion opciones = profesorServicio.obtenerOpcion(Integer.parseInt(value));
            return opciones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Opcion value) {
        if(value != null){
            return String.valueOf(value.getCod());
        }
        return "";
    }
}

