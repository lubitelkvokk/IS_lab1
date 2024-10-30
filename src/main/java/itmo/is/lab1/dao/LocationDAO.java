package itmo.is.lab1.dao;

import itmo.is.lab1.model.Location;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Location location) {
        entityManager.persist(location); // Сохраняет новый объект Location в базе данных
    }

    public Location findById(int id) {
        return entityManager.find(Location.class, id); // Находит Location по id
    }

    public List<Location> findAll() {
        return entityManager.createQuery("SELECT l FROM Location l", Location.class)
                .getResultList(); // Возвращает список всех объектов Location
    }

    @Transactional
    public void update(Location location) {
        entityManager.merge(location); // Обновляет существующий объект Location
    }

    @Transactional
    public void deleteById(int id) {
        Location location = findById(id);
        if (location != null) {
            entityManager.remove(location); // Удаляет объект Location по id, если он существует
        }
    }
}
