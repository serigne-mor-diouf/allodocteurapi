package groupff.gmail.edu.sn.allodocteur.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import groupff.gmail.edu.sn.allodocteur.dao.ConsultationDTO;
import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.repositories.ConsultationRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;

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


    //passer une consultation a un pateint
    public Consultation createConsultation(ConsultationDTO consultationDTO){
        Optional<Medecin> medecinOptional = medecinRepository.findById((consultationDTO.getMedecin().getId())) ;        
        Optional<Patient> patientOptional = patientRepository.findById(consultationDTO.getPatient().getId());
        if(medecinOptional.isPresent() && patientOptional.isPresent()){
            Medecin medecin = medecinOptional.get();
            Patient patient = patientOptional.get();

            Consultation consultation = new Consultation();
            consultation.setMedecin(medecin);
            consultation.setPatient(patient);
            consultation.setAllergie(consultationDTO.getAllergie());
            consultation.setMotif(consultationDTO.getMotif());
            consultation.setAntecedent(consultationDTO.getAntecedent());
            consultation.setDate(consultationDTO.getDate()) ;
            consultation.setDiagnostic(consultationDTO.getDiagnostic());
            consultation.setPoids(consultationDTO.getPoids()) ;
            consultation.setTaille(consultationDTO.getTaille()) ;
            consultation.setGroupeSanguin(consultationDTO.getGroupeSanguin()) ;
            consultation.setProfession(consultationDTO.getProfession());
            // Enregistrez la consultation
            consultationRepository.save(consultation);

        return consultation;
       } else {
        throw new RuntimeException("Médecin ou patient non trouvé pour les ID : " + consultationDTO.getMedecin().getId() + ", " + consultationDTO.getPatient().getId());
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
