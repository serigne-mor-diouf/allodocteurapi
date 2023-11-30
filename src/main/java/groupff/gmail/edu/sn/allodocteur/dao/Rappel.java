package groupff.gmail.edu.sn.allodocteur.dao;
import java.util.Date;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;

public class Rappel {
    private  Long id ;
    private String type ;
    private Date date;
    private  String statut  ;
    private String message ;
    private Patient patient;

    public Rappel(String type, Date date, String statut, String message, Patient patient) {
        this.type = type;
        this.date = date;
        this.statut = statut;
        this.message = message;
        this.patient = patient;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
