package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.EstadisticaServicio;
import co.edu.uniquindio.proyecto.servicios.EstudianteServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.elements.Elements;
import org.primefaces.model.charts.optionconfig.elements.ElementsLine;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.primefaces.model.charts.radar.RadarChartDataSet;
import org.primefaces.model.charts.radar.RadarChartModel;
import org.primefaces.model.charts.radar.RadarChartOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private EstudianteServicio estudianteServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private EstadisticaServicio estadisticaServicio;

    @Getter
    @Setter
    private List<Estudiante> estudianteList;
    @Getter
    @Setter
    private LineChartModel lineModel;

    @Getter
    @Setter
    private HorizontalBarChartModel hbarModel;

    @Getter
    @Setter
    private PolarAreaChartModel polarAreaModel;

    @Getter
    @Setter
    private RadarChartModel radarModel;

    @PostConstruct
    public void init() throws Exception {
        estudianteList = estudianteServicio.listarEstudiantes();
        createLineModel();
        createHorizontalBarModel();
        createRadarModel();
        createPolarAreaModel();

    }


    /*
        Grafico areas Polares
     */
    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();


        List<Integer> integerList = estadisticaServicio.promAllStudents();

        double media = estadisticaServicio.media(integerList);
        Number mediaI = (int) media;
        values.add(mediaI);


        double moda = estadisticaServicio.moda(integerList);
        Integer modaI = (int) moda;

        values.add(modaI);

        double dt = estadisticaServicio.desviacion_tipica(integerList);
        Integer dtI = (int) dt;

        values.add(dtI);


        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Media");
        labels.add("Moda");
        labels.add("Desviacion tipica");

        data.setLabels(labels);

        polarAreaModel.setData(data);
    }


    //Grafico Radar
    private void createRadarModel() throws Exception {
        radarModel = new RadarChartModel();
        ChartData data = new ChartData();

        RadarChartDataSet radarDataSet = new RadarChartDataSet();
        radarDataSet.setLabel("Prom Ops Validas x Categoria");
        radarDataSet.setFill(true);
        radarDataSet.setBackgroundColor("rgba(255, 99, 132, 0.2)");
        radarDataSet.setBorderColor("rgb(255, 99, 132)");
        radarDataSet.setPointBackgroundColor("rgb(255, 99, 132)");
        radarDataSet.setPointBorderColor("#fff");
        radarDataSet.setPointHoverBackgroundColor("#fff");
        radarDataSet.setPointHoverBorderColor("rgb(255, 99, 132)");

        List<Number> dataVal = new ArrayList<>();
        //El promedio de opciones correctas asociadas a todas las preguntas de todas las categorias
        List<Integer> integerList = estadisticaServicio.promPrCatAll();

        for (Integer i : integerList) {
            dataVal.add(i);
        }
        radarDataSet.setData(dataVal);


        RadarChartDataSet radarDataSet2 = new RadarChartDataSet();
        radarDataSet2.setLabel("Preguntas x Categoria");
        radarDataSet2.setFill(true);
        radarDataSet2.setBackgroundColor("rgba(54, 162, 235, 0.2)");
        radarDataSet2.setBorderColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBackgroundColor("rgb(54, 162, 235)");
        radarDataSet2.setPointBorderColor("#fff");
        radarDataSet2.setPointHoverBackgroundColor("#fff");
        radarDataSet2.setPointHoverBorderColor("rgb(54, 162, 235)");


        List<Integer> integerList2 = estadisticaServicio.cantPregCategor();
        List<Number> dataVal2 = new ArrayList<>();
        for (Integer i : integerList2) {
            dataVal2.add(i);
        }


        radarDataSet2.setData(dataVal2);

        data.addChartDataSet(radarDataSet);
        data.addChartDataSet(radarDataSet2);


        // Agrego a las categorias como Labels
        List<Categorias> list = categoriaServicio.listarCategorias();
        List<String> labels = new ArrayList<>();

        for (Categorias element : list) {
            labels.add(element.getDescripcion());
        }
        data.setLabels(labels);

        /* Options */
        RadarChartOptions options = new RadarChartOptions();
        Elements elements = new Elements();
        ElementsLine elementsLine = new ElementsLine();
        elementsLine.setTension(0);
        elementsLine.setBorderWidth(3);
        elements.setLine(elementsLine);
        options.setElements(elements);

        radarModel.setOptions(options);
        radarModel.setData(data);

    }

    //Retorna las edades no repetidas los estudiantes registrados
    public List<Integer> edadesNoRep() {


        //Edades orden sin repetir mayor a menor
        Set<Integer> miHashSet = new HashSet<>();

        //Pasar numeros sin repetir
        for (int i = 0; i < estudianteList.size(); i++) {
            miHashSet.add(estudianteList.get(i).getEdad());
        }

        List<Integer> juntados = new ArrayList<>(miHashSet);
        juntados.sort(Integer::compareTo);


        return juntados;
    }

    //Grafico INDEX Modelo lineal
    public void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Object> values = new ArrayList<>();

        List<String> labels = promedioEdades(edadesNoRep());

        // Convert String to Number
        for (int i = 0; i < labels.size(); i++) {
            int number = Integer.parseInt(labels.get(i));
            values.add(number);
        }

        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Promedio x edad");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);


        List<Integer> cast = edadesNoRep();

        List<String> labels2 = new ArrayList<>(cast.size());
        cast.forEach(e -> labels2.add(String.valueOf(e)));
        data.setLabels(labels2);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Gráfico de linea");
        options.setTitle(title);

        lineModel.setOptions(options);
        lineModel.setData(data);

    }


    //Promedio de puntajes segun las edades
    public List<String> promedioEdades(List<Integer> juntados) {

        List<String> integerList = new ArrayList<>();
        int prom = 0;
        int cant = 0;


        for (int i = 0; i < juntados.size(); i++) {

            for (int j = 0; j < estudianteList.size(); j++) {

                if (juntados.get(i).equals(estudianteList.get(j).getEdad())) {
                    cant++;
                    prom += estudianteList.get(j).getPromedio();

                }


            }

            if (prom != 0) {
                prom = prom / cant;
                String aux = String.valueOf(prom);
                integerList.add(aux);
                prom = 0;
                cant = 0;
            }
        }

        return integerList;

    }


    public void createHorizontalBarModel() {
        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();
        List<Number> values = new ArrayList<>();

        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("Promedio x  edad");


        List<String> labels = promedioEdades(edadesNoRep());

        // Convert String to Number
        for (int i = 0; i < labels.size(); i++) {
            int number = Integer.parseInt(labels.get(i));
            values.add(number);
        }

        // Los valores que cambian osea los promedios no repetidos
        hbarDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        hbarDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);

        data.addChartDataSet(hbarDataSet);

        List<Integer> cast = edadesNoRep();

        List<String> labels2 = new ArrayList<>(cast.size());
        cast.forEach(e -> labels2.add(String.valueOf(e)));


        data.setLabels(labels2);
        hbarModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Gráfico de barras horizontales");
        options.setTitle(title);

        hbarModel.setOptions(options);
    }

}
