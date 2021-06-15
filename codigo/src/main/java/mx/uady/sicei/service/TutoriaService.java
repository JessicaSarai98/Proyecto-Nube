package mx.uady.sicei.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uady.sicei.exception.NotFoundException;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.TutoriaLlave;
import mx.uady.sicei.repository.TutoriaRepository;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.ProfesorRepository;
import mx.uady.sicei.model.request.TutoriaRequest;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@Transactional
@Service
public class TutoriaService{
    @Autowired
    private TutoriaRepository tutoriaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired 
    private ProfesorRepository profesorRepository;

    @Autowired
	JavaMailSender javaMailSender;

    private MailSender mailSender;

    public List<Tutoria> getTutorias(){
        List<Tutoria> tutorias = new LinkedList<>();
        tutoriaRepository.findAll().iterator().forEachRemaining(tutorias::add);
        return tutorias;
    }

    public Tutoria crearTutoria(TutoriaRequest request){
        Tutoria tutoria = new Tutoria();
        TutoriaLlave tutoriaLlave = new TutoriaLlave(); 
        Alumno alumno = new Alumno();
        Profesor profesor = new Profesor(); 
        alumno = alumnoRepository.findById(request.getIdAlumno())
            .orElseThrow(()-> new NotFoundException("El alumno no se encuentra"));
    
        profesor = profesorRepository.findById(request.getIdProfesor())
            .orElseThrow(()-> new NotFoundException("El profesor no se encuentra"));
        tutoriaLlave.setIdAlumno(request.getIdAlumno());
        tutoriaLlave.setIdProfesor(request.getIdProfesor());

        tutoria.setHoras(request.getHoras());
        tutoria.setId(tutoriaLlave);
        tutoria.setAlumno(alumno);
        tutoria.setProfesor(profesor);
        tutoria = tutoriaRepository.save(tutoria);

        return tutoria;
    
    }

    public Tutoria getTutoria(Integer idalumno, Integer idprofesor){
        TutoriaLlave tutoriaLlave = new TutoriaLlave();
        alumnoRepository.findById(idalumno)
        .orElseThrow(() -> new NotFoundException("El alumno no se encuentra."));

        profesorRepository.findById(idprofesor)
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra."));

        tutoriaLlave.setIdAlumno(idalumno);
        tutoriaLlave.setIdProfesor(idprofesor);

        return tutoriaRepository.findById(tutoriaLlave)
            .orElseThrow(() -> new NotFoundException("La tutoria no se encuentra."));
    }

    public Tutoria editarTutoria(Integer idalumno, Integer idprofesor, TutoriaRequest request){
        TutoriaLlave tutoriaLlave = new TutoriaLlave(); 

        alumnoRepository.findById(idalumno)
            .orElseThrow(() -> new NotFoundException("El alumno no se encuentra."));
        
        profesorRepository.findById(idprofesor)
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra."));
        
        tutoriaLlave.setIdAlumno(idalumno);
        tutoriaLlave.setIdProfesor(idprofesor);

        return tutoriaRepository.findById(tutoriaLlave).map(tutoria->{
            tutoria.setHoras(request.getHoras());
            return tutoriaRepository.save(tutoria);
        })
        .orElseThrow(() -> new NotFoundException("La tutoria no se encuentra."));
    
    }

    public void borrarTutoria(Integer idalumno, Integer idprofesor){
        TutoriaLlave tutoriaLlave = new TutoriaLlave(); 

        
        Alumno alumno= alumnoRepository.findById(idalumno)
            .orElseThrow(() -> new NotFoundException("El alumno no se encuentra."));

        profesorRepository.findById(idprofesor)
            .orElseThrow(() -> new NotFoundException("El profesor no se encuentra."));

        tutoriaLlave.setIdAlumno(idalumno);
        tutoriaLlave.setIdProfesor(idprofesor);

        tutoriaRepository.deleteById(tutoriaLlave);
        System.out.println(" "+tutoriaLlave.getIdProfesor()+" "+tutoriaLlave);
        //enviarCorreo("La tutoria con el profesor: "+tutoriaLlave.getIdProfesor()+"", alumno.getUsuario().getEmail(), "");
    }

    @Async
    public void enviarCorreo(String texto, String email, String subject){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            System.out.println("Correo enviando....");
            mailMessage.setFrom("ejemplo-karina@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(texto);
            mailSender.send(mailMessage);
            System.out.println("Correo enviado exitosamente");
        }catch(Exception e){            
            System.out.println("Error al enviar el email de registro desde enviar correo  \n"+ e.getMessage());
        }
    }


}