package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDAO extends JpaRepository<Location, Integer> {
    // JpaRepository provides methods for save, findById, findAll, and deleteById,
    // so no additional implementation is needed for basic CRUD operations.
}
