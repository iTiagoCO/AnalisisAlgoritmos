package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categorias;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.EstudianteRepo;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class CategoriasServicioImpl implements  CategoriaServicio{

    private final CategoriaRepo categoriaRepo;

    public CategoriasServicioImpl(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }


    @Override
    public List<Categorias> listarCategorias() {
        return categoriaRepo.findAll();
    }


}
