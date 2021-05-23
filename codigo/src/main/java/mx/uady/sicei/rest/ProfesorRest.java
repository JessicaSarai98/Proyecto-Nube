package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.request.ProfesorRequest;
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
        Profesor profesor = profesorService.crearProfesor(request);
        
        //201 Created 
        //Header: Location
        return ResponseEntity
        .created(new URI("/profesores"+profesor.getId()))
        .body(profesor);
    }
    
    //GET /api/profesores/id -> 200
    @GetMapping("/profesores/{id}")
    public ResponseEntity<Profesor> getProfesor(@PathVariable Integer id){
        return ResponseEntity.ok().body(profesorService.getProfesor(id));
    }

    @PutMapping("/profesores/{id}")
    public ResponseEntity<Profesor> putProfesores(@PathVariable Integer id, @RequestBody ProfesorRequest request)
        throws URISyntaxException{
            Profesor profesor = profesorService.editarProfesor(id,request);
            return ResponseEntity.ok().body(profesor);
    }

    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Integer id){
        profesorService.borrarProfesor(id);

        return ResponseEntity.noContent().build();
    }

    
}