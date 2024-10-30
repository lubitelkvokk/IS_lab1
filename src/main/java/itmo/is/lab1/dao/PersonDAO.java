package itmo.is.lab1.dao;

import itmo.is.lab1.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Person person) {
        entityManager.persist(person); // Сохраняет новый объект Person в базе данных
    }

    public Person findById(int id) {
        return entityManager.find(Person.class, id); // Находит Person по id
    }

    public List<Person> findAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList(); // Возвращает список всех объектов Person
    }

    @Transactional
    public void update(Person person) {
        entityManager.merge(person); // Обновляет существующий объект Person
    }

    @Transactional
    public void deleteById(int id) {
        Person person = findById(id);
        if (person != null) {
            entityManager.remove(person); // Удаляет объект Person по id, если он существует
        }
    }
}
