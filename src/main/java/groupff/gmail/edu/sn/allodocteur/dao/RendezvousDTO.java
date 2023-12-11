package groupff.gmail.edu.sn.allodocteur.dao;

import java.util.Date;

import jakarta.persistence.Column;


public class RendezvousDTO {
    private Long id ;
    private Date date ;
    @Column(name = "statut", columnDefinition = "VARCHAR(255) DEFAULT 'confirmer'")
    private String statut;
    private String motif ;
    private Long idPatient ;
    private Long idMedecin ;
    private Date dateCreation ;

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
    

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }

    

    @Override
    public String toString() {
        return "RendezvousDTO [id=" + id + ", date=" + date + ", statut=" + statut + ", motif=" + motif + ", idPatient="
                + idPatient + ", dateCreation=" + dateCreation + "]";
    }

    public Long getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Long idMedecin) {
        this.idMedecin = idMedecin;
    }
    
    
}
