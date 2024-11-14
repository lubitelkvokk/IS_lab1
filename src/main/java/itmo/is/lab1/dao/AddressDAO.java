package itmo.is.lab1.dao;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.model.data.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressDAO extends JpaRepository<Address, Integer> {
    // No need to manually define methods for save, findById, findAll, or deleteById.
    // JpaRepository already includes:
    // - save(Address address) for both saving and updating
    // - findById(Integer id) to retrieve an entity by ID
    // - findAll() to list all entities
    // - deleteById(Integer id) to delete by ID

    Page<Address> findAll(Pageable pageable);
}
