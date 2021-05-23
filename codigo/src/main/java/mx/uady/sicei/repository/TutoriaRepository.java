
package mx.uady.sicei.repository;
import java.util.List;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.uady.sicei.model.Tutoria;
import mx.uady.sicei.model.TutoriaLlave;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria, TutoriaLlave>{
    List<Tutoria> findByIdAlumno(Integer idalumno);
    List<Tutoria> findByIdProfesor(Integer idprofesor);

    public Tutoria findByTutoria(Integer Tutoria);
}

