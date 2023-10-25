package groupff.gmail.edu.sn.allodocteur.entites;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {
   
    @EmbeddedId
    private  PrescriptionPK  prescriptionPK ;
    private Date date;

    private Time heure;
   
    private  List<String> medicament ;

    private String description  ;
    

 //constructeur
 
    public Prescription() {

    }

    


    public PrescriptionPK getPrescriptionPK() {
        return prescriptionPK;
    }


    public void setPrescriptionPK(PrescriptionPK prescriptionPK) {
        this.prescriptionPK = prescriptionPK;
    }

    public List<String> getMedicament() {
        return medicament;
    }


    public void setMedicament(List<String> medicament) {
        this.medicament = medicament;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


     public Time getHeure() {
        return heure;
    }


    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
