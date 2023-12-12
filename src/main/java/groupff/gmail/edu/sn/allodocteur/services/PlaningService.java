package groupff.gmail.edu.sn.allodocteur.services;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.dao.PlanningDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Planing;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PlaningRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PlaningService {
    @Autowired
    private PlaningRepository  planingRepository  ;

    @Autowired
    private MedecinRepository medecinRepository ;

    // lister les planings
    public List<Planing> getPlanings(){
        return planingRepository.findAll();
    }

   // Vérifier la disponibilité pour un rendez-vous
   public boolean isSlotAvailable(Long medecinId, Date date) {
    // Rechercher le médecin par ID
    Optional<Medecin> medecinOptional = medecinRepository.findById(medecinId);

    if (medecinOptional.isPresent()) {
        // Récupérer le médecin
        Medecin medecin = medecinOptional.get();

        // Rechercher le planning du médecin pour la date donnée
        Optional<Planing> planingOptional = planingRepository.findByMedecinAndDate(medecin, date);

        // Si le planning n'existe pas ou que le créneau est disponible, retourner vrai
        return planingOptional.isEmpty() || planingOptional.get().isSlotAvailable();
    } else {
        throw new RuntimeException("Médecin non trouvé pour l'ID : " + medecinId);
    }
}

    // definir son planing
    public Planing createPlaning(PlanningDTO planingDTO , Utilisateur user) {
        //rechercher un médecin par son ID
        Optional<Medecin> medecinOptional = medecinRepository.findById(user.getId());
        if (medecinOptional.isPresent()) {
            //recuperer un médecin par son ID
            Medecin medecin = medecinOptional.get(); 
            Planing planing = new Planing();
            planing.setMedecin(medecin);
            planing.setDate(planingDTO.getDate());   
            return planingRepository.save(planing);
        } else {
            throw new EntityNotFoundException(" Entity Médecin non trouvé : ");
        }
    }
    
    // Mettez à jour une plage horaire existante
    public Planing updatePlanning(Long id, PlanningDTO planningDTO) {
        // Vérifiez si le planning avec l'ID donné existe dans la base de données
        Optional<Planing> existPlanning = planingRepository.findById(id);
        if(existPlanning.isPresent()){
        Planing planning = existPlanning.get() ;
            planning.setDate(planningDTO.getDate()) ;
            planning.setDisponibilite(planningDTO.getDisponibilite()) ;
            // Enregistrez le planning mis à jour dans la base de données
            return planingRepository.save(planning);
        } else {
            throw new RuntimeException("Le planning avec le medecin" + id + " n'existe pas.");
        }
    }
    
    // Supprimez une plage horaire du planning par ID
    public void deletePlage(Long id) {
        // si existe dans la base de données
        if (planingRepository.existsById(id)) {
            planingRepository.deleteById(id);
        } else {
            throw new RuntimeException("La planning horaire avec l'ID " + id + " n'existe pas.");
        }
    }
}
