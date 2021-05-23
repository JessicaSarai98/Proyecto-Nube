package mx.uady.sicei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="profesores")
public class Profesor{
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id; 

    @Column(name="nombre")
    private String nombre;
    
    @Column
    private Integer horas;

    public Profesor(){}

    public Profesor(Integer id, String nombre, Integer horas){
        this.id=id;
        this.nombre = nombre;
        this.horas = horas;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public Integer getHoras(){
        return this.horas;
    }

    public void setHoras(Integer horas){
        this.horas=horas;
    }

    public Profesor id(Integer id){
        this.id=id;
        return this;
    }

    public Profesor nombre(String nombre){
        this.nombre=nombre;
        return this;
    }

    public Profesor horas(Integer horas){
        this.horas=horas;
        return this;
    }

    @Override
    public String toString(){
        return "{" +
        "id='" + getId() +"'"+
        ", nombre='" + getNombre() +"'" +
        ", horas='" +getHoras() + "'" +
        "}";
    }
}