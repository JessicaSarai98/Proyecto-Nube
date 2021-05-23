package mx.uady.sicei.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import javax.persistence.EmbeddedId;

@Entity
@Table(name="tutorias")
public class Tutoria{

    @EmbeddedId
    private TutoriaLlave id; 

    @Column private Integer horas;

    @ManyToOne(optional=false)
    @JoinColumn(name="id_alumno", referencedColumnName="id", insertable=false, updatable=false)
    Alumno alumno; 

    @ManyToOne(optional=false)
    @JoinColumn(name="id_profesor",referencedColumnName="id",updatable=false)
    Profesor profesor;

    public Tutoria(){}

    public void setHoras(Integer horas){
        this.horas = horas;
    }

    public Integer getHoras(){
        return horas;
    }

    public void setId(TutoriaLlave id){
        this.id = id; 
    }

    public TutoriaLlave getId(){
        return id; 
    }

    public Alumno getAlumno(){
        return alumno; 
    }

    public void setAlumno(Alumno alumno){
        this.alumno= alumno; 
    }

    public Profesor getProfesor(){
        return profesor;
    }

    public void setProfesor(Profesor profesor){
        this.profesor = profesor; 
    }
}