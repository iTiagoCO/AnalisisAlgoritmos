package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepo extends JpaRepository<Categorias,Integer> {

    Optional<Categorias> findByCod(Integer cod);
}
