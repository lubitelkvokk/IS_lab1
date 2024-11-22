package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerDAO extends JpaRepository<Worker, Integer> {
    // JpaRepository provides methods for save, findById, findAll, and deleteById,
    // making explicit implementation unnecessary for standard CRUD operations.
    @Query(value = "SELECT avg_raiting()", nativeQuery = true)
    Double getAvgRaiting();

    @Query(value = "SELECT * FROM get_workers_by_personid(:person_id)", nativeQuery = true)
    List<Worker> getALlWorkersByPersonId(@Param("person_id") Integer id);

    @Query(value = "SELECT * FROM get_workers_by_substring_name(:name)", nativeQuery = true)
    List<Worker> getWorkersHaveNameStartsWith(@Param("name") String name);
}
