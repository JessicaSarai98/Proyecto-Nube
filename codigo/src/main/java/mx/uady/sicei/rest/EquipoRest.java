package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.request.EquipoRequest;
import mx.uady.sicei.service.EquipoService;

@RestController
@RequestMapping("/api")
public class EquipoRest {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> obtenerUsuario() {
        List<Equipo> equipos = equipoService.getEquipos();
        return ResponseEntity.ok(equipos);
    }

    // POST /api/equipos
    @PostMapping("/equipos")
    public ResponseEntity<Equipo> postEquipos(@RequestBody @Valid EquipoRequest request) throws URISyntaxException {
        // RequestBody le indica a Java que estamos esperando un request que cumpla con los campos del Objeto EquipoRequest
        Equipo equipo = equipoService.crearEquipo(request);
        // 201 Created
        // Header: Location
        return ResponseEntity
            .created(new URI("/equipos/" + equipo.getId()))
            .body(equipo);
    }

    // PUT /api/equipos/:id
    @PutMapping("/equipos/{id}") // PUT /alumnos/1001930
    public ResponseEntity<Equipo> editarEquipo(@PathVariable Integer id, @Valid  @RequestBody EquipoRequest request) {
        Equipo equipoEditado = equipoService.editarEquipo(id, request);
        return ResponseEntity.ok().body(equipoEditado);
    }



    // @GetMapping("/usuarios/{id}")
    // public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
    //     Usuario u = usuarioService.getUsuario(id);
    //     return ResponseEntity.status(HttpStatus.OK).body(u);
    // }
    

     // // Path Paramater
//   @PutMapping("/profesores/{noEmpleado}") // PUT /alumnos/1001930
//   public ResponseEntity<Profesor> editarProfesor(@PathVariable String noEmpleado, @Valid  @RequestBody Profesor profesor) {
//       Profesor profesorEditado = profesorService.editarProfesor(noEmpleado, profesor);
//       return ResponseEntity.ok().body(profesorEditado);
//   }


//     // // // Path Paramater
//   @DeleteMapping("/profesores/{noEmpleado}") // DELETE /alumnos/1001930
//   public ResponseEntity<Void> eliminarProfesor(@PathVariable String noEmpleado) {
//       profesorService.eliminarProfesor(noEmpleado);
//       return ResponseEntity.ok().build();
//   }
}
