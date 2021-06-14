package mx.uady.sicei.service;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

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
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import mx.uady.sicei.repository.EquipoRepository;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.service.AuthService;

@Service
public class AlumnoService {
    private String password="1234asbd";
    private String actualizaciones, alumnoData = "";

    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipoRepository equipoRepository;
    
    @Autowired
    private TutoriaRepository tutoriaRepository;
    // POST, PUT, GET, GET by ID, DELETE para entidad Alumno

    @Autowired
    private AuthService authService;

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
        actualizaciones = alumnoData = "";
        //System.out.println("ID: "+id);
        //System.out.println("ALUMNO: "+request);
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(()-> new NotFoundException());
        //System.out.println("ALUMNO: "+alumno);
        if (request.getNombre()!=null && request.getNombre().trim()!="" ){
            alumnoData+=" Nombre del usuario: " +alumno.getNombre();
            actualizaciones+= " Nombre del usuario: "+request.getNombre();
            alumno.setNombre(request.getNombre());
        }
        if (request.getEquipo()==null ){
            alumno.setEquipo(null);
        }else
        {
            //System.out.println("EQUIPO", type alumno);
            Equipo equipo = equipoRepository.findById(request.getEquipo()).orElseThrow(()-> new BadRequestException("El valor del equipo no existe"));
            alumnoData+="\n Tenia al equipo: "+alumno.getEquipo().getModelo();
            actualizaciones+="\n Tiene el equipo: "+equipo.getModelo().toString();
            alumno.setEquipo(equipo);
        }
        if (request.getLicenciatura()==null ){
            alumno.setLicenciatura(null);
        }else{
            try {          
                Licenciatura lic= Licenciatura.valueOf(request.getLicenciatura());
                alumnoData+="\n Licenciatura: "+alumno.getLicenciatura();
                actualizaciones+= "\n Licenciatura: "+request.getLicenciatura();
                alumno.setLicenciatura(lic);
             } catch (IllegalArgumentException e) {                   
                throw new BadRequestException("Invalid value for Licenciatura " + ": " + request.getLicenciatura());
             }
        }
        alumno = alumnoRepository.save(alumno); 
        authService.enviarCorreo("Sus datos se han actualizado correctamente :) \n\n Los datos que tenía antes del cambio son: \n" + alumnoData+ 
         "\n\n\n Los datos nuevos son:\n"+actualizaciones,alumno.getUsuario().getEmail(),"Datos actualizados");
        return alumno;
    }
    @Transactional(readOnly = true)
    public List<Alumno> getAlumnos() {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add); // SELECT(id, nombre)
        return alumnos;
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
        // Delete del alumno debe validar que no existan tutorias correspondientes. Debe eliminar el Usuario también 
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()){
            //Verificar conecciones con tutorias  
            System.out.println(alumno.get());
            List<Tutoria> tutoria = tutoriaRepository.findByAlumno(alumno.get());
            System.out.println(tutoria);

            if (tutoria.size() > 0)
                throw new BadRequestException("El alumno esta enlazado con una tutoria");

            alumnoRepository.deleteById(id);
            usuarioRepository.deleteById(alumno.get().getUsuario().getId());                            
        }
     }

 
}
