package mx.uady.sicei.service;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

// import jdk.nashorn.internal.ir.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.*;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.Licenciatura;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import mx.uady.sicei.repository.EquipoRepository;
import mx.uady.sicei.repository.TutoriaRepository;

@Service
public class AuthService {
    private String password="1234asbd";

    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipoRepository equipoRepository;
    
    @Autowired
    private TutoriaRepository tutoriaRepository;
    // POST, PUT, GET, GET by ID, DELETE para entidad Alumno

    @Transactional // Crear una transaccion
    public Alumno registrarAlumno(AlumnoRequest request) {
        Usuario usuarioCrear = new Usuario();

        usuarioCrear.setUsuario(request.getUsuario());
        usuarioCrear.setPassword(this.password);
    
        String token = UUID.randomUUID().toString();
        usuarioCrear.setToken(token);
    
        Usuario usuarioGuardado = usuarioRepository.save(usuarioCrear);
            
        Alumno alumno = new Alumno();
        alumno.setNombre(request.getNombre());
        if (request.getEquipo()!=null && request.getEquipo()>0){
            Equipo equipo = equipoRepository.findById(request.getEquipo()).orElseThrow(()-> new BadRequestException("El valor del equipo no existe"));
            alumno.setEquipo(equipo);
        }

        if (request.getLicenciatura()!=null && request.getLicenciatura()!=""){
            try {          
                Licenciatura lic= Licenciatura.valueOf(request.getLicenciatura());
                alumno.setLicenciatura(lic);
             } catch (IllegalArgumentException e) {                   
                throw new BadRequestException(
                  "Invalid value for Licenciatura " + ": " + request.getLicenciatura());
             }
        }
        alumno.setUsuario(usuarioGuardado); // Relacionar 2 entidades
        alumno = alumnoRepository.save(alumno);
        return alumno;
    }
 
 
    @Transactional // Crear una transaccion
    public String login(UsuarioRequest request) {

        Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario());
        // Optional<Usuario> usuario = usuarioRepository.findByUsuario(request.getUsuario());
        if (usuario==null){
            throw new NotFoundException();
        }
        // usuario.setPassword(request.getPassword());

        if(!usuario.getPassword().equals(request.getPassword())){
            throw new NotFoundException();
        }

        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        
        usuario = usuarioRepository.save(usuario); 
        return token;
        
    }


    @Transactional // Crear una transaccion
    public void logout(Integer id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()-> new NotFoundException());
        
        usuario.setToken(null);
        usuarioRepository.save(usuario); 
        
    }





}
