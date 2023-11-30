package groupff.gmail.edu.sn.allodocteur.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import groupff.gmail.edu.sn.allodocteur.AllodocteurHashPassword;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
@Valid
public class UserRegistrationDTO {
    

    @NotNull
    private String nom ;

    @Size(min = 5, max = 50)
    private String prenom;

    @Pattern(regexp = "[a-zA-Z]+")
    private String sexe;


    @Min(01)
    @Max(100)
    private int age;

    @Size(min = 5, max = 50)
    private String adresse ;

    @Size(min = 9, max =15 )
    private String telephone ;

    @NotEmpty
    @Email
    private String email ;
    
    @NotEmpty
    @Size(min = 5, message = "Le mot de passe doit comporter au moins 5 caractères")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Le mot de passe doit contenir au moins une minuscule, une majuscule et un chiffre")
    private String password;

    @Column(name = "satut", columnDefinition = "int default 1")  
    private String statut;

    public UserRegistrationDTO(){
        
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
//
    public void updateData(Utilisateur utilisateur){
        //cripter le mot de passe  en base 64
     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(this.getPassword());
        utilisateur.setNom(this.getNom());
        utilisateur.setPrenom(this.getPrenom());
        utilisateur.setSexe(this.getSexe());
        utilisateur.setAge(this.getAge());
        utilisateur.setAdresse(this.getAdresse());
        utilisateur.setTelephone(this.getTelephone());
        //utilisateur.setProfil(this.getProfil());
        utilisateur.setEmail(this.getEmail());
        utilisateur.setPassword(hashedPassword);
    }
    
    @Override
    public String toString() {
        return " nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", age=" + age
                + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email
                + ", password=" + password ;
    }

    
    
}
