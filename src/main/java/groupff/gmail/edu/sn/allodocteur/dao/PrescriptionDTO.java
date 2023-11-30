package groupff.gmail.edu.sn.allodocteur.dao;
import java.sql.Date;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
public class PrescriptionDTO {

    private Long id ;

    private Date date;
   
    private  String medicament ;

    private String description  ;
    
    private Patient patient ; 

    private Medecin medecin  ; 

    public PrescriptionDTO(){

    }

    public PrescriptionDTO(Date date, String medicament, String description, Patient patient, Medecin medecin) {
        this.date = date;
        this.medicament = medicament;
        this.description = description;
        this.patient = patient;
        this.medecin = medecin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

}
