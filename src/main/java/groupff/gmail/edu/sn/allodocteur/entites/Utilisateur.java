package groupff.gmail.edu.sn.allodocteur.entites;
import jakarta.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED) // Utilisation de l'héritage "table par classe" (table per class)
public abstract class  Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String nom ;
    private String prenom ;
    private String sexe ;
    private int age ;
    private String adresse ;
    private String telephone ;
    private String profil ;

    @Column(unique = true , nullable = false)
    private String email ;
    private String password ;

    
    @Column(name = "statut", columnDefinition = "int default 1")
    private int statut;

    public Utilisateur(){

    }

    public Utilisateur(String nom, String prenom, String sexe, int age, String adresse, String telephone, String profil, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.age = age;
        this.adresse = adresse;
        this.telephone = telephone;
        this.profil = profil;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
