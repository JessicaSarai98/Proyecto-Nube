package mx.uady.sicei.rest;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioRest {
// PUT, GET, GET by ID, DELETE para entidad Usuario
    @Autowired
    private UsuarioService usuarioService;


    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioRequest request) {
        Usuario u = usuarioService.actualizar(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerUsuario() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
        Usuario u = usuarioService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/quienSoy")
    // public ResponseEntity<Usuario> getLoggedUser(){
    //     Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return ResponseEntity.ok(usuario); 
    // }

    // @PostMapping("/register")
    // public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioRequest request) {
    //     Usuario u = usuarioService.crear(request);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(u);
    // }

    
    

}