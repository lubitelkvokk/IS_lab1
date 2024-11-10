package itmo.is.lab1.dao;

import itmo.is.lab1.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    // Custom method to find a user by username
    Optional<User> findByUsername(String username);

    // Custom method to check if a user exists by username
    boolean existsByUsername(String username);

}
