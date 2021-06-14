package mx.uady.sicei.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.model.request.AuthRequest;
import mx.uady.sicei.service.AlumnoService;
import mx.uady.sicei.service.AuthService;
import org.springframework.security.core.userdetails.UserDetails;



import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@RestController // Metaprogramacion
// @RequestMapping("/api")
public class AuthRest {

    @Autowired
    private AuthService authService;

    
    private Usuario usuario; 

    @PostMapping("/register")
    public ResponseEntity<Alumno> postRegister(@RequestBody @Valid AuthRequest request) throws URISyntaxException {
   
        Alumno alumno = authService.registrarAlumno(request);

        //authService.sendEmail(usuario);

        return ResponseEntity
            .created(new URI("/alumnos/" + alumno.getId()))
            .body(alumno);
    }

    @PostMapping("/logout2")
    public ResponseEntity<Void> postLogout( ) throws URISyntaxException {
        UserDetails usuario =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        authService.logout(usuario.getUsername());
       return ResponseEntity.noContent().build();
    }
    

    // @PostMapping("/logout/{id}")
    // public ResponseEntity<Void> postLogout(@PathVariable Integer id ) throws URISyntaxException {
    //     authService.logout(id);
    //    return ResponseEntity.noContent().build();
    // }


    @PostMapping("/login")
    public ResponseEntity<String> postLogin(HttpServletRequest request2, @RequestBody  @Valid UsuarioRequest request) throws URISyntaxException {
        String token = authService.login(request,request2.getHeader("User-Agent"));
        return ResponseEntity.ok(token); 

    }



    @GetMapping("/self")
    public ResponseEntity<Usuario> getLoggedUser(){
        // Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails usuario =  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(authService.self(usuario.getUsername())); 
    }

    @RequestMapping("/hello")
	public String firstPage() {
		return "Hello World";
	}

}