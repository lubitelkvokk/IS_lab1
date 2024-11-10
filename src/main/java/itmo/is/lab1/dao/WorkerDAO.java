package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerDAO extends JpaRepository<Worker, Integer> {
    // JpaRepository provides methods for save, findById, findAll, and deleteById,
    // making explicit implementation unnecessary for standard CRUD operations.
}
