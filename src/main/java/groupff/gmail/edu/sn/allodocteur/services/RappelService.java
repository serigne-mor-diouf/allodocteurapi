// package groupff.gmail.edu.sn.allodocteur.services;
// import java.time.LocalDateTime;

// import java.time.temporal.ChronoUnit;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import groupff.gmail.edu.sn.allodocteur.entites.RendezVous;
// import groupff.gmail.edu.sn.allodocteur.notifications.NotificationService;
// import groupff.gmail.edu.sn.allodocteur.repositories.RendezvousRepository;


// @Service
// public class RappelService {

//     @Autowired
//     private RendezvousRepository rendezVousRepository;

//     @Autowired
//     private NotificationService notificationService;

//     // Méthode pour envoyer des rappels quotidiens
//     @Scheduled(cron = "0 0 09 * * ?") // Exécuté tous les jours à 9h
//     public void envoyerRappelsQuotidiens() {
//         // Récupérer les rendez-vous qui ont 
//         //lieu dans les prochaines 24 heures
//         List<RendezVous> rendezVousAujourdhui = rendezVousRepository.findByDateBetween(
//             LocalDateTime.now(),
//             LocalDateTime.now().plusHours(24)
//         );

//         // Filtrer les rendez-vous avec une différence de 24 heures entre date de création et date du rendez-vous
//         List<RendezVous>rendezVousAvecDiff24h = rendezVousAujourdhui.stream()
//            // .filter(rv -> ChronoUnit.HOURS.between(rv.getDateCreation(), rv.getDate()) )
//             .collect(Collectors.toList());

//         // Envoyer des rappels aux patients pour ces rendez-vous
//         for (RendezVous rv : rendezVousAvecDiff24h) {
//             notificationService.envoyerRappelPatient(rv);
//         }
//     }

// }
