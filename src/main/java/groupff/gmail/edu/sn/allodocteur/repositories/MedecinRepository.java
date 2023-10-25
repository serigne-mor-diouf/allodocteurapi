package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository  extends JpaRepository<Medecin , Long> {
    List<Medecin> findBySpecialite(String specialite);

    Medecin findByNomAndPrenom(String nom, String prenom);

}
