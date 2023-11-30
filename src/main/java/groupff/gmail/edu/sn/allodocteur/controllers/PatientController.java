package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;
@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {
    @Autowired
    private PatientService patientService ;

    //si on enregistre un patient on lui donne son id avec responsEntity
    @PreAuthorize("hasAnyAuthority('PATIENT', 'MEDECIN')")
    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO) {       
            System.out.println("Enregistrement patientDto = " + patientDTO);
            Utilisateur patient = patientService.inscriptionPatient(patientDTO);
          if (patient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(patient.getId());
          }
          else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
          }
        }
    
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN')")
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> patients =  patientService.getPatients();
            return ResponseEntity.ok(patients) ;
        }  

   // rechercher une specialite
   @GetMapping("/specialite")
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
