package groupff.gmail.edu.sn.allodocteur.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.ConsultationDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Utilisateur;
import groupff.gmail.edu.sn.allodocteur.repositories.ConsultationRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository ;

    @Autowired
      private MedecinRepository medecinRepository ;

      @Autowired
      private PatientRepository patientRepository ;

    public List<Consultation> getConsultation() {
        return consultationRepository.findAll();
    }


    public Consultation createConsultation(ConsultationDTO consultationDTO, Utilisateur user) {
        // Récupération du Medecin à partir du DTO
        Optional<Medecin> medecinOptional = medecinRepository.findById(user.getId());
        System.out.println("medecin = " +medecinOptional.orElse(null));
        Optional<Patient> patientOptional = patientRepository.findById(consultationDTO.getIdPatient());

        System.out.println("patient = " +medecinOptional.orElse(null));
            // verifier si le medecin existe et est present et le patient est present et existe 
        if (medecinOptional.isPresent() && patientOptional.isPresent()) {
    
            // Si les deux sont présents, on peut créer la consultation
            Medecin medecin = medecinOptional.get();
            Patient patient = patientOptional.get();
    
            // Création de la consultation
            Consultation consultation = new Consultation();
            consultation.setMedecin(medecin);
            consultation.setPatient(patient);
            consultation.setAllergie(consultationDTO.getAllergie());
            consultation.setMotif(consultationDTO.getMotif());
            consultation.setAntecedent(consultationDTO.getAntecedent());
            consultation.setDate(consultationDTO.getDate());
            consultation.setDiagnostic(consultationDTO.getDiagnostic());
            consultation.setPoids(consultationDTO.getPoids());
            consultation.setTaille(consultationDTO.getTaille());
            consultation.setGroupeSanguin(consultationDTO.getGroupeSanguin());
            consultation.setProfession(consultationDTO.getProfession());
    
            // Enregistrement de la consultation
            consultationRepository.save(consultation);
    
            return consultation;
        } else {
            // Si le Medecin ou le Patient n'a pas été trouvé
            throw new EntityNotFoundException("entite introuvale");
        }
    }
    

    public Consultation modifierConsultation(Long consultationId, ConsultationDTO consultationDTO) {
        // Récupération de l'objet Consultation à partir de l'ID
        Optional<Consultation> consultationOptional = consultationRepository.findById(consultationId);

        if (consultationOptional.isPresent()) {
            Consultation consultationExistante = consultationOptional.get();

            // Mise à Jour des Attributs
            consultationExistante.setAllergie(consultationDTO.getAllergie());
            consultationExistante.setMotif(consultationDTO.getMotif());
            consultationExistante.setAntecedent(consultationDTO.getAntecedent());
            consultationExistante.setDate(consultationDTO.getDate());
            consultationExistante.setDiagnostic(consultationDTO.getDiagnostic());
            consultationExistante.setPoids(consultationDTO.getPoids());
            consultationExistante.setTaille(consultationDTO.getTaille());
            consultationExistante.setGroupeSanguin(consultationDTO.getGroupeSanguin());
            consultationExistante.setProfession(consultationDTO.getProfession());

            // Enregistrez la Consultation Mise à Jour
            return consultationRepository.save(consultationExistante);
        } else {
            throw new RuntimeException("Consultation non trouvée pour l'ID : " + consultationId);
        }
    }

    // A
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id) ;
    }
    
     
}
