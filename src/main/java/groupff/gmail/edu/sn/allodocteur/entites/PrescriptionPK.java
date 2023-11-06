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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((medecin == null) ? 0 : medecin.hashCode());
        result = prime * result + ((patient == null) ? 0 : patient.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrescriptionPK other = (PrescriptionPK) obj;
        if (medecin == null) {
            if (other.medecin != null)
                return false;
        } else if (!medecin.equals(other.medecin))
            return false;
        if (patient == null) {
            if (other.patient != null)
                return false;
        } else if (!patient.equals(other.patient))
            return false;
        return true;
    }


    
}
