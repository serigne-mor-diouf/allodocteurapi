package groupff.gmail.edu.sn.allodocteur.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import groupff.gmail.edu.sn.allodocteur.dao.PrescriptionDTO;

import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PrescriptionRepository;
import jakarta.persistence.EntityNotFoundException;


@Service
public class PrescriptionService {
   
@Autowired
private PrescriptionRepository prescriptionRepository ;
@Autowired
private PatientRepository  patientRepository ;
@Autowired
private MedecinRepository medecinRepository ;

public Prescription createPrescription(PrescriptionDTO prescriptionDTO , Utilisateur user ){
    Optional<Medecin> medecinOptional =  medecinRepository.findById(user.getId());
    Optional<Patient> patieOptional = patientRepository.findById(prescriptionDTO.getIdPatient()) ;

    if(medecinOptional.isPresent() && patieOptional.isPresent()){
        Medecin medecin = medecinOptional.get();
        Patient patient = patieOptional.get();
        Prescription prescription = new Prescription() ;
        prescription.setMedecin(medecin);
        prescription.setPatient(patient);
        //prescription.setDate(prescriptionDTO.getDate());
        prescription.setDescription(prescriptionDTO.getDescription()) ;
        prescription.setMedicament(prescriptionDTO.getMedicament()) ;
        prescriptionRepository.save(prescription) ;
        return prescription ;   
    }
     else {
        throw new EntityNotFoundException("entite introuvable");
        }

    }
   
        //modifier une prescripion
        public Prescription modifierPrescription(Long id , PrescriptionDTO prescriptionDTO) {
               Optional<Prescription> prescripionOptional = prescriptionRepository.findById(id);

            if (prescripionOptional.isPresent()) {

                Prescription prescriptionExistante = prescripionOptional.get();

                prescriptionExistante.setDate(prescriptionDTO.getDate());
                prescriptionExistante.setDescription(prescriptionDTO.getDescription());
                prescriptionExistante.setMedicament(prescriptionDTO.getMedicament());
    
                // Enregistrez la prescription mise à jour dans le repository
                return prescriptionRepository.save(prescriptionExistante);
               
            }    
        else {
            throw new RuntimeException("Consultation non trouvée pour l'ID : " + id);
        }
        }


        public void supprimerPrescription(Long id) {
            prescriptionRepository.deleteById(id);
        }


        public List<Prescription> getPrescription() {
            return prescriptionRepository.findAll();
        }
       

    //rechercher un prescription dont on connait son identifiant
    public Prescription getPrescriptionById(Long id){
        Optional<Prescription> optional = prescriptionRepository.findById(id);
        return optional.orElse(null) ;

    }
}
