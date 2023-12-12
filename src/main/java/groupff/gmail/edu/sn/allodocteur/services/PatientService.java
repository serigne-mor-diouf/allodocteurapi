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
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository ;
    //enregistrer le profil patient par defaut
    // @Value("${profile1}")
    // private String profil;


    public Patient inscriptionPatient(PatientDTO patientDTO) {   
        Patient patient = patientDTO.toPatient();
         //patient.setProfil(profil) ;
        patientRepository.save(patient);
        
        return patient;
    }


    // lister les patients
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    // lister les medecins
    public List<Medecin> getMedecins() {
        return medecinRepository.findAll();
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

        //rechercher un medecin par son nom et prenom
        public  List<Medecin> findByNomAndPrenom(String nom , String prenom){
        List<Medecin> medecins = medecinRepository.findByNomAndPrenom(nom, prenom) ;
        return medecins ;
    }
}


