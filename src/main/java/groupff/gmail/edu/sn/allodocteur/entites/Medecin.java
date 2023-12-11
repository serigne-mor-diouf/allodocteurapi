package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "medecin")
public class Medecin extends  Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String specialite ;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVous;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin" , cascade = CascadeType.ALL)
    private List<Consultation>  consultations;
    
    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
     private List<Prescription> prescriptions;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin" , cascade = CascadeType.ALL)
    private List<Planing> planings ;

    public Medecin() {
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

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Planing> getPlanings() {
        return planings;
    }

    public void setPlanings(List<Planing> planings) {
        this.planings = planings;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority("MEDECIN"));
        //return List.of(new SimpleGrantedAuthority("MEDECIN") , new SimpleGrantedAuthority("USER"));
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


}
