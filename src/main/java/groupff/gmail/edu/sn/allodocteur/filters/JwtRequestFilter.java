package groupff.gmail.edu.sn.allodocteur.filters;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import groupff.gmail.edu.sn.allodocteur.entites.Token;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.jwt.UserDetailsServiceImpl;
import groupff.gmail.edu.sn.allodocteur.services.TokenService;
import groupff.gmail.edu.sn.allodocteur.services.UtilisateurService;
import groupff.gmail.edu.sn.allodocteur.util.JwtUtil;

import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

     @Autowired
    private  UtilisateurService utilisateurService ; 

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService  tokenService ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("header Authorization");
            String tokenValue = authHeader.substring(7);
            if(tokenService.isTokenUtilisateurValide(tokenValue)){
                Token token = tokenService.findToken(tokenValue);
                Utilisateur user = token.getUtilisateur();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }else{
                System.out.println("token invalid");
            }
        }

        filterChain.doFilter(request, response);
    }
}
