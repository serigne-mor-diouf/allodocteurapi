package groupff.gmail.edu.sn.allodocteur.entites;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "rendezvous")
public class RendezVous  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    
    @Column(name = "statut", columnDefinition = "VARCHAR(255) DEFAULT 'confirmer'")
    private String statut;

    private String motif ;

    // Relation avec le patient
  
    @ManyToOne
    @JoinColumn(name = "patient_id") // Crée une clé étrangère nommée "patient_id"
    private Patient patient ;

    // Relation avec le médecin
   
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @CreationTimestamp
    private Date dateCreation ;
    // Constructeurs,

    public RendezVous(){
        this.statut = "confirmer" ;
    }
    // getters, setters

    public Long getId() {
        return id;
    }

    public RendezVous(Date date, Date dateCreation , String statut, String motif, Patient patient, Medecin medecin) {
        this.date = date;
        this.statut = statut;
        this.motif = motif;
        this.patient = patient;
        this.medecin = medecin;
        this.dateCreation = dateCreation ;
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

     

    @Override
    public String toString() {
        return "RendezVous [id=" + id + ", date=" + date + ", statut=" + statut + ", motif=" + motif + ", patient="
                + patient + ", medecin=" + medecin + ", dateCreation=" + dateCreation + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        RendezVous other = (RendezVous) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
