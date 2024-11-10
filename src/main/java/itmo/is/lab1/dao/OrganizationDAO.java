package itmo.is.lab1.dao;

import itmo.is.lab1.model.data.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationDAO extends JpaRepository<Organization, Integer> {
    // JpaRepository provides methods for save, findById, findAll, and deleteById,
    // so no additional implementation is needed for standard CRUD operations.
}
