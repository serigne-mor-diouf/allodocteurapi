package groupff.gmail.edu.sn.allodocteur.controllers;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupff.gmail.edu.sn.allodocteur.entites.Consultation;
import groupff.gmail.edu.sn.allodocteur.entites.Medecin;
import groupff.gmail.edu.sn.allodocteur.entites.Patient;
import groupff.gmail.edu.sn.allodocteur.entites.Prescription;
import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
import groupff.gmail.edu.sn.allodocteur.repositories.MedecinRepository;
import groupff.gmail.edu.sn.allodocteur.repositories.PatientRepository;

@RestController
@RequestMapping("/api")
public class ApiController  {
    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/mes-informations")
    public ResponseEntity<?> getMesInformations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Medecin> optionalMedecin = medecinRepository.findByEmail(username);
        Optional<Patient> optionalPatient = patientRepository.findByEmail(username);

        if (optionalMedecin.isPresent()) {
            Medecin medecin = optionalMedecin.get();
            // Filtrer les prescriptions, rendez-vous et consultations effectués par le médecin
            List<Prescription> prescriptions = medecin.getPrescriptions().stream()
                    .filter(prescription -> prescription.getMedecin().equals(medecin))
                    .collect(Collectors.toList());

            List<RendezVous> rendezVous = medecin.getRendezVous().stream()
                    .filter(rendezvous -> rendezvous.getMedecin().equals(medecin))
                    .collect(Collectors.toList());

            List<Consultation> consultations = medecin.getConsultations().stream()
                    .filter(consultation -> consultation.getMedecin().equals(medecin))
                    .collect(Collectors.toList());

            // Autres informations spécifiques au médecin
            return ResponseEntity.ok(Map.of("prescriptions", prescriptions, "rendezVous", rendezVous, "consultations", consultations));
        } else if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            // Filtrer les prescriptions, rendez-vous et consultations associés au patient
            List<Prescription> prescriptions = patient.getPrescriptions().stream()
                    .filter(prescription -> prescription.getPatient().equals(patient))
                    .collect(Collectors.toList());

            List<RendezVous> rendezVous = patient.getRendezVous().stream()
                    .filter(rendezvous -> rendezvous.getPatient().equals(patient))
                    .collect(Collectors.toList());

            List<Consultation> consultations = patient.getConsultations().stream()
                    .filter(consultation -> consultation.getPatient().equals(patient))
                    .collect(Collectors.toList());

            // Autres informations spécifiques au patient
            return ResponseEntity.ok(Map.of("prescriptions", prescriptions, "rendezVous", rendezVous, "consultations", consultations));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé avec le nom d'utilisateur: " + username);
        }
    }
}
