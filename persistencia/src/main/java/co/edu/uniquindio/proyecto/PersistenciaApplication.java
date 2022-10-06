package co.edu.uniquindio.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Autor Santiago Poveda Garcia - Luis Felipe Toro Valencia
 *
 *  Clase Persistencia que contiene Main
 *  Ejecuta el proyecto y crea la base datos junto a sus tablas en la base de datos.
 */

@SpringBootApplication
public class PersistenciaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersistenciaApplication.class, args);
    }
}
