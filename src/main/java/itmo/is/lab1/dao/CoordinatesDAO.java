package itmo.is.lab1.dao;

import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.model.data.Coordinates;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoordinatesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Coordinates save(Coordinates coordinates) {
        entityManager.persist(coordinates); // Сохраняет новый объект Coordinates в базе данных
        return coordinates;
    }

    public Coordinates findById(int id) {
        return entityManager.find(Coordinates.class, id); // Находит координаты по id
    }

    public List<Coordinates> findAll() {
        return entityManager.createQuery("SELECT c FROM Coordinates c", Coordinates.class)
                .getResultList(); // Возвращает список всех координат
    }

    @Transactional
    public void update(Coordinates coordinates) throws DbException {
        try {
            entityManager.merge(coordinates); // Обновляет существующий объект Coordinates
        } catch (IllegalArgumentException e){
            throw new DbException("Such " + coordinates.getClass().getName() + " isn't in database");
        }
    }

    @Transactional
    public void deleteById(int id) {
        Coordinates coordinates = findById(id);
        if (coordinates != null) {
            entityManager.remove(coordinates); // Удаляет объект Coordinates по id, если он существует
        }
    }
}
