package groupff.gmail.edu.sn.allodocteur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.services.MedecinService;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;
import groupff.gmail.edu.sn.allodocteur.services.UtilisateurService;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService ;
    
    @Autowired
    private PatientService patientService ;

    @Autowired
    private UtilisateurService utilisateurService ;
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> saveMedecin(@RequestBody MedecinDTO medecinDTO){
        System.out.println("enregistrement MdecinDto = "+medecinDTO);
        Utilisateur  medecin = medecinService.inscriptionMedecin(medecinDTO);
           // enregistre un patient en recuperant la requette atendue ici 200
            if (medecin != null) {
                 return ResponseEntity.status(HttpStatus.CREATED).body(medecin.getId()) ;
            }
           else{
                System.out.println("error ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // lister medecin
    @GetMapping
    public ResponseEntity<List<Medecin>> getMedecins() {
        List<Medecin> medecins = patientService.getMedecins();
        return new ResponseEntity<>(medecins, HttpStatus.OK);
    }

    // seul le medecin connecte peut voir ses propres donnees 
    @GetMapping("/rendezvous")
    public ResponseEntity<List<RendezVous>> getRendezVousForMedecin(@RequestParam Long medecinId) {
        List<RendezVous> rendezVousList = medecinService.getRendezVousByMedecin(medecinId);
        return ResponseEntity.ok(rendezVousList);
    }

    @GetMapping("/consultations")
    public ResponseEntity<List<Consultation>> getConsultationsForMedecin(@RequestParam Long medecinId) {
        List<Consultation> consultationList = medecinService.getConsultationsByMedecin(medecinId);
        return ResponseEntity.ok(consultationList);
    }

    @GetMapping("/prescriptions")
    public ResponseEntity<List<Prescription>> getPrescriptionsForMedecin(@RequestParam Long medecinId) {
        List<Prescription> prescriptionList = medecinService.getPrescriptionsByMedecin(medecinId);
        return ResponseEntity.ok(prescriptionList);
    }    
}

