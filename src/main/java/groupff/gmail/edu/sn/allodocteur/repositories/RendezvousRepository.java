package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RendezvousRepository extends JpaRepository<RendezVous , Long> {
    List<RendezVous> findByPatientNom(String nom);

    @Query("SELECT r FROM RendezVous r WHERE r.statut = 'confirmer'")
    List<RendezVous> findRendezVousConfirmer();

    List<RendezVous> findByMedecinId(Long medecinId);
    List<RendezVous> findByDateBetween(LocalDateTime start, LocalDateTime end);
}