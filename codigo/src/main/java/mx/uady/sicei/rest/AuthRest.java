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
import org.springframework.web.bind.annotation.RestController;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.service.AlumnoService;

@RestController // Metaprogramacion
// @RequestMapping("/api")
public class AuthRest {


    @PostMapping("/register")
    public ResponseEntity<Alumno> postRegister(@RequestBody @Valid AlumnoRequest request) throws URISyntaxException {
   
        Alumno alumno = authService.registrarAlumno(request);
 
        return ResponseEntity
            .created(new URI("/alumnos/" + alumno.getId()))
            .body(alumno);
    }


    @PostMapping("/login")
    public ResponseEntity<String> postLogin(@RequestBody  @Valid UsuarioRequest request) throws URISyntaxException {
        String token = AuthService.login(request);
        return ResponseEntity.ok(token); 

    }
}