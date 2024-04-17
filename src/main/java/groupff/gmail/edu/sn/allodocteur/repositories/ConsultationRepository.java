package groupff.gmail.edu.sn.allodocteur.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;

public interface ConsultationRepository  extends JpaRepository<Consultation, Long>{
        Optional<List<Consultation>> findAllByMedecinAndPatient(Medecin medecin, Patient patient);
        List<Consultation> findByMedecinId(Long medecinId);
}
