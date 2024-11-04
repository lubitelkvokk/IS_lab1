package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.dao.AddressDAO;
import itmo.is.lab1.model.data.Address;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

//    private Address;

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@Valid @RequestBody AddressDTO addressDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
    }

    @GetMapping
    public ResponseEntity<AddressDTO> getAddress(Integer id) {
        AddressDTO stubAddressDTO = new AddressDTO();
        return ResponseEntity.status(HttpStatus.OK).body(stubAddressDTO);
    }
}
