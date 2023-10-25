package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;
import groupff.gmail.edu.sn.allodocteur.services.PatientService.PatientRegistrationException;

@RestController
@RequestMapping("/api/patients")
@Validated
public class PatientController {
    @Autowired
    private PatientService patientService ;

    //si on enregistre un patient on lui donne son id avec responsEntity
    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO patientDTO) {
        try {
            System.out.println("Enregistrement patientDto = " + patientDTO);
            Patient patient = patientService.savePatient(patientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(patient.getId());
        } catch (PatientRegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec de l'enregistrement : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur interne s'est produite : " + e.getMessage());
        }
    }
    
    @GetMapping
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }


   // rechercher une specialite
    @GetMapping("/{specialite}")
     public List<Medecin> findMedecinsBySpecialite(@PathVariable String specialite){
        return patientService.findMedecinsBySpecialite(specialite);
     }


   

}
