package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dtEditView")
@ViewScoped
public class EditView implements Serializable {

    private List<Pregunta> preguntaList;
    @Getter
    @Setter
    private List<Boolean> booleanList;

    @Getter
    @Setter
    private Pregunta pregunta;

    @Inject
    private PreguntaServicio service;

    @PostConstruct
    public void init() {
        preguntaList = service.preguntasList();
        booleanList.add(true);
        booleanList.add(false);
    }

    public List<Pregunta> getProducts1() {
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
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getCod()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda de pregunta cambiada", "Viejo: " + oldValue + ", Nuevo:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}