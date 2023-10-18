package groupff.gmail.edu.sn.allodocteur.entites;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "medecindossier")
public class MedecinDossier {
// serev une cles patager
    @EmbeddedId
    private  MedecinDossierPk  medecinDossierPk ;

    private String motif ;

    private List<String> antecedent ;
    
    private List<String> allergie ; 


    public MedecinDossier(){

    }

    public MedecinDossierPk getMedecinDossierPk() {
        return medecinDossierPk;
    }

    public void setMedecinDossierPk(MedecinDossierPk medecinDossierPk) {
        this.medecinDossierPk = medecinDossierPk;
    }

    public String getMotif() {
        return motif;
    }


    public void setMotif(String motif) {
        this.motif = motif;
    }


    public List<String> getAntecedent() {
        return antecedent;
    }


    public void setAntecedent(List<String> antecedent) {
        this.antecedent = antecedent;
    }


    public List<String> getAllergie() {
        return allergie;
    }


    public void setAllergie(List<String> allergie) {
        this.allergie = allergie;
    }




}
