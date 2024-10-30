package itmo.is.lab1.dao;

import itmo.is.lab1.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Address address) {
        entityManager.persist(address); // Добавляет новый адрес в базу данных

    }

    public Address findById(int id) {
        return entityManager.find(Address.class, id); // Находит адрес по id
    }

    public List<Address> findAll() {
        return entityManager.createQuery("SELECT a FROM Address a", Address.class)
                .getResultList(); // Возвращает список всех адресов
    }

    @Transactional
    public void update(Address address) {
        entityManager.merge(address); // Обновляет существующий адрес
    }

    @Transactional
    public void deleteById(int id) {
        Address address = findById(id);
        if (address != null) {
            entityManager.remove(address); // Удаляет адрес по id, если он существует
        }
    }
}
