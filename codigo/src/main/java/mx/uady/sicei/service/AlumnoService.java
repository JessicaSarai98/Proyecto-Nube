package mx.uady.sicei.service;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

// import jdk.nashorn.internal.ir.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.exception.*;
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
    public Alumno actualizarAlumno(Integer id,AlumnoRequest request) {
        // System.out.println(id);
        // System.out.println(request);
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()-> new NotFoundException());
        // System.out.println(alumno.toString());
        if (request.getNombre()!=null && request.getNombre().trim()!="" ){
            alumno.setNombre(request.getNombre());
        }
        if (request.getEquipo()==null ){
            alumno.setEquipo(null);
        }else
        {
            Equipo equipo = equipoRepository.findById(request.getEquipo()).orElseThrow(()-> new BadRequestException("El valor del equipo no existe"));
            alumno.setEquipo(equipo);
        }
        if (request.getLicenciatura()==null ){
            alumno.setLicenciatura(null);
        }else{
            try {          
                Licenciatura lic= Licenciatura.valueOf(request.getLicenciatura());
                alumno.setLicenciatura(lic);
             } catch (IllegalArgumentException e) {                   
                throw new BadRequestException("Invalid value for Licenciatura " + ": " + request.getLicenciatura());
             }
        }
        // System.out.println(alumno);
        alumno = alumnoRepository.save(alumno); 
        return alumno;
    }
    @Transactional(readOnly = true)
    public List<Alumno> getAlumnos() {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add); // SELECT(id, nombre)
        return alumnos;
        // return alumnoRepository.findAll();
    }
    // @Transactional()

    // public List<Alumno> buscarAlumnos(String nombre) {
    //     return alumnoRepository.findByNombreContaining(nombre);
    // }
    @Transactional(readOnly = true)
    public Alumno getById(Integer id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()-> new NotFoundException());
        return alumno;
    }
    @Transactional()
    public void delete(Integer id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()){
            //Verificar conecciones con tutorias y equipo 

            //
            alumnoRepository.deleteById(id);
            usuarioRepository.deleteById(alumno.get().getUsuario().getId());                            
        }
     }

 
}
