package groupff.gmail.edu.sn.allodocteur.dao;

import groupff.gmail.edu.sn.allodocteur.entites.Patient;


public class PatientDTO extends UserRegistrationDTO {

    private Long id ;

    
    public PatientDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // creeer un patient
    public Patient toPatient(){
        Patient patient = new  Patient() ;
        //appelle de la methode update de UserRegistrationDTO
        updateData(patient);
        // a faire  creation rendezvous avec le motif
        return patient ;
    }

    public String tosString(){
        return "{" + super.toString()+"}" ;
    }
}
