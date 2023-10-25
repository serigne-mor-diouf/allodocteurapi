package groupff.gmail.edu.sn.allodocteur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.AdminDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Admin;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.repositories.AdminRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository ;
    
    @Autowired
    private MedecinRepository medecinRepository ;

    public Admin saveAdmin(AdminDTO adminDTO){

        return adminRepository.save(adminDTO.toAdmin());
    }

    
    //modifier un medecin
    public void  editMedecin(Medecin medecin){
        if(medecin == null||medecin.getId() == null ){
        throw new RuntimeException("le medecin doit avoir un identifiant");
        }
        medecinRepository.save(medecin) ;
    }

    
    //supprimer un medecin 
    public void deleteMedecin(Long id){
        medecinRepository.deleteById(id) ;
    }
    
}
