package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RendezvousRepository extends JpaRepository<RendezVous , Long> {
    List<RendezVous> findByPatientNom(String nom);

    @Query("SELECT r FROM RendezVous r WHERE r.statut = 'confirmer'")
    List<RendezVous> findRendezVousConfirmer();

    List<RendezVous> findByMedecinId(Long medecinId);
    List<RendezVous> findByDateBetween(LocalDateTime start, LocalDateTime end);

    // Compter les rendez-vous par médecin et date
    @Query("SELECT COUNT(r) FROM RendezVous r WHERE r.medecin.id = :medecin_id AND r.date = :date")
    int countByMedecinIdAndDate(@Param("medecin_id") Long medecin_id, @Param("date") Date date);

    List<RendezVous> findByPatientId(Long patientId);


}