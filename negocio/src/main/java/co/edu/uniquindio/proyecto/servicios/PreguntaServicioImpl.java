package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Pregunta;
import co.edu.uniquindio.proyecto.repositorios.PreguntaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaServicioImpl implements PreguntaServicio{

    private final PreguntaRepo preguntaRepo;

    public PreguntaServicioImpl(PreguntaRepo preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }


    @Override
    public List<Pregunta> preguntasList() {
        return preguntaRepo.findAll();

    }

    @Override
    public Pregunta registrarPregunta(Pregunta u) throws Exception {
        Pregunta usuarioBuscadoCedula = obtenerPregunta(u.getCod());

        if (usuarioBuscadoCedula != null){
            throw new Exception("El código de pregunta ya esta registrado");
        }

        return preguntaRepo.save(u);
    }





    @Override
    public Pregunta actualizarPregunta(Pregunta u) throws Exception {
        Pregunta usuario = obtenerPregunta(u.getCod());

        if(usuario == null){
            throw new Exception("El usuario no existe");
        }
        return preguntaRepo.save(u);
    }

    @Override
    public Pregunta obtenerPregunta(Integer codigo) {
        return preguntaRepo.findById(codigo).orElse(null);
    }



    @Override
    public void eliminarPregunta(Integer codigo) throws Exception {
        Pregunta u = obtenerPregunta(codigo);

        if(u == null){
            throw new Exception("La pregunta no existe");
        }

        preguntaRepo.delete(u);
    }

}
