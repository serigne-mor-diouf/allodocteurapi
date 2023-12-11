package groupff.gmail.edu.sn.allodocteur.entites;
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
@Table(name = "consultation")
public class Consultation {
// sert a dire que c'est  une cles patager
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

   
    private String motif ;

    private String antecedent ;
    
    private String allergie ; 

    private Date date ;

    private String groupeSanguin ;

    private String diagnostic ;

    @Column(name = "poids")
    private Double poids;
    
    @Column(name = "taille")
    private Double taille;

    private String  profession  ;

    public Consultation(){

    }
  
   
    

    public Consultation(Medecin medecin, Patient patient, String motif, String antecedent, String allergie, Date date,
            String groupeSanguin, String diagnostic, Double poids, Double taille, String profession) {
        this.medecin = medecin;
        this.patient = patient;
        this.motif = motif;
        this.antecedent = antecedent;
        this.allergie = allergie;
        this.date = date;
        this.groupeSanguin = groupeSanguin;
        this.diagnostic = diagnostic;
        this.poids = poids;
        this.taille = taille;
        this.profession = profession;
    }


    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getId() {
        return id;
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



    public Double getTaille() {
        return taille;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getAntecedent() {
        return antecedent;
    }

    public void setAntecedent(String antecedent) {
        this.antecedent = antecedent;
    }

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }


public void setPoids(Double poids) {
    this.poids = poids;
}

    public Double getPoids() {
        return poids;
    }

}