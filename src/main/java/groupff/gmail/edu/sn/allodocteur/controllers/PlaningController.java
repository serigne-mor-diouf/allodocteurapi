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
import groupff.gmail.edu.sn.allodocteur.entites.Planing;
import groupff.gmail.edu.sn.allodocteur.services.PlaningService;

@RestController
@RequestMapping("/api/plannings")
public class PlaningController {
    @Autowired
    private PlaningService planingService ;

    @GetMapping
    public List<Planing> getPlanings() {
        return planingService.getPlanings();
    }

    @PostMapping
    public ResponseEntity<Planing> createPlaning(@RequestBody MedecinDTO medecin, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        Planing createdPlaning = planingService.createPlaning(medecin, date);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlaning);
    }


    //supprimer unplaning
    @DeleteMapping("/{id}")
    public void deletePlanning(@PathVariable Long id){
        planingService.deletePlage(id);
    }


    // rechercher un planning
    // @GetMapping("/nomplaning")
    // public List<Planing> rechercherPlageHorairemedecin(@RequestParam String nomMedecin, @RequestParam String prenomMedecin, @RequestParam Date date){
    //     return planingService.rechercherDisponiblesMedecin(nomMedecin , prenomMedecin , date);
    // }

    // modifier un planning
    @PutMapping("/{planing}")
    public Planing updatePlaning(@RequestBody Planing planing){
        return planingService.updatePlaning(planing) ;
    }
}
