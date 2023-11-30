package groupff.gmail.edu.sn.allodocteur.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.RendezvousDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;

@Service
public class RendezvousService {
    @Value("${rendezvous}")
    private String  rendezvous;

   @Autowired
   private RendezvousRepository rendezvousRepository ;

   @Autowired
   private MedecinRepository medecinRepository ;

   @Autowired
   private PatientRepository patientRepository ;

   //rechercher un rv par id
   public RendezVous getRendezVous(Long rendezVousId) {
      Optional<RendezVous> rendezVousOptional = rendezvousRepository.findById(rendezVousId);
      return rendezVousOptional.orElse(null);
  }

//liste des rv
  public List<RendezVous> getAllRendezVous() {
      return rendezvousRepository.findAll();
  }

  // Creer un rendez-vous pour le patient
  public RendezVous planifierRendezVous(RendezvousDTO rendezVousDTO) {
    Optional<Medecin> medecinOptional = medecinRepository.findById(rendezVousDTO.getMedecin().getId());
    Optional<Patient> patientOptional = patientRepository.findById(rendezVousDTO.getPatient().getId());

    // vérifier si le medecin et le patient existent
    if (medecinOptional.isPresent() && patientOptional.isPresent()) {
        Medecin medecin = medecinOptional.get();
        Patient patient = patientOptional.get();

        RendezVous rendezVous = new RendezVous();
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        rendezVous.setMotif(rendezVousDTO.getMotif());
        rendezVous.setDate(rendezVousDTO.getDate());
        rendezVous.setStatut(rendezVousDTO.getStatut());
        rendezVous.setDateCreation(rendezVousDTO.getDateCreation());

        return rendezvousRepository.save(rendezVous);
    } else {
        throw new RuntimeException("Médecin ou patient non trouvé pour les ID : " + rendezVousDTO.getMedecin().getId() + ", " + rendezVousDTO.getPatient().getId());
    }
}

    
   // Supprimez le rendez-vous
    public void supprimerRendezVous(Long id) {
      // Vérifiez si le rendez-vous n'existe pas 
      if (!rendezvousRepository.existsById(id)) {
         throw new RuntimeException("Le rendez-vous n'existe pas.");
      }
      rendezvousRepository.deleteById(id);
  }

   // Recherchez les rendez-vous en fonction du nom patient
   public List<RendezVous> rechercherRendezVousParPatient(String nom) {  
      return rendezvousRepository.findByPatientNom(nom);
   }

   public RendezVous modifierRendezVous( Long id , RendezvousDTO rendezVousDTO) {
    // Recherchez le rendez-vous par ID
    Optional<RendezVous> existRendezVousOptional = rendezvousRepository.findById(id);
        if (existRendezVousOptional.isPresent()) {
            RendezVous  updatedRendezVous = existRendezVousOptional.get() ;
                updatedRendezVous.setDate(rendezVousDTO.getDate());
                updatedRendezVous.setDateCreation(rendezVousDTO.getDateCreation()) ;
                updatedRendezVous.setStatut(rendezVousDTO.getStatut()) ;
                updatedRendezVous.setMotif(rendezVousDTO.getMotif()) ;
             // Enregistrez le rendez-vous mis à jour
        return  rendezvousRepository.save(updatedRendezVous);
    } else {
        throw new RuntimeException("Le rendez-vous n'existe pas pour id."+id);
    }
}


   //lister  les rv confirmer
   public List<RendezVous> getRendezVousConfirmer() {
      return rendezvousRepository.findRendezVousConfirmer();
   }
   
}



