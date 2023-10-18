package groupff.gmail.edu.sn.allodocteur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.services.DossierMedicaleService;

@RestController
@RequestMapping("/api/dossiermedicales")
public class DossierMedicaleController {
    @Autowired
    private DossierMedicaleService medicaleService ;


    

    public DossierMedicaleService getMedicaleService() {
        return medicaleService;
    }

    public void setMedicaleService(DossierMedicaleService medicaleService) {
        this.medicaleService = medicaleService;
    }

    
    
}
