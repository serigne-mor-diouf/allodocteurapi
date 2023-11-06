package groupff.gmail.edu.sn.allodocteur.dao;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedecinDTO extends UserRegistrationDTO{

    private Long id ;

    @NotEmpty
    @NotNull
    @Size()
    private  String specialite ;

   public MedecinDTO(){

    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
  
    //creer un medecin
    public Medecin toMedecin(){
        Medecin medecin = new Medecin();
        //appelle de la methode update de UserRegistrationDTO
        medecin.setSpecialite(this.specialite) ;
        updateData(medecin) ;
        return medecin ;
    }

    @Override
    public String toString() {
        return "{ specialite  = "+ specialite + "" + super.toString()+"}";
    }
    
}
