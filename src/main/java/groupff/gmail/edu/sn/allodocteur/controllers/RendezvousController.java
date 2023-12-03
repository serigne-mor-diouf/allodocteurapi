package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;
import groupff.gmail.edu.sn.allodocteur.services.RendezvousService;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezvousController {

    @Autowired
    private RendezvousService rendezvousService ;

    @Autowired
    private RendezvousRepository  rendezvousRepository;
    
  @GetMapping
    public ResponseEntity<List<RendezVous>> getRendezVous(){
        List<RendezVous> rendezVous = rendezvousService.getAllRendezVous();
        if(rendezVous!=null && !rendezVous.isEmpty()){
            return ResponseEntity.ok(rendezVous) ;
        }else{
            return ResponseEntity.notFound().build() ;
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


    @PostMapping
    public ResponseEntity<?> createRendezVous(@RequestBody RendezvousDTO rendezvousDTO
    ) 
       {
        RendezVous rendezVous = rendezvousService.planifierRendezVous(rendezvousDTO);
    
        if (rendezVous != null) {
            return ResponseEntity.ok("rendez-vous créée avec succès");
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
                rendezVousExistante.setMedecin(rendezVousDTO.getMedecin());

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
