package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.model.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO,
                                                    @AuthenticationPrincipal User user) throws DbException {
        addressDTO = addressService.createAddress(addressDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
    }
    @GetMapping
    @Operation(description = "Принимает параметры page, size для выполнения пагинации")
    public ResponseEntity<Page<AddressDTO>> getAllAddresses(Pageable pageable, @RequestParam(required = false) String streetFilter) {
        System.out.println(streetFilter);
        Page<AddressDTO> addresses = addressService.getNAddressesStartFromPage(pageable, streetFilter);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Integer id) throws NotEnoughAccessLevelToData, DbException {
        AddressDTO addressDTO = addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressDTO);
    }

    @PutMapping
    public ResponseEntity<String> updateAddress(@Valid @RequestBody AddressDTO addressDTO) throws NotEnoughAccessLevelToData, DbException {

        // Выполнение основной логики контроллера
        addressService.updateAddress(addressDTO);

        return ResponseEntity.status(HttpStatus.OK).body("successfull updating of address");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) throws NotEnoughAccessLevelToData {
        addressService.deleteAddress(id);

        return ResponseEntity.status(HttpStatus.OK).body("successfull deleting");
    }
}
