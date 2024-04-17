// package groupff.gmail.edu.sn.allodocteur.jwt;
// import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
// import groupff.gmail.edu.sn.allodocteur.repositories.UtilisateurRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     private UtilisateurRepository userRepository;

//     private final JwtUserDetails jwtUserDetails;

//     @Autowired
//     public UserDetailsServiceImpl(JwtUserDetails jwtUserDetails) {
//         this.jwtUserDetails = jwtUserDetails;
//     }
   
//    @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Utilisateur user = userRepository.findByEmail(email);
//         if (user == null) {
//             throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
//         }
//         return JwtUserDetails.build(user);
//     }

//      public Long getConnectedUserId() {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
//             Long userId = ((JwtUserDetails) authentication.getPrincipal()).getUserId();
//             System.out.println("Utilisateur connecté avec l'ID : " + userId);
//             return userId;
//         }
//         return null;
//     }
// }
