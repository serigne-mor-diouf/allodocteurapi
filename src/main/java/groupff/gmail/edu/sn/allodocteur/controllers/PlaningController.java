package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import groupff.gmail.edu.sn.allodocteur.dao.PlanningDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Planing;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.services.PlaningService;

@RestController
@RequestMapping("/api/plannings")
public class PlaningController {
    @Autowired
    private PlaningService planingService ;

    @Autowired
    private MedecinRepository medecinRepository;

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping
    public List<Planing> getPlanings() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Medecin> optionalMedecin = medecinRepository.findByEmail(username);

        if (optionalMedecin.isPresent()) {
            Medecin medecin = optionalMedecin.get();
            // Filtrer les prescriptions, rendez-vous et consultations effectués par le médecin
            List<Planing> planings = medecin.getPlanings().stream()
                    .filter(planing -> planing.getMedecin().equals(medecin))
                    .collect(Collectors.toList());
                        return planings;
            }else{
              
             return planingService.getPlanings();
            }
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping
    public ResponseEntity<Planing> createPlanning(@RequestBody PlanningDTO planningDTO , 
    @AuthenticationPrincipal  Utilisateur user) {
        Planing planning = planingService.createPlaning(planningDTO , user);
        if (planning != null) { 
            return ResponseEntity.status(HttpStatus.CREATED).body(planning);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //supprimer unplaning
    @PreAuthorize("hasAuthority('MEDECIN')")
    @DeleteMapping("/{id}")
    public void deletePlanning(@PathVariable Long id){
        planingService.deletePlage(id);
    }

    // modifier un planning
    @PreAuthorize("hasAuthority('MEDECIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Planing> updatPlanning(@PathVariable Long id, @RequestBody PlanningDTO planningDTO) {
        Planing updatepPlaning = planingService.updatePlanning(id, planningDTO);
        if (updatepPlaning != null) {
            return ResponseEntity.ok(updatepPlaning);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
