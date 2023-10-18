package groupff.gmail.edu.sn.allodocteur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;

@Service
public class MedecinService {
    @Autowired
   private MedecinRepository medecinRepository ;

    // creation de compte medecin
   public Medecin savMedecin(MedecinDTO medecinDTO){
     return medecinRepository.save(medecinDTO.toMedecin()) ;
   }

}
