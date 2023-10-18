package groupff.gmail.edu.sn.allodocteur.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
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
    public ResponseEntity<Long>savePatient( @RequestBody PatientDTO patientDTO) {
        System.out.println("enregistrement patientDto = "+patientDTO);
        Patient patient = patientService.savePatient(patientDTO);
        // enregistre un patient en recuperant la requette atendue ici 200
        return ResponseEntity.status(HttpStatus.CREATED).body(patient.getId()) ;
    }

}
