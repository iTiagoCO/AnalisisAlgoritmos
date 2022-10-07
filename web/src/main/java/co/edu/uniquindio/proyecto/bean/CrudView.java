package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.servicios.PreguntaServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CrudView implements Serializable {

    @Inject
    PreguntaServicio preguntaServicio;

    @Getter
    @Setter
    private List<Pregunta> preguntasList;

    @Getter
    @Setter
    private List<Opcion> opcionList;

    private Pregunta pregunta;

    private List<Pregunta> selectedPreguntas;


    @PostConstruct
    public void init() {
        this.preguntasList = this.preguntaServicio.preguntasList();
    }

    public List<Pregunta> getProducts() {
        return preguntasList;
    }

    public Pregunta getSelectedProduct() {
        return pregunta;
    }

    public void setSelectedProduct(Pregunta selectedProduct) {
        this.pregunta = pregunta;
    }

    public List<Pregunta> getSelectedPreguntas() {
        return selectedPreguntas;
    }

    public void setSelectedPreguntas(List<Pregunta> selectedPreguntas) {
        this.selectedPreguntas = selectedPreguntas;
    }

    public void openNew() {
        this.pregunta = new Pregunta();
    }

    public void saveProduct() throws Exception {
        if (this.pregunta.getCod() == null && preguntaServicio.obtenerPregunta(this.pregunta.getCod())==null) {
            pregunta.setOpcionList(opcionList);
            this.preguntasList.add(this.pregunta);
            preguntaServicio.registrarPregunta(pregunta);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta agregada"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta actualizada"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() throws Exception {
        this.preguntasList.remove(this.pregunta);
        this.selectedPreguntas.remove(this.pregunta);
        this.preguntaServicio.eliminarPregunta(pregunta.getCod());
        this.pregunta = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta removida"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
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

}