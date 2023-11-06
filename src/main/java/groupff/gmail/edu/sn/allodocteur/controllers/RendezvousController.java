package groupff.gmail.edu.sn.allodocteur.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
import groupff.gmail.edu.sn.allodocteur.dao.RendezvousDTO;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.services.RendezvousService;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezvousController {

    @Autowired
    private RendezvousService rendezvousService ;
    
  @GetMapping
    public ResponseEntity<List<RendezVous>> getRendezVous(){
        List<RendezVous> rendezVous = rendezvousService.getRendezVous();
        if(rendezVous!=null && !rendezVous.isEmpty()){
            return ResponseEntity.ok(rendezVous) ;
        }else{
            return ResponseEntity.notFound().build() ;
        }
    }

    //rechercher un rv  par  nom et prenom
    @GetMapping("/recherchenomprenom")
    public List<RendezVous>rechercherRendezVous(@RequestParam String nom , @RequestParam  String prenom){
        return rendezvousService.rechercherRendezVousParPatient(nom , prenom);
    }


    //planifier un rv a un patient
    @PostMapping
    public ResponseEntity<RendezVous> creaRendezVous(@RequestBody MedecinDTO medecin, @RequestBody PatientDTO patient, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestParam String motif) {
        RendezVous rendezVous = rendezvousService.planifierRendezVous(medecin, patient, date, motif);

        if (rendezVous != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rendezVous);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //mettre  a jour un rv a un patient
    @PutMapping("/{id}")
    public RendezVous updatRendezVous(RendezvousDTO rendezVous){
        return rendezvousService.modifierRendezVous(rendezVous);
    }

    //delete a rv
    @DeleteMapping("/{id}")
    public void deleteRendezvous( @PathVariable Long id){
        rendezvousService.supprimerRendezVous(id);
    }

    //lister  les rv confirmer 
    @GetMapping("/confirmer")
    public List<RendezVous> listeRendezVousConfirmer(){
        return rendezvousService.getRendezVousConfirmer();
    }
}
