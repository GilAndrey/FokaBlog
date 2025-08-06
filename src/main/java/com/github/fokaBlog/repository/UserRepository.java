package com.github.fokaBlog.repository;

import com.github.fokaBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Search for Email in DataBase
    Optional<User> findByEmail(String email);

    // Looks if exists the email in DataBase
    boolean existsByEmail(String email);
}
