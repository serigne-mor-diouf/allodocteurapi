package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.Embeddable;


import jakarta.persistence.ManyToOne;

@Embeddable
public class MedecinDossierPk {
  
    @ManyToOne
    private Medecin medecin;
    
    @ManyToOne
    private DossiersMedicale dossiermedicale;

    public MedecinDossierPk(Medecin medecin, DossiersMedicale dossierMedicale) {
        this.medecin = medecin;
        this.dossiermedicale = dossierMedicale;
    }


    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public DossiersMedicale getDossierMedicale() {
        return dossiermedicale;
    }

    public void setDossierMedicale(DossiersMedicale dossierMedicale) {
        this.dossiermedicale = dossierMedicale;
    }

    

    
}
