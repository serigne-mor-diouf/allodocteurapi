package groupff.gmail.edu.sn.allodocteur.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;

@Service
public class MedecinService {
  @Autowired
  private MedecinRepository medecinRepository ;

    @Value("${profile2}")
    private String  profil ;

    public Medecin savMedecin(MedecinDTO medecinDTO) {
      Medecin medecin = medecinDTO.toMedecin();
      // Définir le profil en "medecin" par défaut
      medecin.setProfil(profil);
      return medecinRepository.save(medecin);
  }
   //supprimer un medecin 
  public void  delete(Long id){
    medecinRepository.deleteById(id);
  }

  //modifier un medecin
  public void updateMedecin(MedecinDTO medecinDTO) {
    // Vérifiez si le médecin existe
    if (medecinRepository.existsById(medecinDTO.getId())) {
      Medecin medecin = medecinRepository.findById(medecinDTO.getId()).get();       
      // à jour les propriétés 
      medecin.setNom(medecinDTO.getNom());
      medecin.setPrenom(medecinDTO.getPrenom());
      medecin.setAge(medecinDTO.getAge());
      medecin.setAdresse(medecinDTO.getAdresse());
      medecin.setEmail(medecinDTO.getEmail());
      medecin.setPassword(medecinDTO.getPassword());
      medecin.setSexe(medecinDTO.getSexe());
      medecin.setTelephone(medecinDTO.getTelephone());
      medecin.setSpecialite(medecinDTO.getSpecialite());
      medecinRepository.save(medecin);
    }
    else {
      throw new RuntimeException("Le médecin avec l'ID " + medecinDTO.getId() + " n'a pas été trouvé.");
    }
  }
}
