package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.AddressDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

//    @PersistenceContext
//    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO stubAddressDTO = new AddressDTO();
//        entityManager.persist(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(stubAddressDTO);
    }

    @GetMapping
    public ResponseEntity<AddressDTO> getAddress(int id) {
        AddressDTO stubAddressDTO = new AddressDTO();
        return ResponseEntity.status(HttpStatus.OK).body(stubAddressDTO);
    }
}
