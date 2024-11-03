package itmo.is.lab1.dao;

import itmo.is.lab1.model.auth.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Find a user by username
    public Optional<User> findByUsername(String username) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Check if a user exists by username
    public boolean existsByUsername(String username) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    // Save or update a user
    @Transactional
    public User save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            entityManager.persist(user); // Save new user
        } else {
            user = entityManager.merge(user); // Update existing user
        }
        return user;
    }

    // Find a user by ID
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    // Retrieve all users
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // Delete a user by ID
    @Transactional
    public void deleteById(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
