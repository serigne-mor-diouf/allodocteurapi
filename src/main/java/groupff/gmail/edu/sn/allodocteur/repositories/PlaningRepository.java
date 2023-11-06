package groupff.gmail.edu.sn.allodocteur.repositories;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Planing;

public interface PlaningRepository  extends JpaRepository<Planing , Long>{  
    List<Medecin> findByMedecinAndDate(Medecin medecin, Date date);
}
