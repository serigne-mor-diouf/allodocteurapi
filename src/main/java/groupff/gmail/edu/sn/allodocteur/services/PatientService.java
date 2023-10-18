package groupff.gmail.edu.sn.allodocteur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.PatientDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository ;

public Patient savePatient(PatientDTO patientDTO) {

    return patientRepository.save(patientDTO.toPatient());
}


    

}


