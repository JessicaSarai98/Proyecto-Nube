package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.profesorRepository;

@Service
public class ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;
    //falta tutoria

    public List<Profesor> getProfesores(){
        List<Profesor> profesores = new LinkedList<>():
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

    
}