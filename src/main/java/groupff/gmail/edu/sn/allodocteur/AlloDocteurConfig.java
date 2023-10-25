// package groupff.gmail.edu.sn.allodocteur;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.bcrypt.BCrypt;

// import groupff.gmail.edu.sn.allodocteur.dao.UserRegistrationDTO;
// import groupff.gmail.edu.sn.allodocteur.entites.Admin;
// import groupff.gmail.edu.sn.allodocteur.repositories.AdminRepository;

// @Configuration
// public class AlloDocteurConfig {

//     @Bean
//     CommandLineRunner commandLineRunner(AdminRepository adminRepository){
//         UserRegistrationDTO registrationDTO  = new UserRegistrationDTO();
//     String hashedPaString= bcrypt.hashpw(registrationDTO.getPassword(), BCrypt.gensalt());

//         return args ->{
//             Admin admin = new Admin();

//             admin.setNom("Diouf");
//             admin.setEmail("dioufserigne@gmail.com");
//             admin.setAdresse("Thies");
//             admin.setAge(22);
//             admin.setPrenom("Serigne Mor") ;
//             admin.setProfil("admin") ;
//             admin.setSexe("M");
//             admin.setTelephone("778653628");
//             admin.setPassword(hashedPaString);
//             System.out.println("enregistrement de l'admin"+admin) ;
//             adminRepository.save(admin) ;
//         } ;
//     }
// }
