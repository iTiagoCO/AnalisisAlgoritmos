package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class QuizBean implements Serializable {

    @Autowired
    PreguntaServicio preguntaServicio;

    @Getter
    @Setter
    private List<Pregunta> preguntaList;

    @PostConstruct
    public void init() {
        preguntaList = preguntaServicio.preguntasList();

    }



}
