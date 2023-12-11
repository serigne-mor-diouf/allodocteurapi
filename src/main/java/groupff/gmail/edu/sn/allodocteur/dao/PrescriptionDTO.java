package groupff.gmail.edu.sn.allodocteur.dao;
import java.sql.Date;
public class PrescriptionDTO {

    private Long id ;

    private Date date;
   
    private  String medicament ;

    private String description  ;
    
    private Long idPatient ; 

    public PrescriptionDTO(){

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


    public String getMedicament() {
        return medicament;
    }


    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }

}
