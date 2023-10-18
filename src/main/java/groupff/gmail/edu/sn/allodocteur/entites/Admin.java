package groupff.gmail.edu.sn.allodocteur.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin  extends  Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;


    public Admin(Long id) {
        this.id = id;
    }

    public Admin(String nom, String prenom, String sexe, String age, String adresse, String telephone, String profil, String email, String password, Long id) {
        super() ;
        this.id = id;
    }

    public  Admin(){

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
