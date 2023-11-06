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
        @Column(name = "traitement")
        private List<String> traitements;
    
        @Column(name = "allergie")
        private List<String> allergies;

        @Column(name = "diagnostic")
        private List<String> diagnostics;
    
        @Column(name = "groupeSanguin")
        private String groupeSanguin;
    
        @Column(name = "poids")
        private double poids;
    
        @Column(name = "taille")
        private double taille;
    
        @Column(name = "date")
        @Temporal(TemporalType.DATE)
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

        public List<String> getTraitements() {
            return traitements;
        }

        public void setTraitements(List<String> traitements) {
            this.traitements = traitements;
        }

        public List<String> getAllergies() {
            return allergies;
        }

        public void setAllergies(List<String> allergies) {
            this.allergies = allergies;
        }

        public List<String> getDiagnostics() {
            return diagnostics;
        }

        public void setDiagnostics(List<String> diagnostics) {
            this.diagnostics = diagnostics;
        }

        public String getGroupeSanguin() {
            return groupeSanguin;
        }

         public void setGroupeSanguin(String groupeSanguin) {
            this.groupeSanguin = groupeSanguin;
        }

        public double getPoids() {
            return poids;
        }

        public void setPoids(double poids) {
            this.poids = poids;
        }

        public double getTaille() {
            return taille;
        }

        public void setTaille(double taille) {
            this.taille = taille;
        }

        public void setNumero(Long numero) {
            this.numero = numero;
        }

        public Long getNumero() {
            return numero;
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

        
    }


