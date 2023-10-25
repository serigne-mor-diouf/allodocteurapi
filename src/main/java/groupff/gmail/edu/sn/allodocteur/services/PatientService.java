package groupff.gmail.edu.sn.allodocteur.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository ;

    @Autowired
    private MedecinRepository medecinRepository ;

    public Patient savePatient(PatientDTO patientDTO) {

        return patientRepository.save(patientDTO.toPatient());
    }

    // lister les patients
    public List<Patient> getPatients(){
        return patientRepository.findAll() ;
    }

    // lister les medecins
    public List<Medecin> getMedecins(){
        return medecinRepository.findAll() ;
    }

    //rechercher un medecin dont on connaait son identifiant
    public Medecin fingMedecin(Long id){
        Optional<Medecin> medecinOptional = medecinRepository.findById(id) ;
        return medecinOptional.orElse(null);
    }

    //rechercher les médecins qui ont une spécialité spécifique
    public List<Medecin> findMedecinsBySpecialite(String specialite) {
        List<Medecin> medecins = medecinRepository.findBySpecialite(specialite);
        return medecins;
    }

    //gestions des exceptions 
    public class PatientRegistrationException extends RuntimeException {
        public PatientRegistrationException(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return "PatientRegistrationException []";
        }
    }
}


