package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository  extends JpaRepository<Medecin , Long> {
    List<Medecin> findBySpecialite(String specialite);
    List<Medecin> findByNomAndPrenom(String nom, String prenom);
    Optional<Medecin> findByEmail(String email);


}
