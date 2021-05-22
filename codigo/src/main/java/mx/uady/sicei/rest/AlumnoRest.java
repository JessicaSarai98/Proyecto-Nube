package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.service.AlumnoService;

@RestController // Metaprogramacion
@RequestMapping("/api")
public class AlumnoRest {

    @Autowired
    private AlumnoService alumnoService;
    // POST, PUT, GET, GET by ID, DELETE para entidad Alumno
    // GET /api/alumnos
    @GetMapping("/alumnos")
    public ResponseEntity<List<Alumno>> getAlumnos() {
        return ResponseEntity.ok().body(alumnoService.getAlumnos());
    }

    @PutMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Integer id,  @RequestBody AlumnoRequest request) {
        Alumno u = alumnoService.actualizarAlumno(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<Alumno> buscarAlumno(@PathVariable Integer id) {
        Alumno u = alumnoService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }

    @GetMapping("/alumnos/buscar") // RequestParam = Query parameter -> ?llave=valor&llave=valor
    public ResponseEntity<List<Alumno>> searchAlumnos(@RequestParam("nombre") String nombre)  {
        return ResponseEntity.ok().body(alumnoService.buscarAlumnos(nombre));
    }

    // POST /api/alumnos
    @PostMapping("/alumnos")
    public ResponseEntity<Alumno> postAlumnos(@RequestBody @Valid AlumnoRequest request) throws URISyntaxException {
        
        // RequestBody le indica a Java que estamos esperando un request que cumpla con los campos del Objeto AlumnoRequest
        
        Alumno alumno = alumnoService.crearAlumno(request);

        // 201 Created
        // Header: Location
        return ResponseEntity
            .created(new URI("/alumnos/" + alumno.getId()))
            .body(alumno);
    }

    @DeleteMapping("/alumnos/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Integer id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}