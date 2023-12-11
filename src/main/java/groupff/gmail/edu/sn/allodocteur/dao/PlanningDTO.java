package groupff.gmail.edu.sn.allodocteur.dao;
import java.util.Date;
import jakarta.persistence.Column;

public class PlanningDTO {
    private Long id ;
    private  Date date ;
    @Column(name = "disponibilite", columnDefinition = "int default 1")
    private int disponibilite;
    private Long idMedecin ;

    
    public PlanningDTO() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getDisponibilite() {
        return disponibilite;
    }
    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Long getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Long idMedecin) {
        this.idMedecin = idMedecin;
    }

    @Override
    public String toString() {
        return "PlanningDTO [id=" + id + ", date=" + date + ", disponibilite=" + disponibilite + ", idMedecin="
                + idMedecin + "]";
    }
    

    
}
