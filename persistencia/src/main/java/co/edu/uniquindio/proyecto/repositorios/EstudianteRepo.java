package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Estudiante;
import co.edu.uniquindio.proyecto.entidades.Prueba_Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepo extends JpaRepository<Estudiante,Integer> {

    List<Estudiante> findAllByNombreContains(String nombre);
    List<Estudiante> findAll();
    Optional<Estudiante> findByEmail(String email);
    Optional<Estudiante> findByEmailAndPassword(String email, String clave);

    //Retornar todas las pruebas del estudiante
    @Query("select p from Estudiante u, IN (u.prueba_estudianteList) p where u.email= :email")
    List<Prueba_Estudiante> obtenerPruebas(String email);





}
