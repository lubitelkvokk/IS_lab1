package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.dao.AddressDAO;
import itmo.is.lab1.model.data.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressDAO addressDAO;

    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setZipCode(addressDTO.getZipCode());
        addressDAO.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
    }

    @GetMapping
    public ResponseEntity<AddressDTO> getAddress(Integer id) {
        AddressDTO stubAddressDTO = new AddressDTO();
        return ResponseEntity.status(HttpStatus.OK).body(stubAddressDTO);
    }
}
