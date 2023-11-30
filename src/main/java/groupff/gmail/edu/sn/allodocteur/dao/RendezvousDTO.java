package groupff.gmail.edu.sn.allodocteur.dao;

import java.util.Date;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import jakarta.persistence.Column;

public class RendezvousDTO {
    private Long id ;
    private Date date ;
    @Column(name = "statut", columnDefinition = "VARCHAR(255) DEFAULT 'confirmer'")
    private String statut;

    private String motif ;
    private Patient patient ;
    private Medecin medecin;
    private Date dateCreation ;

    public RendezvousDTO(Date date, Date dateCreation ,String statut, String motif, Patient patient, Medecin medecin) {
        this.date = date;
        this.statut = statut;
        this.motif = motif;
        this.patient = patient;
        this.medecin = medecin;
        this.dateCreation = dateCreation ;
    }

    public RendezvousDTO(){

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
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
    
}
