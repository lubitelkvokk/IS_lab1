package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Organization;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Organization organization) {
        entityManager.persist(organization); // Сохраняет новый объект Organization в базе данных
    }

    public Organization findById(int id) {
        return entityManager.find(Organization.class, id); // Находит Organization по id
    }

    public List<Organization> findAll() {
        return entityManager.createQuery("SELECT o FROM Organization o", Organization.class)
                .getResultList(); // Возвращает список всех объектов Organization
    }

    @Transactional
    public void update(Organization organization) {
        entityManager.merge(organization); // Обновляет существующий объект Organization
    }

    @Transactional
    public void deleteById(int id) {
        Organization organization = findById(id);
        if (organization != null) {
            entityManager.remove(organization); // Удаляет объект Organization по id, если он существует
        }
    }
}
