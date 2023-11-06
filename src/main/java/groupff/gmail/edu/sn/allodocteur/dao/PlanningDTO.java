package groupff.gmail.edu.sn.allodocteur.dao;

import java.util.Date;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import jakarta.persistence.Column;


public class PlanningDTO {
    private Long id ;
    private  Date date ;
    @Column(name = "disponibilite", columnDefinition = "int default 1")
    private int disponibilite;
    private Medecin medecin ;


    
    public PlanningDTO(Date date, int disponibilite, Medecin medecin) {
        this.date = date;
        this.disponibilite = disponibilite;
        this.medecin = medecin;
    }

    
    public PlanningDTO() {
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
    public int getDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }
    public Medecin getMedecin() {
        return medecin;
    }
    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    
}
