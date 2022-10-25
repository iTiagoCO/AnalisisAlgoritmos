/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.PreguntaRespuesta;
import co.edu.uniquindio.proyecto.entidades.Calificacion;
import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.entidades.Prueba;
import co.edu.uniquindio.proyecto.servicios.EstudianteServicio;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import co.edu.uniquindio.proyecto.servicios.PruebaServicio;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import javax.enterprise.context.SessionScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@SessionScoped
public class EncuestasViewBean implements Serializable {

    @Autowired
    private PreguntaServicio preguntaServicio;
    @Autowired
    private PruebaServicio pruebaServicio;
    @Autowired
    private EstudianteServicio estudianteServicio;

    
    @Autowired
    private SeguridadBean seguridadBean;
    @Getter
    @Setter
    private List<Pregunta> preguntaList;

    @Getter
    //@Setter
    private int timeSegundos;
    @Getter
    @Setter
    private int timeRestante;

    @Getter
    @Setter
    private int tiempoLimite;
    @Getter
    //@Setter
    private String tiempoString;

    @Getter
    @Setter
    private Pregunta preguntaSeleccionada;

    private int indexPregunta;
    private int maxIndex;
    @Getter
    @Setter
    private List<Opcion> opciones;

    @Getter
    @Setter
    private Opcion opcioneSelecionada;

    @Getter
    @Setter
    private List<PreguntaRespuesta> opcionesRespondidas;
    @Getter
    @Setter
    private int califiacion;
    private int ESTATUS;

    @Getter
    @Setter
    private boolean mostrarRespuestas;
    private Integer id;

    @PostConstruct
    public void init() {

        preguntaList = preguntaServicio.preguntasList();
        indexPregunta = 0;
        opcionesRespondidas = new ArrayList<>();
        buscarPregunta();
        reiniciar();

    }

    private void buscarPregunta() {
        if (preguntaList.isEmpty()) {
            preguntaSeleccionada = null;
        } else {
            maxIndex = preguntaList.size();
        }

        if (indexPregunta > -1 && indexPregunta < preguntaList.size()) {

            preguntaSeleccionada = preguntaServicio.obtenerPregunta(
                    preguntaList.get(indexPregunta).getCod()
            );
            opciones = preguntaSeleccionada.getOpcionList();

        }
    }

    public void preguntaSiguiente() {
        indexPregunta++;
        if (indexPregunta > maxIndex) {
            indexPregunta = maxIndex;
        }
        //System.out.println("co.edu.uniquindio.proyecto.bean.EncuestasViewBean.preguntaSiguiente() " + indexPregunta);
        buscarPregunta();

    }

    public void preguntaAnterior() {
        indexPregunta--;

        if (indexPregunta < 0) {
            indexPregunta = 0;
        }

        buscarPregunta();

    }

    public void increment() {

        if (timeSegundos < tiempoLimite) {
            Date cuurrent = new Date(timeSegundos * 1000);
            tiempoString = convertTime(cuurrent);
            timeSegundos++;
            timeRestante--;
        } else {
            ESTATUS = -1;
            if(id == null){
                finalizo();
            }
        }
    }

    private String convertTime(Date time) {

        Format format = new SimpleDateFormat("mm:ss");
        return format.format(time);
    }

    public void reiniciar() {
        // tiempoLimite = 180;
        tiempoLimite = 30;
        timeSegundos = 0;
        timeRestante = tiempoLimite;
        mostrarRespuestas = false;
        opcioneSelecionada = null;
        id=null;
    }

    public void guardarRespuesta() {

        PreguntaRespuesta preguntaRespuesta = buscarPreguntaRespuesta(preguntaSeleccionada.getCod());

        if (preguntaRespuesta == null) {

            preguntaRespuesta = new PreguntaRespuesta();
            preguntaRespuesta.setCodPregunta(preguntaSeleccionada.getCod());
            preguntaRespuesta.setCodRespuesta(opcioneSelecionada.getCod());
            preguntaRespuesta.setPregunta(preguntaSeleccionada.getDescripcion());
            preguntaRespuesta.setRespuesta(opcioneSelecionada.getDescripcion());
            preguntaRespuesta.setCorrecta(opcioneSelecionada.getEsCorrecta());
            preguntaRespuesta.setValor(opcioneSelecionada.getValor());

            opcionesRespondidas.add(preguntaRespuesta);

        } else {
            preguntaRespuesta.setCodRespuesta(opcioneSelecionada.getCod());
            preguntaRespuesta.setPregunta(preguntaSeleccionada.getDescripcion());
            preguntaRespuesta.setRespuesta(opcioneSelecionada.getDescripcion());
            preguntaRespuesta.setCorrecta(opcioneSelecionada.getEsCorrecta());
            preguntaRespuesta.setValor(opcioneSelecionada.getValor());
        }
        sumarCalifiacion();

    }

    public PreguntaRespuesta buscarPreguntaRespuesta(int cod_pregunta) {

        return opcionesRespondidas.stream()
                .filter(pregunta -> cod_pregunta == (pregunta.getCodPregunta()))
                .findAny()
                .orElse(null);

    }

    private void sumarCalifiacion() {

        califiacion = opcionesRespondidas.stream().filter(opciones -> opciones.isCorrecta()).mapToInt(PreguntaRespuesta::getValor).sum();;

    }

    public void finalizo() {

        List<Estudiante> estudiantes = estudianteServicio.listarEstudiantes();
        if (!estudiantes.isEmpty()) {
            //tomanos el primnero por cuestion de tiempo
            Estudiante estudiante = estudiantes.get(0);
            Prueba prueba = new Prueba();
            prueba.setEstudiante(estudiante);
            // 0 termino dando finalizar
            prueba.setEstatus(ESTATUS);
            prueba.setFecha(LocalDateTime.now());

            /*
            Si tuvo una calificación mayor a 10 por ejemplo. Sería malo. 
            Mayor a 20, regular 
            Mayor a 25 bueno
             */
            if (isBetween(califiacion, 0, 20)) {
                prueba.setCalificacion(new Calificacion(2));
            }
            if (isBetween(califiacion, 20, 25)) {
                prueba.setCalificacion(new Calificacion(3));
            }
            if (califiacion > 25) {
                prueba.setCalificacion(new Calificacion(1));
            }

            try {

               
                prueba.setProfesor(seguridadBean.getProfesorSesion());
                //prueba.setCod(878);
                Prueba idPrueba = pruebaServicio.registrarPrueba(prueba);
                id= idPrueba.getCod();
                mostrarRespuestas = true;
            } catch (Exception ex) {
                Logger.getLogger(EncuestasViewBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            throw new UnsupportedOperationException("NO HAY ESTUDIANTES");
        }

    }

    public boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

}
