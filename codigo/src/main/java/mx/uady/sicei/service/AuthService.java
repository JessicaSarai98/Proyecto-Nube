package mx.uady.sicei.service;


import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import jdk.nashorn.internal.runtime.regexp.joni.ast.AnyCharNode;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import mx.uady.sicei.config.EmailSender;
import mx.uady.sicei.config.JwtTokenUtil;
// import mx.uady.sicei.config.JwtTokenUtil;
import mx.uady.sicei.exception.*;
import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.model.Equipo;
import mx.uady.sicei.model.Licenciatura;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.request.AlumnoRequest;
import mx.uady.sicei.model.request.UsuarioRequest;
import mx.uady.sicei.model.request.AuthRequest;
import mx.uady.sicei.repository.AlumnoRepository;
import mx.uady.sicei.repository.UsuarioRepository;
import mx.uady.sicei.repository.EquipoRepository;
import mx.uady.sicei.repository.TutoriaRepository;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class AuthService {
    // private String password="1234asbd";

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
    private PasswordEncoder passwordEncoder;

    
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Autowired
	JavaMailSender javaMailSender;

    private MailSender mailSender;

    @Autowired
    public AuthService(MailSender mailSender){
        this.mailSender=mailSender;
    }

    @Autowired
    private EmailService emailService;



    @Transactional // Crear una transaccion
    public Alumno registrarAlumno(AuthRequest request) {
        
        Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario());
        if (usuario!=null){
            throw new BadRequestException("El valor del campo usuario no esta disponible");
        }

        Usuario usuarioCrear = new Usuario();
        usuarioCrear.setUsuario(request.getUsuario());
        usuarioCrear.setPassword(passwordEncoder.encode(request.getPassword()));
        
        usuarioCrear.setEmail(request.getEmail());
        // String token = UUID.randomUUID().toString();

        UserDetails userDetails = new User(usuarioCrear.getUsuario(), usuarioCrear.getPassword(),	new ArrayList<>());
        String token = jwtTokenUtil.generateToken(userDetails);

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
        
        enviarCorreo("Su registro fue completado con exito",request.getEmail(),"Registro completado");
        //emailService.enviarCorreoRegistro(usuarioCrear);
        return alumno;
    }
 
 
    @Transactional // Crear una transaccion
    public String login(UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario());
        if (usuario==null){
            throw new NotFoundException();
        }
        if(!passwordEncoder.matches(request.getPassword(), usuario.getPassword())){
            throw new BadRequestException();
        }
        String token = UUID.randomUUID().toString();
        usuario.setToken(token);
        usuario = usuarioRepository.save(usuario); 
        return token;
    }


    @Transactional // Crear una transaccion
    public void logout(String usuario) {
        Usuario user = usuarioRepository.findByUsuario(usuario);
        // .orElseThrow(()-> new NotFoundException());
        if (user!=null){
            user.setToken(null);
            usuarioRepository.save(user); 
        }
            // new NotFoundException();

        
    }
 
    @Transactional // Crear una transaccion
    public Usuario self(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
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
