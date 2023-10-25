package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RendezvousRepository extends JpaRepository<RendezVous , Long> {
    List<RendezVous> findByPatientNomAndPatientPrenom(String nom, String prenom);

    @Query("SELECT r FROM RendezVous r WHERE r.statut = 'confirmer'")
    List<RendezVous> findRendezVousConfirmer();
}
