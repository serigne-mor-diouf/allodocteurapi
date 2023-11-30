package groupff.gmail.edu.sn.allodocteur.repositories;
import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
        Optional<List<Consultation>> findAllByMedecinAndPatient(Medecin medecin, Patient patient);
        List<Prescription> findByMedecinId(Long medecinId);
}
