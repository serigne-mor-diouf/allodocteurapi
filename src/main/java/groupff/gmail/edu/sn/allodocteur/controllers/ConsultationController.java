package groupff.gmail.edu.sn.allodocteur.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.dao.ConsultationDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.ConsultationRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import groupff.gmail.edu.sn.allodocteur.services.ConsultationService;

@RestController
@RequestMapping("/api/consultations") 
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService ; 
    
    @Autowired
    private ConsultationRepository consultationRepository ;

      @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;


    @GetMapping
    public List<Consultation> getConsultation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Medecin> optionalMedecin = medecinRepository.findByEmail(username);
        Optional<Patient> optionalPatient = patientRepository.findByEmail(username);

        if (optionalMedecin.isPresent()) {
            Medecin medecin = optionalMedecin.get();
            // Filtrer les prescriptions, rendez-vous et consultations effectués par le médecin
            List<Consultation> consultations = medecin.getConsultations().stream()
                    .filter(consultation -> consultation.getMedecin().equals(medecin))
                    .collect(Collectors.toList());
                        return consultations;

            } else if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
               List<Consultation> consultations = patient.getConsultations().stream()
                    .filter(consultation -> consultation.getPatient().equals(patient))
                    .collect(Collectors.toList());
                                                        
                return consultations ;

            }
            else {
            return consultationService.getConsultation() ;   
        }
}


    //creer une consultation patien
    @PostMapping
    public ResponseEntity<?> createConsultation(
        @RequestBody ConsultationDTO consultationDTO , @AuthenticationPrincipal  Utilisateur user){
        Consultation  consultation = consultationService.createConsultation(consultationDTO , user);
        if (consultation != null) {
            return ResponseEntity.ok("consulatation créée avec succès");
            
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

   // Mettre à jour une consultation
   @PutMapping("/{id}")
    public ResponseEntity<Consultation> modifierConsultation(@PathVariable Long id,
            @RequestBody ConsultationDTO consultationDTO) {
        Optional<Consultation> consultationOptional = consultationRepository.findById(id);

        if (consultationOptional.isPresent()) {
            Consultation consultationExistante = consultationOptional.get();

            consultationExistante.setMotif(consultationDTO.getMotif());
            consultationExistante.setAntecedent(consultationDTO.getAntecedent());
            consultationExistante.setAllergie(consultationDTO.getAllergie());
            consultationExistante.setDate(consultationDTO.getDate());
            consultationExistante.setGroupeSanguin(consultationDTO.getGroupeSanguin());
            consultationExistante.setDiagnostic(consultationDTO.getDiagnostic());
            consultationExistante.setPoids(consultationDTO.getPoids());
            consultationExistante.setTaille(consultationDTO.getTaille());
            consultationExistante.setProfession(consultationDTO.getProfession());

            // Enregistrez la consultation mise à jour dans le repository
            Consultation updatedConsultation = consultationRepository.save(consultationExistante);
            return ResponseEntity.ok(updatedConsultation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   
//Supprimer une consultation
@DeleteMapping("/{id}")
public void deleteConsultation(@PathVariable Long id){
    consultationService.deleteConsultation(id);
}

}
