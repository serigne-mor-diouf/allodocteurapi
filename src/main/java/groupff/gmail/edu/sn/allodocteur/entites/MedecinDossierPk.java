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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((medecin == null) ? 0 : medecin.hashCode());
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
        MedecinDossierPk other = (MedecinDossierPk) obj;
        if (medecin == null) {
            if (other.medecin != null)
                return false;
        } else if (!medecin.equals(other.medecin))
            return false;
        return true;
    }

}
