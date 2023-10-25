package groupff.gmail.edu.sn.allodocteur.services;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;

@Service
public class RendezvousService {
   @Autowired
   private RendezvousRepository rendezvousRepository ;
   //lister les rendez_vous
   public List<RendezVous> getRendezVous(){
      return rendezvousRepository.findAll() ;
   }

   //planifier un rv a un patient 
   public RendezVous planifierRendezVous(Medecin medecin, Patient patient, Date date, Time heure, String statut) {
      // Créez un objet RendezVous avec les informations fournies
      RendezVous rendezVous = new RendezVous();
      rendezVous.setMedecin(medecin);
      rendezVous.setPatient(patient);
      rendezVous.setDate(date);
      rendezVous.setHeure(heure);
      rendezVous.setStatut(statut);
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
    public RendezVous modifierRendezVous(RendezVous rendezVous) {
        // Vérifiez si le rendez-vous existe
        RendezVous existingRendezVous = rendezvousRepository.findById(rendezVous.getId()).orElse(null);
        if (existingRendezVous == null) {
            throw new RuntimeException("Le rendez-vous n'existe pas.");
        }
        RendezVous updatedRendezVous = rendezvousRepository.save(rendezVous);
        return updatedRendezVous;
    }


   //lister  les rv confirmer
   public List<RendezVous> getRendezVousConfirmer() {
      return rendezvousRepository.findRendezVousConfirmer();
   }
   
}



