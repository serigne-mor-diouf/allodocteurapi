package groupff.gmail.edu.sn.allodocteur.services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;


@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
  
     @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;
    //liter les utilisateurs
    public List<Utilisateur>  getUtilisateurs(){
       return utilisateurRepository.findAll() ;
    }

    public Utilisateur findByEmail(String email){
        return utilisateurRepository.findByEmail(email) ;
    }

    // les patient et medecin
    public List<Utilisateur> getPatientsAndMedecins() {
    List<Utilisateur> patientsAndMedecins = new ArrayList<>();

    // Récupérer tous les patients
    List<Patient> patients = patientRepository.findAll();
    patientsAndMedecins.addAll(patients);

    // Récupérer tous les médecins
    List<Medecin> medecins = medecinRepository.findAll();
    patientsAndMedecins.addAll(medecins);

    return patientsAndMedecins;
    }


    public void verrouillerUtilisateur(Long utilisateurId) {
        mettreAJourStatutUtilisateur(utilisateurId, false);
    }

    public void deverrouillerUtilisateur(Long utilisateurId) {
        mettreAJourStatutUtilisateur(utilisateurId, true);
    }

    private void mettreAJourStatutUtilisateur(Long utilisateurId, boolean enabled) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(utilisateurId);

        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            utilisateur.setEnabled(enabled);
            utilisateurRepository.save(utilisateur);
        } else {
            throw new RuntimeException("Utilisateur non trouvé pour l'ID : " + utilisateurId);
        }
    }

}


