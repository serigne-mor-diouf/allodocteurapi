package groupff.gmail.edu.sn.allodocteur.services;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
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
    public Planing createPlaning(MedecinDTO medecin , Date date) {
        Planing planing = new Planing();
        //rechercheer l'ide dans la base de donnee si existe
        Optional<Medecin> medecin2 = medecinRepository.findById(medecin.getId());
        // on le recupere
        planing.setMedecin(medecin2.get());
        planing.setDate(date); 
       return  planingRepository.save(planing);
    }

    // Mettez à jour une plage horaire existante
    public Planing updatePlaning(Planing planing) {
        if (planingRepository.existsById(planing.getId())) {
            return planingRepository.save(planing);
        } else {
            throw new RuntimeException("La plage horaire avec l'ID " + planing.getId() + " n'existe pas.");
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
