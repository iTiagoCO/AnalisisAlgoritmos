package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Opcion;
import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.EstudianteRepo;
import co.edu.uniquindio.proyecto.repositorios.OpcionRepo;
import co.edu.uniquindio.proyecto.repositorios.PreguntaRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EstadisticaServicioImpl implements EstadisticaServicio {


    private final PreguntaRepo preguntaRepo;
    private final CategoriaRepo categoriaRepo;
    private final OpcionRepo opcionRepo;
    private final EstudianteRepo estudianteRepo;

    public EstadisticaServicioImpl(PreguntaRepo preguntaRepo, CategoriaRepo categoriaRepo, OpcionRepo opcionRepo, EstudianteRepo estudianteRepo) {
        this.preguntaRepo = preguntaRepo;
        this.categoriaRepo = categoriaRepo;
        this.opcionRepo = opcionRepo;
        this.estudianteRepo = estudianteRepo;
    }


    /**
     * Calcula de los valores que contenga un vector de doubles facilitado por el usuario
     *
     * @param v vector de doubles con los elemento introducidos por el usuario
     * @return la media de los valores de v
     */
    public double media(List<Integer> v) {
        double res = 0;
        for (int i = 0; i < v.size(); i++) {
            res += v.get(i);
        }

        return res / v.size();
    }

    /**
     * Calcula la moda, el valor más repetido, del vector de doubles v introducido por el usuario
     *
     * @param v vector de doubles con los elemento introducidos por el usuario
     * @return valor más repetido del vector
     */
    public double moda(List<Integer> v) {
        int cont = 0;
        double num = 0;
        for (int i = 0; i < v.size(); i++) {
            int aux = 0;
            for (int j = 0; j < v.size(); j++) {
                if (v.get(i) == v.get(j))
                    aux++;
            }
            if (aux >= cont) {
                cont = aux;
                num = v.get(i);
            }
        }
        return num;
    }

    /**
     * Calcula la desviacion tipica del vector de doubles v introducido por el usuario
     *
     * @param v vector de doubles con los elemento introducidos por el usuario
     * @return desviacion tipica del vector
     */
    public double desviacion_tipica(List<Integer> v) {
        double res = Math.sqrt(varianza(v));
        return res;
    }

    /**
     * Calcula la varianza del vector de doubles v introducido por el usuario
     *
     * @param v vector de doubles con los elemento introducidos por el usuario
     * @return la varianza del vector
     */
    public double varianza(List<Integer> v) {
        double m = media(v);
        double sum = 0;
        for (int i = 0; i < v.size(); i++) {
            sum += Math.pow(v.get(i), 2.0);
        }

        return sum / v.size() - Math.pow(m, 2.0);
    }


    /**
     * Calcula la cantidad de preguntas por categoria [1,3,5,3} etc
     *
     * @return Lista
     */
    public List<Integer> cantPregCategor() {

        List<Integer> integerList = new ArrayList<>();
        List<Pregunta> preguntaList = preguntaRepo.findAll();
        List<Categorias> categoriasList = categoriaRepo.findAll();


        Integer prom = 0;


        for (int i = 0; i < categoriasList.size(); i++) {

            for (int j = 0; j < preguntaList.size(); j++) {

                if (categoriasList.get(i).getCod().intValue() == preguntaList.get(j).getCategoria().getCod()) {

                    prom++;
                }


            }
            if (prom != 0) {

                integerList.add(prom);
                prom = 0;
            }
        }

        return integerList;

    }


    /**
     * El promedio de opciones correctas asociadas a todas las preguntas de todas las categorias
     *
     * @return Lista
     */
    public List<Integer> promPrCatAll() {

        List<Integer> list = new ArrayList<>();
        List<Categorias> categoriasList = categoriaRepo.findAll();

        for (Categorias categoria : categoriasList) {
            list.add(promPuntajePrCat(categoria.getCod()));
        }

        return list;
    }

    /**
     * El promedio de opciones correctas asociadas a la pregunta de una categoria
     *
     * @return Lista
     */
    public Integer promPuntajePrCat(Integer cod) {

        List<Pregunta> preguntaList = preguntaRepo.findAll();
        List<Opcion> opcionList = opcionRepo.findAll();

        Integer prom = 0;
        Integer cant = 0;


        for (int i = 0; i < preguntaList.size(); i++) {

            List<Opcion> opcionList1 = preguntaList.get(i).getOpcionList();

            if ((cod == preguntaList.get(i).getCategoria().getCod())) {

                for (int j = 0; j < opcionList1.size(); j++) {

                    if (opcionList1.get(j).getEsCorrecta()) {
                        prom += opcionList1.get(j).getValor();
                        cant++;
                    }

                }
                if (prom != 0) {
                    prom = prom / cant;
                }
            }
        }

        return prom;

    }


    /**
     * La cantidad de preguntas por categoria
     *
     * @param codCategoria de la categoria a buscar preguntas
     * @return Lista
     */
    public List<Pregunta> preguntasPorCateg(Integer codCategoria) {

        List<Pregunta> list = new ArrayList<>();

        List<Pregunta> preguntaList = preguntaRepo.findAll();

        for (Pregunta pregunta : preguntaList) {

            if (codCategoria == pregunta.getCategoria().getCod()) {
                list.add(pregunta);
            }
        }


        return list;
    }


    /**
     * Retorna la lista con los promedios de todos los estudiantes
     *
     * @return
     */
    public List<Integer> promAllStudents() {

        List<Integer> prom = new ArrayList<>();

        List<Estudiante> estudianteList = estudianteRepo.findAll();


        for (Estudiante e : estudianteList) {
            prom.add(e.getPromedio());
        }

        return prom;


    }


}


