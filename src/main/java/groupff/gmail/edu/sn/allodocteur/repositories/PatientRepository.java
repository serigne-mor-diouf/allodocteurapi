package groupff.gmail.edu.sn.allodocteur.repositories;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long> {
    Optional<Patient> findByEmail(String email);
}
