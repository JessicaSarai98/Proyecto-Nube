
package mx.uady.sicei.repository;
import java.util.List;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import mx.uady.sicei.model.Alumno;
import mx.uady.sicei.model.Profesor;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.TutoriaLlave;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria, TutoriaLlave>{
    // List<Tutoria> findByAlumno(Integer idalumno);
    List<Tutoria> findByProfesor(Profesor profesor);
    List<Tutoria> findByAlumno(Alumno alumno);
    // public Tutoria findByTutoria(Integer Tutoria);
}

