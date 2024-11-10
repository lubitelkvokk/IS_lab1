package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Coordinates;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesDAO extends JpaRepository<Coordinates, Integer> {
    // All basic CRUD methods like save, findById, findAll, and deleteById are provided by JpaRepository.
    // Custom exception handling can be implemented at the service layer if necessary.

    @Transactional
    default Coordinates updateOrInsert(Coordinates entity) {
        return save(entity);
    }
}
