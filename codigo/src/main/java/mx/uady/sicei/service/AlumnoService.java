package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List; 
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.Licenciatura;

import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import mx.uady.sicei.repository.EquipoRepository;

@Service
public class AlumnoService {
    private String password="1234asbd";

    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipoRepository equipoRepository;
    // POST, PUT, GET, GET by ID, DELETE para entidad Alumno

    @Transactional // Crear una transaccion
    public Alumno crearAlumno(AlumnoRequest request) {

        // System.out.println(request.getEquipo());
        // if(request.getEquipo() instanceof Integer)
        //     System.out.println("si es un integer");
        

        Usuario usuarioCrear = new Usuario();

        usuarioCrear.setUsuario(request.getUsuario());
        usuarioCrear.setPassword(this.password);
    
        String token = UUID.randomUUID().toString();
        usuarioCrear.setToken(token);
    
        Usuario usuarioGuardado = usuarioRepository.save(usuarioCrear);
            
        Alumno alumno = new Alumno();
        alumno.setNombre(request.getNombre());
        if (request.getEquipo()!=null && request.getEquipo()>0){
            Equipo equipo = equipoRepository.findById(request.getEquipo()).orElseThrow(()-> new NotFoundException());
            // System.out.println(equipo.toString());
            alumno.setEquipo(equipo);
        }
        // System.out.println("no entra ");
        alumno.setUsuario(usuarioGuardado); // Relacionar 2 entidades
        alumno = alumnoRepository.save(alumno);
        // return usuarioGuardado;
        return alumno;
    }

    @Transactional // Crear una transaccion
    public Alumno actualizarAlumno(Integer id,AlumnoRequest request) {

        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()-> new NotFoundException());

        // usuario.setId(id);
        alumno.setNombre(request.getNombre());
        if (request.getEquipo()!=null && request.getEquipo()!=0){
            Equipo equipo = equipoRepository.findById(request.getEquipo()).orElseThrow(()-> new NotFoundException());
            alumno.setEquipo(equipo);
        }
        // if (request.getEquipo()!=null){
        //     Equipo equipo = equipoRepository.findById(request.getEquipo()).orElseThrow(()-> new NotFoundException());
        //     alumno.setEquipo(equipo);
        // }

        // String token = UUID.randomUUID().toString();
        // usuario.setToken(token);
        
        alumno = alumnoRepository.save(alumno); 
        return alumno;
    }

    public List<Alumno> getAlumnos() {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add); // SELECT(id, nombre)
        return alumnos;
        // return alumnoRepository.findAll();
    }

    public List<Alumno> buscarAlumnos(String nombre) {
        return alumnoRepository.findByNombreContaining(nombre);
    }


    
// @Transactional // Crear una transaccion
    // public Usuario crear(UsuarioRequest request) {
    //     Usuario usuarioCrear = new Usuario();

    //     usuarioCrear.setUsuario(request.getUsuario());
    //     usuarioCrear.setPassword(request.getPassword());

    //     String token = UUID.randomUUID().toString();
    //     usuarioCrear.setToken(token);

    //     Usuario usuarioGuardado = usuarioRepository.save(usuarioCrear);
        
    //     Alumno alumno = new Alumno();

    //     // alumno.setNombre(request.getNombre());
    //     alumno.setUsuario(usuarioGuardado); // Relacionar 2 entidades

    //     alumno = alumnoRepository.save(alumno);

    //     return usuarioGuardado;
    // }


}
