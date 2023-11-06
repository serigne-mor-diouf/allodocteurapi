package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;
@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {
    @Autowired
    private PatientService patientService ;

    //si on enregistre un patient on lui donne son id avec responsEntity
    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO) {       
            System.out.println("Enregistrement patientDto = " + patientDTO);
            Patient patient = patientService.savePatient(patientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(patient.getId());
        }
    
    
    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> patients =  patientService.getPatients();
        if(patients != null && !patients.isEmpty()){
            return ResponseEntity.ok(patients) ;
        }
        else{
            return ResponseEntity.notFound().build() ;
        }
    }


   // rechercher une specialite
   @GetMapping("/medecins/specialite")
   public ResponseEntity<List<Medecin>> findMedecinsBySpecialite(@RequestParam String specialite) {
       List<Medecin> medecins = patientService.findMedecinsBySpecialite(specialite);
       if (medecins.isEmpty()) {
           return ResponseEntity.notFound().build();
       } else {
           return ResponseEntity.ok(medecins);
       }
   }
   
    @GetMapping("/medecins")
    public ResponseEntity<List<Medecin>> findMedecinByNomAndPrenom(
        @RequestParam String nom,
        @RequestParam String prenom
    ) {
        final List<Medecin> medecins =  patientService.findByNomAndPrenom(nom, prenom);
        if (medecins.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(medecins);
        }
    }
    
   

}
