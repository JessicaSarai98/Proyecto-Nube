package mx.uady.sicei.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import mx.uady.sicei.model.Licenciatura;
import mx.uady.sicei.model.Equipo;

public class AlumnoRequest {

    @NotNull
    @Size(min = 5, max = 50)
    @NotEmpty
    private String usuario;
    
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String nombre;
 
    private String licenciatura;
    
    private Integer equipo;

    
    // private Licenciatura licenciatura;

    public AlumnoRequest() {
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public AlumnoRequest(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AlumnoRequest nombre(String nombre) {
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
