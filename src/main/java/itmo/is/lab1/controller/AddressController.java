package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.AddressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/address")
public class AddressController {
    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO){
        AddressDTO stubAddressDTO = new AddressDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(stubAddressDTO);
    }
    @GetMapping
    public ResponseEntity<AddressDTO> getAddress(int id){
        AddressDTO stubAddressDTO = new AddressDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(stubAddressDTO);
    }
}
