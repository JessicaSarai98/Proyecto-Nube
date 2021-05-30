package mx.uady.sicei.config;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javassist.bytecode.SignatureAttribute;

import java.time.ZonedDateTime;

import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.UsuarioRepository;
import io.jsonwebtoken.JwtException;

public class JwtTokenProvider implements Serializable{
    
    private static final long serialVersionUID= 2569800841756370596L;
    private String secretKey= "java";
    public static final long JWT_TOKEN_VALIDITY = 5*60*1000;

    @PostConstruct
    void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("Auth", username);
        Date now = new Date();
        return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime()+ JWT_TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Autowired
    private UsuarioRepository usuarioRepository; 

    public Authentication getAuthentication(String token){
        String username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        UsuarioRepository usuarioRepository = usuarioRepository.findByUsuario(username);
        return new UsernamePasswordAuthenticationToken(userRepository, "", userRepository.getAuthorities()); 



    }
    //recuperar el nombre del usuario del token dado
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims); 
    }

    //recupera toda la informacion del token y necesita de la llave secreta
    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().parseClaimsJwt(token).getBody();
    }

    //generando token para el usuario
    public String generateToken(Usuario usuario){
        Map<String, Object> claims = new HashMap<>(); 
        claims.put("ext", ZonedDateTime.now().toInstant().toEpochMilli()+ JWT_TOKEN_VALIDITY);
        return doGenerateToken(claims, usuario.getUsuario(), usuario.getToken());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String secret){
        System.out.printl(new Date(ZonedDateTime.now().toInstant().toEpochMilli()).toString());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(ZonedDateTime.now().toInstant().toEpochMilli()))
                        .setExpiration(new Date(ZonedDateTime.now().toInstant().toEpochMilli()+ JWT_TOKEN_VALIDITY))
                        .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Boolean isTokenExpired(TokenDecodificado token) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("SSS");
			Date date = new Date(Long.parseLong(token.ext));
			return date.before(new Date());
		}
		catch(Exception e){			
			e.printStackTrace();
			return true;
		}
	}

    public Boolean validateToken(TokenDecodificado token, Usuario usuario, String jwt){
        final String username = usuario.getUsuario();
        try{
            Jwts.parser().setSigningKey(usuario.getToken()).parseClaimsJws(jwt).getBody();

        }catch(JwtException ex){
            return false;
        }
        return (username.equals(usuario.getUsuario()) && !isTokenExpired(token));
    }
}
