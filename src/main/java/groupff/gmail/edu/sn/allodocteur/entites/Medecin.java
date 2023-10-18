package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "medecin")
public class Medecin extends  Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String specialite ;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVous;
    


    @OneToMany(mappedBy = "medecinDossierPk.medecin" , cascade = CascadeType.ALL)
    private List<MedecinDossier> medecinDossiers;


    @OneToMany(mappedBy = "medecin" , cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;


//constructors

   

    @Override
    public Long getId() {
        return id;
    }

  


    public Medecin(String nom, String prenom, String sexe, int age, String adresse, String telephone, String profil,
            String email, String password, String specialite, List<RendezVous> rendezVous,
            List<MedecinDossier> medecinDossiers, List<Prescription> prescriptions) {
        super(nom, prenom, sexe, age, adresse, telephone, profil, email, password);
        this.specialite = specialite;
        this.rendezVous = rendezVous;
        this.medecinDossiers = medecinDossiers;
        this.prescriptions = prescriptions;
    }



    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Medecin() {



    }
}
