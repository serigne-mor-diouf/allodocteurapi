package groupff.gmail.edu.sn.allodocteur.services;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.entites.Token;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.TokenRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.UtilisateurRepository;
import groupff.gmail.edu.sn.allodocteur.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public static final String AUTHORITIES_CLAIM_KEY = "authorities";

    @Autowired
    private TokenRepository tokenRepository ;

    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private UtilisateurRepository utilisateurRepository ;

    public Token findToken(String value){
        return tokenRepository.findById(value).orElse(null);
    }

    public boolean isTokenUtilisateurValide(String tokenValue) {
        Token token = tokenRepository.findById(tokenValue).orElse(null);
        if (token == null) {
            return false;
        }
    
        String username = extractUsername(tokenValue);
        Utilisateur utilisateur = utilisateurRepository.findByEmail(username);
        
        // Vérifier si le token est valide, l'utilisateur est valide et le compte est activé
        return token.isTokenValide() && utilisateur.isValid() && utilisateur.isEnabled();
    }
    

     public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

     private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

      private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Token generateToken(Utilisateur user) {
        // Vérifier si l'utilisateur est activé avant de générer le token
        if (!user.isEnabled()) {
            throw new RuntimeException("Impossible de générer le token. Votre compte est désactivé. Veuillez contacter l'administrateur.");
        }
    
        Map<String, Object> claims = new HashMap<>();
        List<String> authorities = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    
        claims.put(AUTHORITIES_CLAIM_KEY, authorities);
        Token token = null;
        String tokenValue = null;
        Date issuedAt = new Date(System.currentTimeMillis());
        Date dateExpiration = new Date(issuedAt.getTime() + 30 * 60 * 1000); // 30min en moyenne
    
        do {
            tokenValue = createToken(claims, user, issuedAt, dateExpiration);
            token = tokenRepository.findById(tokenValue).orElse(null);
        } while (token != null);
    
        token = new Token(tokenValue, issuedAt, dateExpiration, user);
        tokenRepository.save(token);
    
        return token;
    }
    

     private String createToken(Map<String, Object> claims, Utilisateur user , Date issuedAt ,Date expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public void updateToken(Token token) {
        tokenRepository.save(token);
    }

}
