package mx.uady.sicei.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Integer>{
    List<Profesor> findByNombreContaining(String nombre); // LIKE %nombre%
}