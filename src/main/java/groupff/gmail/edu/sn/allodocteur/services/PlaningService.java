package groupff.gmail.edu.sn.allodocteur.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.dao.PlanningDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Planing;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PlaningRepository;

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

    // definir son planing
    public Planing createPlaning(PlanningDTO planingDTO) {
        //rechercher un médecin par son ID
        Optional<Medecin> medecinOptional = medecinRepository.findById(planingDTO.getMedecin().getId());
    
        if (medecinOptional.isPresent()) {
            //recuperer un médecin par son ID
            Medecin medecin = medecinOptional.get(); 
            Planing planing = new Planing();
            planing.setMedecin(medecin);
            planing.setDate(planingDTO.getDate());   
            return planingRepository.save(planing);
        } else {
            throw new RuntimeException("Médecin non trouvé pour l'ID : " + (planingDTO.getMedecin().getId()));
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
