package groupff.gmail.edu.sn.allodocteur.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "patient")
public class Patient  extends  Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVous ;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private DossiersMedicale dossierMedicale ;


    @JsonIgnore
    @OneToMany(mappedBy = "prescriptionPK.patient")
     private List<Prescription> prescriptions;

    //relation avec rappel
    @OneToMany(mappedBy = "patient")
    private List<Rappel> rappels;

    public Patient(String nom, String prenom, String sexe, String age, String adresse, String telephone, String profil, String email, String password, String motif,  List<RendezVous> rendezVous, DossiersMedicale dossierMedicale, List<Prescription> prescriptions, List<Rappel> rappels) {
        super();
        this.rendezVous = rendezVous;
        this.dossierMedicale = dossierMedicale;
        this.prescriptions = prescriptions;
        this.rappels = rappels;
    }

    public Patient(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public DossiersMedicale getDossierMedicale() {
        return dossierMedicale;
    }

    public void setDossierMedicale(DossiersMedicale dossierMedicale) {
        this.dossierMedicale = dossierMedicale;
    }

    public List<Rappel> getRappels() {
        return rappels;
    }

    public void setRappels(List<Rappel> rappels) {
        this.rappels = rappels;
    }

}
