package groupff.gmail.edu.sn.allodocteur.entites;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "patient")
public class Patient  extends  Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rendezVous ;

    @JsonIgnore
    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
     private List<Prescription> prescriptions;

    //relation avec rappel
    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private List<Rappel> rappels;


    public Patient() {
    }

    public Patient(String nom, String prenom, String sexe, int age, String adresse, String telephone) {
        super(nom, prenom, sexe, age, adresse, telephone);
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

    public List<Rappel> getRappels() {
        return rappels;
    }

    public void setRappels(List<Rappel> rappels) {
        this.rappels = rappels;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

  
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority("PATIENT"));
      //return List.of(new SimpleGrantedAuthority("PATIENT") , new SimpleGrantedAuthority("USER"));
    }


}
