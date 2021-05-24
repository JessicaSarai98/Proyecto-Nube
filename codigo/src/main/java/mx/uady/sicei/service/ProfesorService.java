package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.request.ProfesorRequest;
import mx.uady.sicei.repository.ProfesorRepository;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.exception.*; 
@Transactional
@Service
public class ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private TutoriaRepository tutoriaRepository;

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

        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()){
            //Verificar conecciones con tutorias  
            System.out.println(profesor.get());
            List<Tutoria> tutoria = tutoriaRepository.findByProfesor(profesor.get());
            System.out.println(tutoria);

            if (tutoria.size() > 0)
                throw new BadRequestException("El profesor esta enlazado con una tutoria");

            profesorRepository.deleteById(id);
        }






        
    }

    
}