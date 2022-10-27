package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Pregunta;

import java.util.List;

public interface EstadisticaServicio {

    public double media(List<Integer> v);

    public double moda(List<Integer> v);

    public double varianza(List<Integer> v);

    public List<Integer> cantPregCategor();

    public List<Integer> promPrCatAll();

    public double desviacion_tipica(List<Integer> v);

    List<Pregunta> preguntasPorCateg(Integer codCategoria);

    public List<Integer> promAllStudents();

    Integer promPuntajePrCat(Integer cod);
}
