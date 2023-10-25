package groupff.gmail.edu.sn.allodocteur.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.dao.MedecinDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.services.MedecinService;
import groupff.gmail.edu.sn.allodocteur.services.PatientService;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService ;
    @Autowired
    private PatientService patientService ;

    @PostMapping
    public ResponseEntity<Long> saveMedecin( @RequestBody MedecinDTO medecinDTO){
        System.out.println("enregistrement MdecinDto = "+medecinDTO);
        Medecin  medecin = medecinService.savMedecin(medecinDTO);
           // enregistre un patient en recuperant la requette atendue ici 200
        return ResponseEntity.status(HttpStatus.CREATED).body(medecin.getId()) ;
    }
    // lister medecin
    @GetMapping
    public List<Medecin> getMedecins(){
        return patientService.getMedecins();
    }

    //
}

