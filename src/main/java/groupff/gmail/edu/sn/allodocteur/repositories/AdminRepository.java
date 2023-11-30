package groupff.gmail.edu.sn.allodocteur.repositories;

import groupff.gmail.edu.sn.allodocteur.entites.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin , Long> {
    Optional<Admin> findByEmail(String email);
}
