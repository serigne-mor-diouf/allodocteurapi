package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "rendezvous")
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String lieu ;
    private String statut ;

    // Relation avec le patient
    @ManyToOne
    @JoinColumn(name = "patient_id") // Crée une clé étrangère nommée "patient_id"
    private Patient patient ;

    // Relation avec le médecin
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
    // Constructeurs,

    

    public RendezVous(){

    }


    // getters, setters

    public RendezVous( Date date, String lieu, String statut, Patient patient, Medecin medecin) {
        
        this.date = date;
        this.lieu = lieu;
        this.statut = statut;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
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
}
