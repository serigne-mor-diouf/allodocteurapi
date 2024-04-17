package groupff.gmail.edu.sn.allodocteur.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import groupff.gmail.edu.sn.allodocteur.dao.AuthenticationDTO;
import groupff.gmail.edu.sn.allodocteur.dao.AuthenticationResponse;
import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Token;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;
import groupff.gmail.edu.sn.allodocteur.services.TokenService;
import groupff.gmail.edu.sn.allodocteur.services.UtilisateurService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    // @Autowired
    // private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  UtilisateurService utilisateurService ; 

    @Autowired
    private TokenService tokenService ;

    @Autowired
    private PatientService patientService ;

    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationDTO.getEmail(),
             authenticationDTO.getPassword()));
            System.out.println("Connection réussie");
        } catch (BadCredentialsException e) {
            System.out.println("Connection échouée - Incorrect username or password");
            throw new BadCredentialsException("Incorrect username or password!");
        } 

        // Récupérez le compte de l'utilisateur
        Utilisateur user = utilisateurService.findByEmail(authenticationDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur not found", null);
        }

        // Récupérez le profil de l'utilisateur
        final String token = tokenService.generateToken(user).getValeur();
        System.out.println("token = "+token);
        // return new AuthenticationResponse(jwt);
        return ResponseEntity.ok(new AuthenticationResponse(token)) ;
    }


    @GetMapping("/logout/{tokenValue}")
    public ResponseEntity<Object> logOut( @PathVariable String tokenValue){
        if (tokenService.isTokenUtilisateurValide(tokenValue)) {
            Token token =  tokenService.findToken(tokenValue);
            token.setRevoquer(true);
            token.setDeconnection(new Date());
            tokenService.updateToken(token);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

  

    //si on enregistre un patient on lui donne son id avec responsEntity
    @PostMapping("/inscriptionPatient")
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO) {

        // Appelez la méthode inscriptionPatient avec l'ID du médecin
        Patient patient = patientService.inscriptionPatient(patientDTO);

        if (patient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(patient.getId());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
