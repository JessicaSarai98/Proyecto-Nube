package mx.uady.sicei.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.uady.sicei.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByUsuario(String usuario);

    // SELECT * FROM usuarios where token = 'token';
    public Usuario findByToken(String token);
    // public Usuario findByUsuarioContaining(String nombre); // LIKE %nombre%

}