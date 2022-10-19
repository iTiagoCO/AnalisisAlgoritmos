package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import co.edu.uniquindio.proyecto.servicios.ProfesorServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
@ViewScoped
public class CrudViewBean implements Serializable {

    @Value(value = "#{seguridadBean.profesorSesion}")
    private Profesor personaSesion;
    @Getter
    @Setter
    private boolean value, value2;

    @Autowired
    private PreguntaServicio preguntaServicio;
    @Autowired
    private ProfesorServicio profesorServicio;


    @Getter
    @Setter
    private List<Pregunta> preguntasList;

    @Getter
    @Setter
    private List<Opcion> opcionList;


    @Getter
    @Setter
    private List<Categorias> categoriasList;

    @Getter
    @Setter
    private Opcion opcion;

    @Getter
    @Setter
    private Pregunta pregunta;

    @Getter
    @Setter
    private String titulo, descripcion;

    @Getter
    @Setter
    Integer cod;

    @Getter
    @Setter
    private Categorias categoria;

    @Getter
    @Setter
    private List<Pregunta> selectedPreguntas;


    @PostConstruct
    public void init() {
        opcion = new Opcion();
        pregunta = new Pregunta();
        opcionList = new ArrayList<>();
        categoria = new Categorias();
        preguntasList = preguntaServicio.preguntasList();
        categoriasList = profesorServicio.obtenerCategorias();
    }

    public List<Pregunta> getSelectedPreguntas() {
        return selectedPreguntas;
    }

    public void setSelectedPreguntas(List<Pregunta> selectedPreguntas) {
        this.selectedPreguntas = selectedPreguntas;
    }


    // Guardar pregunta
    public String savePregunta() {

        try {
            System.out.println(pregunta);
            pregunta.setEsVisible(value);
            categoria = profesorServicio.obtenerCategoria(1);
            pregunta.setCategoria(categoria);
            preguntasList.add(pregunta);
            //pregunta.setOpcionList(opcionList);
            //preguntaServicio.registrarPregunta(pregunta);
            Pregunta p = preguntaServicio.registrarPregunta(pregunta);

            opcionList.forEach(op -> {
                try {
                    op.setPregunta(p);
                    profesorServicio.registrarOpcion(op);
                } catch (Exception e) {
                    e.printStackTrace();
                    FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("msjBeanPregunta", mensaje);
                }
            });

        } catch (Exception e) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msjBeanPregunta", mensaje);
        }

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Pregunta creada");
        FacesContext.getCurrentInstance().addMessage("msjBeanPregunta", mensaje);

        return null;
    }


    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedPreguntas.size();
            return size > 1 ? size + " Pregunta eliminada" : "1 pregunta seleccionada";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedPreguntas != null && !this.selectedPreguntas.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.preguntasList.removeAll(this.selectedPreguntas);
        this.selectedPreguntas = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Preguntas Removidas"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }


    //Crear opcion de pregunta
    public void crearOpcion() {
        System.out.println("OPC  -------------------" + opcion.getDescripcion());
        opcion.setEsCorrecta(value2);
        opcionList.add(opcion);
        opcion = new Opcion();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exitoso", "Opcion creada con éxito");
        FacesContext.getCurrentInstance().addMessage("msjBeanOpcion", mensaje);
    }
}