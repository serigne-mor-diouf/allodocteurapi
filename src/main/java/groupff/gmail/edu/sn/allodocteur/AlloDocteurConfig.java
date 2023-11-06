package groupff.gmail.edu.sn.allodocteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import groupff.gmail.edu.sn.allodocteur.entites.Admin;
import groupff.gmail.edu.sn.allodocteur.repositories.AdminRepository;

@Component
public class AlloDocteurConfig implements CommandLineRunner  {
    @Autowired
    AdminRepository adminRepository ;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Demarrage de l'application");
        if(adminRepository.count()==0){
            System.out.println("Pas d'administatreur dans le systeme");
            System.out.println("Creation de l'utilisateur administration");
            Admin admin  = new Admin() ;
            String hash = AllodocteurHashPassword.genSHAS512("password");
            admin.setNom("Diouf");
            admin.setEmail("dioufserigne@gmail.com");
            admin.setAdresse("Thies");
            admin.setAge(22);
            admin.setPrenom("Serigne Mor");
            admin.setProfil("admin");
            admin.setSexe("M");
            admin.setTelephone("778653628");
            admin.setPassword(hash);           
            adminRepository.save(admin);
            System.out.println("Enregistrement de l'admin " + admin);
        }
        
    }
}
