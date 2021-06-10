package mx.uady.sicei.service;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import mx.uady.sicei.config.JwtTokenUtil;

@Service
public class JwtUsuarioService implements UserDetailsService{
	
	@Autowired
    private UsuarioRepository usuarioRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsuario(username);

		if (usuario!=null) {
			return new User(usuario.getUsuario(), usuario.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public String authToken(String username)  {
		Usuario usuario = usuarioRepository.findByUsuario(username);

		if (usuario!=null) {

			UserDetails userDetails = new User(usuario.getUsuario(), usuario.getPassword(),	new ArrayList<>());
			String token = jwtTokenUtil.generateToken(userDetails);

			usuario.setToken(token);
			usuario = usuarioRepository.save(usuario); 
			
			return token;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}


	

}


 
