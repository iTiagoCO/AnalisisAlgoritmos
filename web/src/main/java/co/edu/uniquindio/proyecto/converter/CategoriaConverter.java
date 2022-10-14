package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CategoriaConverter implements Converter<Categorias>, Serializable {

    @Autowired
    private ProfesorServicio profesorServicio;

    @Override
    public Categorias getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Categorias categorias = profesorServicio.obtenerCategoria(Integer.parseInt(value));
            return categorias;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Categorias value) {
        if(value != null){
            return String.valueOf(value.getCod());
        }
        return "";
    }
}
