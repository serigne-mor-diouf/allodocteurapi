package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import groupff.gmail.edu.sn.allodocteur.dao.PrescriptionDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;
import groupff.gmail.edu.sn.allodocteur.repositories.PrescriptionRepository;
import groupff.gmail.edu.sn.allodocteur.services.PrescriptionService;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    
    @Autowired
    private PrescriptionService prescriptionService ;
    @Autowired
    private PrescriptionRepository prescriptionRepository ;
     @PostMapping
    public ResponseEntity<String> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        prescriptionService.createPrescription(prescriptionDTO);
        return ResponseEntity.ok("Prescription créée avec succès");
    }

    // Endpoint pour modifier une prescription
   
@PutMapping("/{id}")
public ResponseEntity<Prescription> modifierPrescription(@PathVariable Long id, @RequestBody PrescriptionDTO prescriptionDTO) {
    Optional<Prescription> prescriptionOptional = prescriptionRepository.findById(id);

    if (prescriptionOptional.isPresent()) {
        Prescription prescriptionExistante = prescriptionOptional.get();

        prescriptionExistante.setDate(prescriptionDTO.getDate());
        prescriptionExistante.setDescription(prescriptionDTO.getDescription());
        prescriptionExistante.setMedicament(prescriptionDTO.getMedicament());

        // Enregistrez la prescription mise à jour dans le repository
        Prescription updatedPrescription = prescriptionRepository.save(prescriptionExistante);
        return ResponseEntity.ok(updatedPrescription);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerPrescription(@PathVariable Long id
         ) {      
        try {
           prescriptionService.supprimerPrescription(id);
            return ResponseEntity.ok("Prescription supprimée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

     @GetMapping
    public ResponseEntity<List<Prescription>> getPrescription(){
        List<Prescription> prescriptions = prescriptionService.getPrescription();
        if(prescriptions!=null && !prescriptions.isEmpty()){
            return ResponseEntity.ok(prescriptions) ;
        }else{
            return ResponseEntity.notFound().build() ;
        }
    }

     @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id){
       Prescription prescription =  prescriptionService.getPrescriptionById(id);
        if(prescription!=null){
            return ResponseEntity.ok(prescription) ;
        }else{
            return ResponseEntity.notFound().build() ;
        }
    } 
}
