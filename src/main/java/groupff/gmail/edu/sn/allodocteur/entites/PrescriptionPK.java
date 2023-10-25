package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.Embeddable;

import jakarta.persistence.ManyToOne;

@Embeddable
public class PrescriptionPK {
    @ManyToOne
    private Medecin medecin;
    
    @ManyToOne
    private Patient patient;

    public PrescriptionPK(){

    }

    public PrescriptionPK(Medecin medecin, Patient patient) {
        this.medecin = medecin;
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    
}
