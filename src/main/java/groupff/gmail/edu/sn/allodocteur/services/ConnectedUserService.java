package groupff.gmail.edu.sn.allodocteur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.jwt.UserDetailsServiceImpl;

@Service
public class ConnectedUserService {
     @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // public Long getConnectedUserId() {
    //     return userDetailsService.getConnectedUserId();
    // }
}
