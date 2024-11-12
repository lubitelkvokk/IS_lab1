package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressDTO = addressService.createAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
    }

    @GetMapping
    public ResponseEntity<AddressDTO> getAddress(Integer id) throws NotEnoughAccessLevelToData, DbException {
        System.out.println(id);
        // Выполнение основной логики контроллера
        AddressDTO addressDTO = addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
    }
    @PutMapping
    public ResponseEntity<String> updateAddress(@Valid @RequestBody AddressDTO addressDTO) throws NotEnoughAccessLevelToData {

        // Выполнение основной логики контроллера
        addressService.updateAddress(addressDTO);

        return ResponseEntity.status(HttpStatus.OK).body("successfull updating of address");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAddress(AddressDTO addressDTO) throws NotEnoughAccessLevelToData {
        addressService.deleteAddress(addressDTO);

        return ResponseEntity.status(HttpStatus.OK).body("successfull deleting");
    }
}
