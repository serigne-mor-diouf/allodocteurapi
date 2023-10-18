package groupff.gmail.edu.sn.allodocteur.entites;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private  List<String> nomMedicament ;
    private String description  ;
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

 //constructeur

    public Prescription() {

    }

    public Prescription(List<String> nomMedicament, String description, Medecin medecin, Patient patient) {
        this.nomMedicament = nomMedicament;
        this.description = description;
        this.medecin = medecin;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<String> getNomMedicament() {
        return nomMedicament;
    }

    public void setNomMedicament(List<String> nomMedicament) {
        this.nomMedicament = nomMedicament;
    }
}
