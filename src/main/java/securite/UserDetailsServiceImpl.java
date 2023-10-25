// package securite ;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
// import groupff.gmail.edu.sn.allodocteur.repositories.UtilisateurRepository;

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {
//     @Autowired
//     private final UtilisateurRepository utilisateurRepository;

//     public UserDetailsServiceImpl(UtilisateurRepository utilisateurRepository) {
//         this.utilisateurRepository = utilisateurRepository;
//     }

    
// @Override
// public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//     Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
//     if (utilisateur == null) {
//         throw new UsernameNotFoundException("Utilisateur non trouvé pour l'email : " + email);
//     }

//     // Créez et retournez un objet UserDetails personnalisé ici
//     return new CustomUserDetails(
//         utilisateur.getEmail(), 
//         utilisateur.getPassword(), 
//         getAuthorities(utilisateur.getProfil())
//     );
// }


// private List<? extends GrantedAuthority> getAuthorities(String profil) {    //  des rôles en fonction du profil de l'utilisateur.
//     // Par exemple, si l'utilisateur a le profil "admin", attribuez-lui le rôle "ADMIN".

//     List<GrantedAuthority> authorities = new ArrayList<>();
    
//     // Ajoutons les rôles ici en fonction du profil
//     if ("admin".equals(profil)) {
//         authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//     } else if ("medecin".equals(profil)) {
//         authorities.add(new SimpleGrantedAuthority("ROLE_MEDECIN"));
//     } else if ("patient".equals(profil)) {
//         authorities.add(new SimpleGrantedAuthority("ROLE_PATIENT"));
//     }

//     return authorities;
// }

// }
