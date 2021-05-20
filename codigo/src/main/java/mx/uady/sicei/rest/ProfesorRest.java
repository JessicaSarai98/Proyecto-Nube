package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.service.AlumnoSerivce;
import mx.uady.sicei.service.ProfesorService;

@RestController //Metaprogramacion
@RequestMapping("/api")
public class ProfesorRest{

    @Autowired
    private ProfesorService profesorService;

    //GET /api/profesores
    @GetMapping("/profesores")
    public ResponseEntity<List<Profesor>>getProfesores(){
        return ResponseEntity.ok().body(profesorService.getProfesores());
    }

    //POST /api/profesores
    @PostMapping("/profesores")
    public ResponseEntity<Profesor> postProfesores(@RequestBody @Valid ProfesorRequest request) throws URISyntaxException{
        Profesor profesor = profesorService.crearProfesor(request)
        
        //201 Created 
        //Header: Location
        return ResponseEntity
        .created(new URI("/profesores"+profesor.getId()))
        .body(profesor);
    }
}