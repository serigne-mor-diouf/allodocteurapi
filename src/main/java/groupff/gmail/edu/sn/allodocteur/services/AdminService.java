package groupff.gmail.edu.sn.allodocteur.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.repositories.AdminRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    // public Admin saveAdmin(AdminDTO adminDTO) {
    //     return adminRepository.save(adminDTO.toAdmin());
    // }

    // Modifier un médecin
    public void editMedecin(Medecin medecin) {
        if (medecin == null || medecin.getId() == null) {
            throw new RuntimeException("Le médecin doit avoir un identifiant.");
        }
        medecinRepository.save(medecin);
    }

    // Supprimer un médecin
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }

   


}
