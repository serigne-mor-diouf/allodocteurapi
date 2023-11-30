package groupff.gmail.edu.sn.allodocteur.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.repositories.ConsultationRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PrescriptionRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;

@Service
public class MedecinService {
  @Autowired
  private MedecinRepository medecinRepository ;

  @Autowired
  private RendezvousRepository rendezvousRepository ;

  @Autowired
  private ConsultationRepository consultationRepository ;

  @Autowired
  private PrescriptionRepository prescriptionRepository ;
    // @Value("${profile2}")
    // private String  profil ;

    public Medecin inscriptionMedecin(MedecinDTO medecinDTO) {
      Medecin medecin = medecinDTO.toMedecin();
      //medecin.setProfil(profil) ;
      medecinRepository.save(medecin);
      return medecin;
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
      // medecin.setEmail(medecinDTO.getEmail());
      // medecin.setPassword(medecinDTO.getPassword());
      medecin.setSexe(medecinDTO.getSexe());
      medecin.setTelephone(medecinDTO.getTelephone());
      medecin.setSpecialite(medecinDTO.getSpecialite());
      medecinRepository.save(medecin);
    }
    else {
      throw new RuntimeException("Le médecin avec l'ID " + medecinDTO.getId() + " n'a pas été trouvé.");
    }
  }

  //  pour récupérer les informations spécifiques au médecin apres avoir connecter

// Récupérer tous les rendez-vous pour un médecin spécifique
public List<RendezVous> getRendezVousByMedecin(Long medecinId) {
    return rendezvousRepository.findByMedecinId(medecinId);
}

// Récupérer toutes les consultations pour un médecin spécifique
public List<Consultation> getConsultationsByMedecin(Long medecinId) {
    return consultationRepository.findByMedecinId(medecinId);
}

// Récupérer toutes les prescriptions pour un médecin spécifique
public List<Prescription> getPrescriptionsByMedecin(Long medecinId) {
    return prescriptionRepository.findByMedecinId(medecinId);
}

}
