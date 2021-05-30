package mx.uady.sicei.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import mx.uady.sicei.model.Alumno;

public class AuthRequest {

    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    private String usuario;
    
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String nombre;

    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message="La contraseña debe mayor de 8 caracteres y contener letras, numeros y al menos 1 caracter especial.")      
    private String password;
 
    private String licenciatura;
    
    private Integer equipo;

    
    // private Licenciatura licenciatura;

    public AuthRequest() {
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public AuthRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AuthRequest nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
    //nuevo
    public Integer getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Integer equipo) {
        this.equipo = equipo; 
    }
    public String getLicenciatura() {
        return this.licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura; 
    }
    //fin nuevo
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            "}";
    }
    
}
