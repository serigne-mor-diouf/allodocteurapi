package groupff.gmail.edu.sn.allodocteur.jwt;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository userRepository;

   
    private final JwtUserDetails jwtUserDetails;

    @Autowired
    public UserDetailsServiceImpl(JwtUserDetails jwtUserDetails) {
        this.jwtUserDetails = jwtUserDetails;
    }
   
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByEmail(email) ;
            if(user == null){
                throw new UsernameNotFoundException("user not found "+email) ;
            }
        // Assuming JwtUserDetails is your custom UserDetails implementation
        return JwtUserDetails.build(user);
    }
}
