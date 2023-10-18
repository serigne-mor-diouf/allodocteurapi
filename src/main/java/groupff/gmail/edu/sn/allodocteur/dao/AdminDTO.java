package groupff.gmail.edu.sn.allodocteur.dao;

import groupff.gmail.edu.sn.allodocteur.entites.Admin;

public class AdminDTO  extends UserRegistrationDTO{
    
    public Admin toAdmin(){
        Admin admin = new Admin();
        //appelle de la methode update de UserRegistrationDTO
        updateData(admin);
        return admin ;
    }
}
