package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.entidades.Prueba;
import co.edu.uniquindio.proyecto.repositorios.PruebaRepo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PruebaServicioImpl implements PruebaServicio {

    private PruebaRepo pruebaRepo;

    public PruebaServicioImpl(PruebaRepo pruebaRepo) {
        this.pruebaRepo = pruebaRepo;
    }


    /*
    private int minutesToTest;
    private DateTime begin;
    private DateTime end;
    private DateTime current;
    private Thread time;

    public void start() {
        this.begin = new DateTime();
        this.end = new DateTime().plusMinutes(minutesToTest);

        time = new Thread(() -> {
            current = new DateTime();
            while (end.getMillis() - current.getMillis() > 0) {
                current = new DateTime();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PruebaServicio.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
            }
        });
        time.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(PruebaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long remainMillis() {
        long result = -1;
        if (begin != null && end != null && current != null) {
            result = end.getMillis() - current.getMillis();
        }
        return result;
    }
     */
    @Override
    public Prueba registrarPrueba(Prueba u) throws Exception {

        System.out.println("co.edu.uniquindio.proyecto.servicios.PruebaServicioImpl.registrarPrueba()"
                + pruebaRepo);

        return pruebaRepo.save(u);

    }

    
    public List<Prueba> buscarPruebasByProfesor(Profesor profesor) throws Exception {
       return pruebaRepo.buscarPruebasByProfesor(profesor);
    }

   @Override
    public List<Prueba> pruebaList() {

        return pruebaRepo.findAll();

    }

}
