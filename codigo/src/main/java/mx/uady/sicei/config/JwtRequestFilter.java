package mx.uady.sicei.config;

import java.io.IOException;
import mx.uady.sicei.model.Usuario;
import mx.uady.sicei.repository.UsuarioRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private JwtTokenProvider jwtTokenProvider;

    @Override 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException{
            final String requestToken = request.getHeader("Authorization");
            String username = null;
            String jwtTokenProvider = null;
            TokenDecodificado token = null;

            if(requestToken!= null && requestToken.startsWith("Bearer")){
                jwtTokenProvider = requestToken.substring(7); 
                try{
                    token = TokenDecodificado.getDecod(jwtTokenProvider);
                    username = token.sub; 
                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                    System.out.println("El token ha expirado");
                }
            }else{
                logger.warn("El token no empieza con el string Bearer");
            }
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                Usuario usuario = this.usuarioRepository.findByUsuario(username);
                if(jwtTokenProvider.validateToken(token, usuario, jwtTokenProvider)){
                    Authentication authentication = new UsernamePasswordAuthenticationToken(usuario,null,null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(request, response); 

    }
}
