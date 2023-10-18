package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, Long> {

}
