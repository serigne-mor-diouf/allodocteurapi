package groupff.gmail.edu.sn.allodocteur.dao;

import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class PatientDTO extends UserRegistrationDTO {
    @NotEmpty
    @NotNull
    @Size()
    private  String  motif ;
    
    public PatientDTO() {
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
  
    // creeer un patient
    public Patient toPatient(){
        Patient patient = new  Patient() ;
        //
        //appelle de la methode update de UserRegistrationDTO
        updateData(patient);
        // a faire  creation rendezvous avec le motif
        return patient ;
    }

    public String tosString(){
        return "{ motif  = "+ motif + "" + super.toString()+"}" ;
    }
}
