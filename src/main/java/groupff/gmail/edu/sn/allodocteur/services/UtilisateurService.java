package groupff.gmail.edu.sn.allodocteur.services;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.UtilisateurRepository;


@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
  
    //liter les utilisateurs
    public List<Utilisateur>  getUtilisateurs(){
       return utilisateurRepository.findAll() ;
    }
}


