package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rappel")
public class Rappel {
    @Id
    private  Long id ;
    private String type ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private  String statut  ;
    private String message ;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Rappel(String type, Date date, String statut, String message, Patient patient) {
        this.type = type;
        this.date = date;
        this.statut = statut;
        this.message = message;
        this.patient = patient;
    }

    public Rappel(){

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
