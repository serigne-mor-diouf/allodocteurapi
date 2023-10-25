package groupff.gmail.edu.sn.allodocteur.entites;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "planing")
public class Planing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private  Date date ;
    private  Time heuredebut ;
    private  Time heureFin ;
    private  String jour ;

    @Column(name = "disponibilite", columnDefinition = "int default 1")
    private int disponibilite;

   

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin ;

    public Planing(Date date, Time heuredebut, Time heureFin, String jour, Medecin medecin) {
        this.date = date;
        this.heuredebut = heuredebut;
        this.heureFin = heureFin;
        this.jour = jour;
        this.medecin = medecin;
    }

    public Planing() {
        this.disponibilite = 1; // La disponibilité par défaut est 1
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

    public Time getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(Time heuredebut) {
        this.heuredebut = heuredebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }
    

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    

    
}
