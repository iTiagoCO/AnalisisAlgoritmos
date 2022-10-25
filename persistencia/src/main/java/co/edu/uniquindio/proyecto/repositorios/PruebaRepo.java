package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Profesor;
import co.edu.uniquindio.proyecto.entidades.Prueba;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PruebaRepo extends JpaRepository<Prueba,Integer> {

    
    
    @Query("select prueba from Prueba prueba where prueba.profesor = :profesor")
    public List<Prueba> buscarPruebasByProfesor(Profesor profesor) throws Exception;

}
