package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder
@Entity
@Table(name = "medecin")
public class Medecin extends  Utilisateur implements Serializable {
    private String specialite ;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVous;
    
    @OneToMany(mappedBy = "medecinDossierPk.medecin" , cascade = CascadeType.ALL)
    private List<MedecinDossier> medecinDossiers;

    @OneToMany(mappedBy = "prescriptionPK.medecin")
     private List<Prescription> prescriptions;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin" , cascade = CascadeType.ALL)
    private List<Planing> planings ;

    public Medecin(){

    }

    public Medecin(String nom, String prenom, String sexe, int age, String adresse, String telephone, String profil,
            String email, String password, String specialite, List<RendezVous> rendezVous,
            List<MedecinDossier> medecinDossiers, List<Prescription> prescriptions, List<Planing> planings) {
        super(nom, prenom, sexe, age, adresse, telephone, profil, email, password);
        this.specialite = specialite;
        this.rendezVous = rendezVous;
        this.medecinDossiers = medecinDossiers;
        this.prescriptions = prescriptions;
        this.planings = planings;
    }

    
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public List<MedecinDossier> getMedecinDossiers() {
        return medecinDossiers;
    }

    public void setMedecinDossiers(List<MedecinDossier> medecinDossiers) {
        this.medecinDossiers = medecinDossiers;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Planing> getPlanings() {
        return planings;
    }

    public void setPlanings(List<Planing> planings) {
        this.planings = planings;
    }

   
    

   

}
