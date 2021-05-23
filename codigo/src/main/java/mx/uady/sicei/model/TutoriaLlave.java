package mx.uady.sicei.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TutoriaLlave implements Serializable{
    @Column(name="idalumno", insertable=false, updatable=false)
    private Integer idalumno; 

    @Column(name="idprofesor",insertable=false, updatable=false)
    private Integer idprofesor;

    public Integer getIdAlumno(){
        return idalumno;
    }

    public void setIdAlumno(Integer idalumno){
        this.idalumno = idalumno; 
    }

    public Integer getIdProfesor(){
        return idprofesor;
    }

    public void setIdProfesor(Integer idprofesor){
        this.idprofesor = idprofesor; 
    }
}