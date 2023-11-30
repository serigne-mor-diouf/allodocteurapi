package groupff.gmail.edu.sn.allodocteur.entites;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin  extends  Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;

    public Admin() {
        
    }

    public Admin(String nom, String prenom, String sexe, int age, String adresse, String telephone, Long id) {
        super(nom, prenom, sexe, age, adresse, telephone);
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority("ADMIN"));
        //return List.of(new SimpleGrantedAuthority("MEDECIN") , new SimpleGrantedAuthority("USER"));
    }

}
