package mx.uady.sicei.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import javax.transaction.Transactional;
import java.util.LinkedList;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.request.UsuarioRequest;

import mx.uady.sicei.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //@Autowired
    //private AlumnoRepository alumnoRepository;
// PUT, GET, GET by ID, DELETE para entidad Usuario

    @Transactional // Crear una transaccion
    public Usuario actualizar(Integer id,UsuarioRequest request) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new NotFoundException());

        usuario.setId(id);
        usuario.setUsuario(request.getUsuario());
        usuario.setPassword(request.getPassword());

        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        
        usuario = usuarioRepository.save(usuario); 
        return usuario;
        
    }

    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new LinkedList<>();
        usuarioRepository.findAll().iterator().forEachRemaining(usuarios::add);
        return usuarios;
    }

    @Transactional(readOnly = true)
    public Usuario getById(Integer id) {

        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new NotFoundException();
    }
    @Transactional()
    public void delete(Integer id) {
        try {
            usuarioRepository.deleteById(id);                        
        } catch (Exception e) {}
        
    }

 
}