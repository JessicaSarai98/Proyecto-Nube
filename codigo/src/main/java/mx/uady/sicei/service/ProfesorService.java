package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;

import mx.uady.sicei.exception.NotFoundException;

@Service
public class ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;
    //falta tutoria

    public List<Profesor> getProfesores(){
        List<Profesor> profesores = new LinkedList<>();
        profesorRepository.findAll().iterator().forEachRemaining(profesores::add); //SELECT(id,nombre)
        return profesores;
    }

    public List<Profesor> buscarProfesores(String nombre){
        return profesorRepository.findByNombreContaining(nombre);
    }
    public Profesor crearProfesor(ProfesorRequest request){
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getNombre());
        profesor.setHoras(request.getHoras());
        profesor = profesorRepository.save(profesor);
        return profesor;
    }

    public Profesor getProfesor(Integer id){
        return profesorRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("El profesor no pudo ser encontrado"));
    }

    public Profesor editarProfesor(Integer id, ProfesorRequest request){
        return profesorRepository.findById(id).map(profesor ->{
            profesor.setNombre(request.getNombre());
            profesor.setHoras(request.getHoras());
            return profesorRepository.save(profesor);
        })
        .orElseThrow(()-> new NotFoundException("El profesor no fue encontrado"));
    }

    public void borrarProfesor(Integer id){
        List<Profesor> profesores = new LinkedList<>();
        profesorRepository.findAll().iterator().forEachRemaining(profesores::add);
        if(profesores.size()<id|| id <=0){
            throw new NotFoundException("El profesor no pudo ser encontrado");
        }

        
    }

    
}