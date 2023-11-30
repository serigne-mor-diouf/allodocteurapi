package groupff.gmail.edu.sn.allodocteur.entites;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
public class Token {
   @Id
   private String valeur ; 

   private Boolean revoquer ;

   @Column(nullable = false)
   private Date dateCreation ;

   @Column(nullable = false)
   private Date validite ;

   private Date deconnection  ; 

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur ;


    public Token(String valeur, Date dateCreation, Date validite, Utilisateur utilisateur) {
        this();
        this.valeur = valeur;
        this.dateCreation = dateCreation;
        this.validite = validite;
        this.utilisateur = utilisateur;
    }
    
    public Token() {
        this.revoquer = false ;
    }

    
   public boolean isTokenValide(){
    Date maintenant = new Date();
    return !revoquer && maintenant.before(validite) && (deconnection == null);
}

}
