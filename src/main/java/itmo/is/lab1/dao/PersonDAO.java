package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {
    // JpaRepository provides all necessary CRUD operations, including save, findById, findAll, and deleteById.
    // Custom methods can be added here if specific queries are needed.
}
