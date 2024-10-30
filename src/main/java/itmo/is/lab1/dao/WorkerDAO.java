package itmo.is.lab1.dao;

import itmo.is.lab1.model.Worker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Worker worker) {
        entityManager.persist(worker); // Сохраняет новый объект Worker в базе данных
    }

    public Worker findById(int id) {
        return entityManager.find(Worker.class, id); // Находит Worker по id
    }

    public List<Worker> findAll() {
        return entityManager.createQuery("SELECT w FROM Worker w", Worker.class)
                .getResultList(); // Возвращает список всех объектов Worker
    }

    @Transactional
    public void update(Worker worker) {
        entityManager.merge(worker); // Обновляет существующий объект Worker
    }

    @Transactional
    public void deleteById(int id) {
        Worker worker = findById(id);
        if (worker != null) {
            entityManager.remove(worker); // Удаляет объект Worker по id, если он существует
        }
    }
}
