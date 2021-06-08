package mx.uady.sicei.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.DAOUser;

@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    
}
