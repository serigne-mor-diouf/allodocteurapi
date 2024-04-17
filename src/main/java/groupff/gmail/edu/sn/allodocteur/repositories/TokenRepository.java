package groupff.gmail.edu.sn.allodocteur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import groupff.gmail.edu.sn.allodocteur.entites.Token;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;

public interface TokenRepository  extends JpaRepository<Token , String>{
    Token findByUtilisateur(Utilisateur utilisateur) ;

    void deleteAllByrevoquer(boolean revoquer);
}
