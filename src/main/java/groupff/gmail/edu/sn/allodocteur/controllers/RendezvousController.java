package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import groupff.gmail.edu.sn.allodocteur.dao.RendezvousDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;
import groupff.gmail.edu.sn.allodocteur.services.RendezvousService;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezvousController {

    @Autowired
    private RendezvousService rendezvousService ;

    @Autowired
    private RendezvousRepository  rendezvousRepository;

     @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;
    
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN' , 'PATIENT')")
    @GetMapping
    public List<RendezVous> getRendezVous(){
        //obtenir les informations d'authentification pour l'utilisateur actuel
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //extrair le nom d'utilisateur
        String username = authentication.getName();

        Optional<Medecin> optionalMedecin = medecinRepository.findByEmail(username);
        Optional<Patient> optionalPatient = patientRepository.findByEmail(username);

        if (optionalMedecin.isPresent()) {
            Medecin medecin = optionalMedecin.get();
            // Filtrer les prescriptions, rendez-vous et consultations effectués par le médecin
            List<RendezVous> rendezVous = medecin.getRendezVous().stream()
                    .filter(rendezvous -> rendezvous.getMedecin().equals(medecin))
                    .collect(Collectors.toList());
                        return rendezVous;

            } else if (optionalPatient.isPresent()) {
                Patient patient = optionalPatient.get();
                List<RendezVous> rendezVous = patient.getRendezVous().stream()
                    .filter(rendezvous -> rendezvous.getPatient().equals(patient))
                    .collect(Collectors.toList());
                                                        
                return rendezVous ;

            }
            else {
            return rendezvousService.getAllRendezVous() ;   
        }
    }   


    @GetMapping("/getRendezVous")
    public ResponseEntity<RendezVous> getRendezVousById(@RequestParam Long id) {
        RendezVous rendezVous = rendezvousService.getRendezVous(id);
        if (rendezVous != null) {
            return ResponseEntity.ok(rendezVous);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //rechercher un rv  par  nom et prenom
    @GetMapping("/recherchenomprenom")
    public List<RendezVous>rechercherRendezVous(@RequestParam String nom){
        return rendezvousService.rechercherRendezVousParPatient(nom);
    }

     @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN')")
    @PostMapping
    public ResponseEntity<RendezVous> creaRendezVous(
        @RequestBody RendezvousDTO rendezvousDTO ,
        @AuthenticationPrincipal  Utilisateur user ){
            System.out.println("creaRendezVous userDetails = " +user);
        
        RendezVous rendezVous = rendezvousService.ajouterRendezVous(rendezvousDTO , user);
    
        if (rendezVous != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rendezVous);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN' , 'PATIENT')")
    @PostMapping("/prendreRv")
    public ResponseEntity<?> prendreRendezVous(@RequestBody RendezvousDTO rendezvousDTO ,
    @AuthenticationPrincipal Utilisateur user) 
       {
        RendezVous rendezVous = rendezvousService.prendreRendezVous(rendezvousDTO , user);
    
        if (rendezVous != null) {
            return ResponseEntity.ok("rendez-vous a ete recue avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    
    // Mettre à jour un rendezvous patient
    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezvousDTO rendezVousDTO) {
    
        Optional<RendezVous> rendezvousOptional =  rendezvousRepository.findById(id);
        if (rendezvousOptional.isPresent()) {
            RendezVous rendezVousExistante = rendezvousOptional.get();

                rendezVousExistante.setDate(rendezVousDTO.getDate());
                rendezVousExistante.setMotif(rendezVousDTO.getMotif());
                rendezVousExistante.setStatut(rendezVousDTO.getStatut()) ;
                //rendezVousExistante.setMedecin(rendezVousDTO.getMedecin());

                RendezVous updatRendezVous = rendezvousRepository.save(rendezVousExistante);
                return ResponseEntity.ok(updatRendezVous);
        }else{

            return ResponseEntity.notFound().build();
        }
    }

    //delete a rv
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN')")
    @DeleteMapping("/{id}")
    public void deleteRendezvous(@PathVariable Long id){
        rendezvousService.supprimerRendezVous(id);
    }

    //lister  les rv confirmer 
    @GetMapping("/confirmer")
    public ResponseEntity<List<RendezVous>> listeRendezVousConfirmer(@PathVariable Long confirmer) {
        List<RendezVous> rendezVousConfirmer = rendezvousService.getRendezVousConfirmer();
        
        if (rendezVousConfirmer.isEmpty()) {        
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); 
        } else {
            return ResponseEntity.ok(rendezVousConfirmer);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN')")
    @PostMapping("/annuler/{id}")
    public ResponseEntity<String> annulerRendezVous(@PathVariable Long id) {
        try {
            rendezvousService.annulerRendezVous(id);
            return ResponseEntity.ok("Votre Rendez-vous a ete annulé avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } 
}
