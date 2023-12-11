package groupff.gmail.edu.sn.allodocteur.jwt;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;

@Component
public class JwtUserDetails implements UserDetails {
    private String email;
    private String password;
    private Long userId; // Nouvel attribut pour stocker l'ID du médecin (ou du patient)
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails() {
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        
        return true ;    
    }
   
    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }
    @Override
    public boolean isEnabled() {
        
        return true ;
    }

    public static JwtUserDetails build(Utilisateur user) {
    JwtUserDetails userDetails = new JwtUserDetails();
    userDetails.setUserId(user.getId()); // Définir l'ID de l'utilisateur (médecin ou patient)
    userDetails.setEmail(user.getEmail());
    userDetails.setPassword(user.getPassword());
    userDetails.setAuthorities(user.getAuthorities().stream()
            .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .collect(Collectors.toList()));

    if (user instanceof Medecin) {
        userDetails.setUserId(((Medecin) user).getId());
    }else if(user instanceof Patient){
       userDetails.setUserId(((Patient) user).getId()); 
    }
    else{
        System.out.println("instance not found");
    }


    return userDetails;
}


    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonLocked() {
      
        return true ;    
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    } 

    
}
