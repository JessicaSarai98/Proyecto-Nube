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

import mx.uady.sicei.model.DAOUser;
import mx.uady.sicei.model.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUsuarioService implements UserDetailsService{
	
	@Autowired
    private UsuarioRepository usuarioRepository;

	@Autowired 
	private DAOUser DaoUser; 

	@Autowired
	private PassswordEncoder bcryptEncoder;
	
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
	public UserDao save(UserDTO user){
		DAOUser newUser = new DAOUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return DAOUser.save(newUser);
	}
}




// public String login(UsuarioRequest request) {
// 	Usuario usuario = usuarioRepository.findByUsuario(request.getUsuario());
// 	if (usuario==null){
// 		throw new NotFoundException();
// 	}
// 	if(!passwordEncoder.matches(request.getPassword(), usuario.getPassword())){
// 		throw new BadRequestException();
// 	}
// 	String token = UUID.randomUUID().toString();
// 	usuario.setToken(token);
// 	usuario = usuarioRepository.save(usuario); 
// 	return token;
// }
