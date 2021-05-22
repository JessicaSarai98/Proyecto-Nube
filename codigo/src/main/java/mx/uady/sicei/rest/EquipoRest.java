package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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



    @GetMapping("/equipos/{id}")
    public ResponseEntity<Equipo> buscarEquipoPorId(@PathVariable Integer id) {
        Equipo equipo = equipoService.buscarEquipoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(equipo);
    }


//     // // // Path Paramater
    @DeleteMapping("/equipos/{id}") // DELETE /alumnos/1001930
    public ResponseEntity<Void> eliminarProfesor(@PathVariable Integer id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.ok().build();
    }
}
