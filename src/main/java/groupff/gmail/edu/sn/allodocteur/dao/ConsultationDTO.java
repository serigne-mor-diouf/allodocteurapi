package groupff.gmail.edu.sn.allodocteur.dao;
import java.util.Date;

import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;

public class ConsultationDTO {
    private Long id ;

    private String motif ;

    private String antecedent ;
    
    private String allergie ; 

    private Date date ;

    private String groupeSanguin ;
    
    private String diagnostic ;

    private Long idPatient ; 

    private Consultation consultation ;

    private Double poids;
  
    private Double taille;

    private String profession ;
 
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
   

    public ConsultationDTO(){

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
    
    public void setTaille(Double taille) {
        this.taille = taille;
    }
    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public double getTaille() {
        return taille;
    }
    
    public void setTaille(double taille) {
        this.taille = taille;
    }
    
    public Consultation getConsultation() {
        return consultation;
    }
    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public Long getIdPatient() {
        return idPatient;
    }
    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }
    @Override
    public String toString() {
        return "ConsultationDTO [id=" + id + ", motif=" + motif + ", antecedent=" + antecedent + ", allergie="
                + allergie + ", date=" + date + ", groupeSanguin=" + groupeSanguin + ", diagnostic=" + diagnostic
                + ", idPatient=" + idPatient + ", consultation=" + consultation + ", poids=" + poids + ", taille="
                + taille + ", profession=" + profession + "]";
    }
   

    

}
