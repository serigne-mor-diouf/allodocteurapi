package groupff.gmail.edu.sn.allodocteur.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
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
   
   public List<RendezVous> getRendezVous(){
      System.out.println("liste des rv");
      return rendezvousRepository.findAll() ;
   }

  public RendezVous planifierRendezVous(MedecinDTO medecinDTO, PatientDTO patientDTO, Date date, String motif) {
    // Recherchez les objets Medecin et Patient correspondant à partir de leurs DTO
    Optional<Medecin> medecin = medecinRepository.findById(medecinDTO.getId());
    Optional<Patient> patient = patientRepository.findById(patientDTO.getId());

    RendezVous rendezVous = new RendezVous();
    rendezVous.setMedecin(medecin.get());
    rendezVous.setPatient(patient.get());
    rendezVous.setMotif(motif);
    rendezVous.setDate(date);
   // rendezVous.setStatut("planifié"); // Assurez-vous que cela correspond à la logique de votre application
    // Enregistrez le rendez-vous dans la base de données
    return rendezvousRepository.save(rendezVous);
}


    
   // Supprimez le rendez-vous
    public void supprimerRendezVous(Long id) {
      // Vérifiez si le rendez-vous existe
      if (!rendezvousRepository.existsById(id)) {
         // Gérez l'erreur, par exemple, en lançant une exception
         throw new RuntimeException("Le rendez-vous n'existe pas.");
      }
      // Supprimez le rendez-vous de la base de données
      rendezvousRepository.deleteById(id);
  }

   // Recherchez les rendez-vous en fonction du nom et du prénom du patient
   public List<RendezVous> rechercherRendezVousParPatient(String nom, String prenom) {  
      return rendezvousRepository.findByPatientNomAndPatientPrenom(nom, prenom);
   }

   //mettre  a jour un rv
    public RendezVous modifierRendezVous(RendezvousDTO rendezVous) {
        // Vérifiez si le rendez-vous existe
        RendezVous existingRendezVous = rendezvousRepository.findById(rendezVous.getId()).orElse(null);
        if (existingRendezVous == null) {
            throw new RuntimeException("Le rendez-vous n'existe pas.");
        }
        RendezVous updatedRendezVous = rendezvousRepository.save(existingRendezVous);
        return updatedRendezVous;
    }


   //lister  les rv confirmer
   public List<RendezVous> getRendezVousConfirmer() {
      return rendezvousRepository.findRendezVousConfirmer();
   }
   
}



