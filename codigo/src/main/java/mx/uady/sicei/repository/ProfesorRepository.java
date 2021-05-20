package mx.uady.sicei.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.Alumno;

@Repository
public interface profesorRepository extends CrudRepository<Profesor, Integer>{
    
}