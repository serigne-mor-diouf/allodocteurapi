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
        private Long id;

        // Autres attributs
        private String allergi ;
        private String traitement ;
        private Date date ;

       
        @OneToOne
        @JoinColumn(name = "patient_id")
        private Patient patient;


       
        @JsonIgnore
        @OneToMany(mappedBy = "medecinDossierPk.dossiermedicale")
        private List<MedecinDossier> medecinDossiers;


        public DossiersMedicale(){

        }


// Getters et setters


       


        public Long getId() {
            return id;
        }

        public DossiersMedicale( String allergi, String traitement, Date date, Patient patient,
                List<MedecinDossier> medecinDossiers) {
           
            this.allergi = allergi;
            this.traitement = traitement;
            this.date = date;
            this.patient = patient;
            this.medecinDossiers = medecinDossiers;
        }


        public void setId(Long id) {
            this.id = id;
        }

        public String getAllergi() {
            return allergi;
        }

        public void setAllergi(String allergi) {
            this.allergi = allergi;
        }

        public String getTraitement() {
            return traitement;
        }

        public void setTraitement(String traitement) {
            this.traitement = traitement;
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


