package co.edu.uniquindio.proyecto.servicios;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

public class PruebaServicioImpl implements PruebaServicio{

    private int minutesToTest;
    private DateTime begin;
    private DateTime end;
    private DateTime current;
    private Thread time;

    public void start(){
        this.begin = new DateTime();
        this.end = new DateTime().plusMinutes(minutesToTest);

        time = new Thread(() -> {
            current = new DateTime();
            while(end.getMillis() - current.getMillis() > 0){
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



    public long remainMillis(){
        long result = -1;
        if (begin != null && end != null && current != null) {
            result = end.getMillis() - current.getMillis();
        }
        return result;
    }

}
