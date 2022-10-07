package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.servicios.EstudianteServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.FilterMeta;

import org.primefaces.model.MatchMode;
import org.primefaces.util.LangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

import static java.lang.Integer.getInteger;

@Component
@ViewScoped
@Getter
@Setter
public class ProfesorBean implements Serializable {

    @Autowired
    EstudianteServicio estudianteServicio;

    @Getter
    @Setter
    private List<Estudiante> estudianteList;

    @Getter
    @Setter
    private List<Estudiante> estudianteFilterList;

    @Getter
    @Setter
    private List<FilterMeta> filterBy;



    private boolean globalFilterOnly;

    @PostConstruct
    public void init() {
        estudianteList = estudianteServicio.listarEstudiantes();
        filterBy = new ArrayList<>();
        globalFilterOnly = false;

        filterBy = new ArrayList<>();


    }


    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueEmpty(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Estudiante estudiante = (Estudiante) value;
        return estudiante.getNombre().toLowerCase().contains(filterText)
                || estudiante.getFechaPresentacion().toString().toLowerCase().contains(filterText)
                || estudiante.getStatus().getTitulo().toLowerCase().contains(filterText)
                || estudiante.getPromedio() < filterInt;
    }

    public void toggleGlobalFilter() {
        setGlobalFilterOnly(!isGlobalFilterOnly());
    }

}
