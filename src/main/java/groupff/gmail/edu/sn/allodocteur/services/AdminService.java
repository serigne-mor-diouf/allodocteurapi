package groupff.gmail.edu.sn.allodocteur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.AdminDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Admin;
import groupff.gmail.edu.sn.allodocteur.repositories.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository ;
    
    public Admin saveAdmin(AdminDTO adminDTO){

        return adminRepository.save(adminDTO.toAdmin());
    }
    
}
