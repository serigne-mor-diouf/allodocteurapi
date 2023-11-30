package groupff.gmail.edu.sn.allodocteur.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import groupff.gmail.edu.sn.allodocteur.entites.Token;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
/* 

    public static final String AUTHORITIES_CLAIM_KEY = "authorities";


    public String extractProfile(String token) {
        return extractClaim(token, claims -> claims.get(AUTHORITIES_CLAIM_KEY, String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(Utilisateur user) {
        Map<String, Object> claims = new HashMap<>();
        List<String>authorities  = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
                //equivalent
        // List<String>authorities2 = new ArrayList<>();
        // for(GrantedAuthority g : user.getAuthorities()){
        //     authorities2.add(g.getAuthority()) ;
        // }

        claims.put(AUTHORITIES_CLAIM_KEY , authorities);
        Token token = null ;
        do{
           String token
        } while(token == null) 

        return createToken(claims, user);
    }

    private String createToken(Map<String, Object> claims, Utilisateur user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
*/

}
