package groupff.gmail.edu.sn.allodocteur.services;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createPlaning(Medecin medecin, String jour, Date date, Time heureDebut, Time heureFin) {
        // Créez un objet Planing avec les informations
        Planing planing = new Planing();
        planing.setMedecin(medecin);
        planing.setJour(jour);
        planing.setDate(date);
        planing.setHeuredebut(heureDebut);
        planing.setHeureFin(heureFin);  
        // Enregistrez le planning dans la base de données
        planingRepository.save(planing);
    }

    // Mettez à jour une plage horaire existante
    public Planing updatePlaning(Planing planing) {
        // Vérifions d'abord si la plage horaire existe dans la base de données
        if (planingRepository.existsById(planing.getId())) {
            // Si la plage horaire existe, utilisez la méthode save pour la mettre à jour
            return planingRepository.save(planing);
        } else {
            // Gérez le cas où la plage horaire n'existe pas
            throw new RuntimeException("La plage horaire avec l'ID " + planing.getId() + " n'existe pas.");
        }
    }

    
    // Supprimez une plage horaire du planning par ID
    public void deletePlage(Long id) {
        // Vérifiez d'abord si la plage horaire existe dans la base de données
        if (planingRepository.existsById(id)) {
            // Si la plage horaire existe, utilisez la méthode deleteById pour la supprimer
            planingRepository.deleteById(id);
        } else {
            // Gérez le cas où la plage horaire n'existe pas
            throw new RuntimeException("La planning horaire avec l'ID " + id + " n'existe pas.");
        }
    }

    //rechercher les plannigs disponible  d'un medecin en function de son nom
    public List<Planing> rechercherDisponiblesMedecin(String nomMedecin, String prenomMedecin, LocalDate date) {
       // Recherchez le médecin en fonction de son nom
        Medecin medecin = medecinRepository.findByNomAndPrenom(nomMedecin , prenomMedecin);

        if (medecin == null) {
            // Gérer le cas où le médecin n'a pas été trouvé
            return new ArrayList<>(); // Ou renvoyer une erreur
        }
        // Récupérons la liste des plannings pour le médecin et la date donnée
            List<Planing> planings = planingRepository.findByMedecinAndDate(medecin, date);
        // Filtrer les plannings pour ne récupérer que ceux avec disponibilité (disponibilite = 1)
        return planings.stream()
            .filter(planing -> planing.getDisponibilite() == 1)
            .collect(Collectors.toList());
        }

}
