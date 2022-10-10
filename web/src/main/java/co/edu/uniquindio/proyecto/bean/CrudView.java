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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@Component
@ViewScoped
@Getter
@Setter
public class CrudView implements Serializable {

    @Value(value = "#{seguridadBean.profesorSesion}")
    private Profesor personaSesion;
    @Getter
    @Setter
    private boolean value, value2;

    @Inject
    PreguntaServicio preguntaServicio;
    @Inject
    ProfesorServicio profesorServicio;

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
    private Categorias categoria;

    private List<Pregunta> selectedPreguntas;


    @PostConstruct
    public void init() {
        opcion = new Opcion();
        opcionList = new ArrayList<>();
        this.preguntasList = this.preguntaServicio.preguntasList();
        this.categoriasList = this.profesorServicio.obtenerCategorias();
    }

    public List<Pregunta> getProducts() {
        return preguntasList;
    }

    public Pregunta getSelectedProduct() {
        return pregunta;
    }

    public void setSelectedProduct(Pregunta selectedProduct) {
        this.pregunta = selectedProduct;
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


    // Guardar pregunta
    public String savePregunta() throws Exception {

        if (preguntaServicio.obtenerPregunta(pregunta.getCod()) == null) {
            //No  existe la pregunta
            try {
                pregunta.setOpcionList(opcionList);
                pregunta.setEsVisible(value);
                pregunta.setCategoria(categoria);
                pregunta.setOpcionList(opcionList);
                opcionList.clear();

                this.preguntasList.add(this.pregunta);
                preguntaServicio.registrarPregunta(pregunta);
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msjBean", mensaje);
            }

        } else {

            //Si existe la pregunta

            try{
                pregunta.setOpcionList(opcionList);
                pregunta.setEsVisible(value);
                pregunta.setCategoria(categoria);
                pregunta.setOpcionList(opcionList);

                this.preguntasList.remove(pregunta);
                this.preguntasList.add(pregunta);

                preguntaServicio.actualizarPregunta(pregunta);

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta actualizada"));
            }catch (Exception e){
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msjBean", mensaje);
            }

        }

        return null;
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


    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    //Crear opcion de pregunta
    public String crearOpcion(){

        opcion.setPregunta(getSelectedProduct());
        opcion.setEsCorrecta(value2);

        //No es pregunta nueva
        if(getSelectedProduct()!=null){
            opcionList.clear();
            opcionList = getSelectedProduct().getOpcionList();
            opcionList.add(opcion);

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exitoso", "Opcion creada con éxito");
            FacesContext.getCurrentInstance().addMessage("msjBean",mensaje);
        }else{
            //Si es pregunta nueva
            opcionList.clear();
            opcionList.add(opcion);

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,"Exitoso", "Opcion creada con éxito");
            FacesContext.getCurrentInstance().addMessage("msjBean",mensaje);
        }
        return null;
    }
}