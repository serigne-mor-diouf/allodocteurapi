package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import groupff.gmail.edu.sn.allodocteur.dao.AdminDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.services.AdminService;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;
import groupff.gmail.edu.sn.allodocteur.services.UtilisateurService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired    
    private AdminService adminService;

    @Autowired
    private PatientService patientService ;

    @Autowired
    private UtilisateurService utilisateurService;

    // Endpoint pour sauvegarder un nouvel admin
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveAdmin(@RequestBody AdminDTO adminDTO) {
        System.out.println("Enregistrement de l'administrateur = " + adminDTO);
        Utilisateur admin = adminService.saveAdmin(adminDTO);
        if (admin != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(admin);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listeMedecin")
    public ResponseEntity<List<Medecin>> getMedecins() {
        List<Medecin> medecins = patientService.getMedecins();
    
        if (medecins != null && !medecins.isEmpty()) {
            return ResponseEntity.ok(medecins); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @GetMapping("/listePatient")
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> patients =  patientService.getPatients();
        if(patients != null && !patients.isEmpty()){
            return ResponseEntity.ok(patients) ;
        }
        else{
            return ResponseEntity.notFound().build() ;
        }
    }

    // Endpoint pour modifier un médecin
    @PutMapping("/{editMedecin}")
    public ResponseEntity<String> editMedecin(@PathVariable Medecin medecin) {
        try {
            adminService.editMedecin(medecin);
            return ResponseEntity.ok("Médecin modifié avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint pour supprimer un médecin
    @DeleteMapping("/deleteMedecin/{id}")
    public ResponseEntity<String> deleteMedecin(@PathVariable Long id) {
        adminService.deleteMedecin(id);
        return ResponseEntity.ok("Médecin supprimé avec succès.");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/verrouiller/{utilisateurId}")
    public ResponseEntity<?> verrouillerUtilisateur(@PathVariable Long utilisateurId) {
        utilisateurService.verrouillerUtilisateur(utilisateurId);
        return ResponseEntity.ok("Utilisateur verrouillé avec succès.");
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/deverrouiller/{utilisateurId}")
    public ResponseEntity<?> deverrouillerUtilisateur(@PathVariable Long utilisateurId) {
        utilisateurService.deverrouillerUtilisateur(utilisateurId);
        return ResponseEntity.ok("Utilisateur déverrouillé avec succès.");
    }

}
