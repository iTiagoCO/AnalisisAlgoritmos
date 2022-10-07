package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dtAddRowView")
@ViewScoped
public class AddRowView implements Serializable {

    private List<Pregunta> preguntaList;

    @Getter
    @Setter
    private Pregunta pregunta;

    @Inject
    private PreguntaServicio service;

    @PostConstruct
    public void init() {
        preguntaList = service.preguntasList();
    }

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setService(PreguntaServicio service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent<Pregunta> event) {
        FacesMessage msg = new FacesMessage("Pregunta editada", String.valueOf(event.getObject().getCod()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Pregunta> event) {
        FacesMessage msg = new FacesMessage("Edit cancelado", String.valueOf(event.getObject().getCod()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() throws Exception {
        // Add one new product to the table:
        Pregunta newPregunta = pregunta;
        preguntaList.add(newPregunta);
        service.registrarPregunta(newPregunta);

        FacesMessage msg = new FacesMessage("Nueva pregunta añadida", String.valueOf(pregunta.getCod()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}