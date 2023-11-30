package groupff.gmail.edu.sn.allodocteur.entites;
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @CreationTimestamp
    private Date date;
   
    private  String medicament ;

    private String description  ;
    

 //constructeur
 
    public Prescription() {

    }

    

    public Long getId() {
        return id;
    }


    public Prescription(Medecin medecin, Patient patient, Date date, String medicament, String description) {
        this.medecin = medecin;
        this.patient = patient;
        this.date = date;
        this.medicament = medicament;
        this.description = description;
    }



    public void setId(Long id) {
        this.id = id;
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

    public String getMedicament() {
        return medicament;
    }


    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
