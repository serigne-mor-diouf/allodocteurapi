package groupff.gmail.edu.sn.allodocteur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.services.PlaningService;

@RestController
@RequestMapping("/api/plannings")
public class PlaningController {
    @Autowired
    private PlaningService planingService ;
    
}
