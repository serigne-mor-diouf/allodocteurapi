// package securite ;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;


// import java.util.Collection;
// import java.util.Collections;
// import java.util.List;

// public class CustomUserDetails implements UserDetails {

//     private String email; // Peut être l'email si vous utilisez l'email comme identifiant
//     private String password;
//     private Collection<? extends GrantedAuthority> authorities;

//      public CustomUserDetails() {
  

//     }

//     public CustomUserDetails(String email, String password, List<? extends GrantedAuthority> list) {
//         this.email = email;
//         this.password = password;
//         // Vous pouvez définir les autorisations ici, par exemple, en tant qu'utilisateur (USER) par défaut.
//         this.authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return authorities;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     // D'autres méthodes UserDetails...

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

   

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//         this.authorities = authorities;
//     }

//     @Override
//     public String getUsername() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
//     }

   
// }
