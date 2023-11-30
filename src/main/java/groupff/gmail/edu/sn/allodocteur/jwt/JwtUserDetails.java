package groupff.gmail.edu.sn.allodocteur.jwt;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
@Component
public class JwtUserDetails implements UserDetails {
    private String email;
    private String password;
    private String profiles;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails() {
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
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
        userDetails.setEmail(user.getEmail());
        userDetails.setPassword(user.getPassword());
       // userDetails.setProfiles(compte.getProfile());  // Assuming getProfile() returns a String

        // Other fields...

        return userDetails;
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
      
        return true ;    }
    
}
