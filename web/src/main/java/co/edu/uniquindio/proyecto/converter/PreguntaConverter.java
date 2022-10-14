package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

public class PreguntaConverter implements Converter<Pregunta>, Serializable {

    @Autowired
    private PreguntaServicio preguntaServicio;

    @Override
    public Pregunta getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Pregunta pregunta = preguntaServicio.obtenerPregunta(Integer.parseInt(value));
            return pregunta;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Pregunta value) {
        if(value != null){
            return String.valueOf(value.getCod());
        }
        return "";
    }
}

