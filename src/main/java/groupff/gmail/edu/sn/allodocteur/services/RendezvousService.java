package groupff.gmail.edu.sn.allodocteur.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.dao.RendezvousDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RendezvousService {
    @Value("${rendezvous}")
    private String  rendezvous;

    @Value("${maxRendezVousParJour}")
    private int maxRendezVousParJour ;
   @Autowired
   private RendezvousRepository rendezvousRepository ;

   @Autowired
   private MedecinRepository medecinRepository ;

   @Autowired
   private PatientRepository patientRepository ;

   @Autowired
   private PlaningService planingService ;

   //rechercher un rv par id
    public RendezVous getRendezVous(Long rendezVousId) {
        Optional<RendezVous> rendezVousOptional = rendezvousRepository.findById(rendezVousId);
        return rendezVousOptional.orElse(null);
    }

    //liste des rv
    public List<RendezVous> getAllRendezVous() {
        return rendezvousRepository.findAll();
    }

   
   // Vérifier la disponibilité du médecin pour la date du rendez-vous
   private boolean disponibilite(Long medecinId, Date date) {
        return planingService.disponibilite(medecinId, date);
    }

    // Compter les rendez-vous existants pour un médecin à une date donnée
    private int nombreRendezVousParJour(Long medecinId, Date date) {
        return rendezvousRepository.countByMedecinIdAndDate(medecinId, date);
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

    public RendezVous modifierRendezVous(Long id , RendezvousDTO rendezVousDTO) {
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

     // Annuler un rendez-vous
     public void annulerRendezVous(Long rendezVousId) {
        // Rechercher le rendez-vous par ID
        Optional<RendezVous> rendezVousOptional = rendezvousRepository.findById(rendezVousId);

        if (rendezVousOptional.isPresent()) {
            // Récupérer le rendez-vous
            RendezVous rendezVous = rendezVousOptional.get();

            // Vérifier si le rendez-vous est confirmé avant de l'annuler
            if (rendezVous.getStatut().equals("confirmer")) {
                // Mettre à jour le statut du rendez-vous à "annuler"
                rendezVous.setStatut("annuler");
                // Enregistrer le rendez-vous mis à jour dans la base de données
                rendezvousRepository.save(rendezVous);
            } else {
                throw new RuntimeException("Le rendez-vous ne peut être annulé que s'il est confirmé.");
            }
        } else {
            throw new RuntimeException("Rendez-vous non trouvé pour l'ID : " + rendezVousId);
        }
    }  


    public RendezVous ajouterRendezVous(RendezvousDTO rendezVousDTO , Utilisateur user) {
          System.out.println("ajouterRendezVous");
            System.out.println("rendezVous = "+ rendezVousDTO);
            System.out.println("Utilisateur = "+ user);
        Optional<Medecin> medecinOptional = medecinRepository.findById(user.getId());
              System.out.println("medecin = " +medecinOptional.orElse(null));
        Optional<Patient> patientOptional = patientRepository.findById(rendezVousDTO.getIdPatient());

        System.out.println("patient = " +medecinOptional.orElse(null));
           // verifier si le medecin existe et est present et le patient est present et existe 
        if (medecinOptional.isPresent() && patientOptional.isPresent()) {
          
            Medecin medecin = medecinOptional.get();
            Patient patient = patientOptional.get();
            
            RendezVous rendezVous = new RendezVous();
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            rendezVous.setMotif(rendezVousDTO.getMotif());
            rendezVous.setDate(rendezVousDTO.getDate());
            //rendezVous.setDateCreation(rendezVousDTO.getDateCreation());
            System.out.println("rendezvous" + rendezvous );
            System.out.println("patient ="+patient);
            System.out.println("medecin ="+medecin);
            return rendezvousRepository.save(rendezVous);
        } else {
            throw new EntityNotFoundException("medecin ou patient introuvale") ;
        }
    }

    public RendezVous prendreRendezVous(RendezvousDTO rendezVousDTO, Utilisateur user) {
        // Vérifier si le patient connecté existe
            Optional<Medecin> medecinOptional = medecinRepository.findById(rendezVousDTO.getIdMedecin());
            System.out.println("medecin = " +medecinOptional.orElse(null));
            System.out.println("medecin = "+rendezVousDTO.getIdMedecin());
        Optional<Patient> patientOptional = patientRepository.findById(user.getId());

        System.out.println("patient = " +medecinOptional.orElse(null));
           // verifier si le medecin existe et est present et le patient est present et existe 
        if (medecinOptional.isPresent() && patientOptional.isPresent()) {
          
            Medecin medecin = medecinOptional.get();
            Patient patient = patientOptional.get();
    
        // Vérifier la disponibilité du créneau pour le médecin
         Date dateRendezVous = rendezVousDTO.getDate();
        if (!disponibilite(medecin.getId(), dateRendezVous)) {
            System.out.println("le medecin n'est pas disponible");
            throw new RuntimeException("Créneau non disponible pour le médecin à la date : " + dateRendezVous);
        }
        
     System.out.println("le medecin est disponible");
       // Vérifier le nombre maximum de rendez-vous pour cette date
        if (nombreRendezVousParJour(medecin.getId(), dateRendezVous) >= maxRendezVousParJour) {
            throw new RuntimeException("Nombre maximal de rendez-vous atteint pour cette date : " + dateRendezVous);
        }
       // Créer le rendez-vous
        System.out.println("pas de probleme ");
        RendezVous rendezVous = new RendezVous();
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        rendezVous.setMotif(rendezVousDTO.getMotif());
        rendezVous.setDate(dateRendezVous);
        // rendezVous.setStatut(rendezVousDTO.getStatut());
        rendezVous.setDateCreation(rendezVousDTO.getDateCreation());
    
        return rendezvousRepository.save(rendezVous);
    }
    else {
            throw new EntityNotFoundException("medecin introuvale") ;
        }
    }

}



