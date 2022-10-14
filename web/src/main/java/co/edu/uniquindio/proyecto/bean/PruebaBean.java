package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
@Named
@Getter
@Setter
public class PruebaBean implements Serializable {


    @Autowired
    PreguntaServicio preguntaServicio;

    @Getter
    @Setter
    private List<Pregunta> preguntaList;

    @Getter
    @Setter
    Pregunta pregunta;

    @Getter
    @Setter
    private int number;


    @PostConstruct
    public void init() {
        preguntaList = preguntaServicio.preguntasList();

    }


    public void increment() {
        number++;
    }


}
