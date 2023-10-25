    package groupff.gmail.edu.sn.allodocteur.entites;

    import jakarta.persistence.*;

    import java.util.Date;
    import java.util.List;

    import com.fasterxml.jackson.annotation.JsonIgnore;

    @Entity
    @Table(name = "dossiermedicale")
    public class DossiersMedicale{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long numero;

        // Autres attributs
        private List<String>allergi ;
        private List<String> antecedent ;
        private Date date ;

        @OneToOne
        @JoinColumn(name = "patient_id")
        private Patient patient;
/////////////////////////////////////////////////////////////////////////
        @JsonIgnore
        @OneToMany(mappedBy = "medecinDossierPk.dossiermedicale")
        private List<MedecinDossier> medecinDossiers;

        public DossiersMedicale(){

        }

        public DossiersMedicale(List<String> allergi, List<String> traitement, Date date, Patient patient,
            List<MedecinDossier> medecinDossiers) {
            this.allergi = allergi;
            this.antecedent = traitement;
            this.date = date;
            this.patient = patient;
            this.medecinDossiers = medecinDossiers;
        }


        public void setNumero(Long numero) {
            this.numero = numero;
        }

        
        public Long getNumero() {
            return numero;
        }

        public List<String> getAllergi() {
            return allergi;
        }


        public void setAllergi(List<String> allergi) {
            this.allergi = allergi;
        }


       public List<String> getAntecedent() {
           return antecedent;
       }

       public void setAntecedent(List<String> antecedent) {
           this.antecedent = antecedent;
       }


        public List<MedecinDossier> getMedecinDossiers() {
            return medecinDossiers;
        }


        public void setMedecinDossiers(List<MedecinDossier> medecinDossiers) {
            this.medecinDossiers = medecinDossiers;
        }


        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        // public Medecin getMedecin() {
        //     return medecin;
        // }

        // public void setMedecin(Medecin medecin) {
        //     this.medecin = medecin;
        // }
    }


