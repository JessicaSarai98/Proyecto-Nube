package mx.uady.sicei.service;

import mx.uady.sicei.config.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Usuario;

@Service
public class EmailService {
    private EmailSender emailSender;

    private final String EMAIL_SERVICE= "jessicasarai1698@gmail.com";
    
    private final String SUBJECT = "Bienvenido al SICEI";

    private final String id_email= "aaaaa";

    public EmailService(){
        emailSender = new EmailSender(); 
    }

    @Async
    public void enviarCorreoRegistro(Usuario usuario){
        try{
            emailSender.send(EMAIL_SERVICE, id_email, usuario.getEmail(), SUBJECT, mensaje(usuario.getUsuario()));

        }catch(Exception e){
            System.out.println("Error al enviar el email de registro");
        }
    }

    private String mensaje(String nombre){
        return String.format("Bienvenido al sicei alumno: %s", nombre);
    }



}
