package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.LocationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/location")
public class LocationController {

    @PostMapping
    public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO locationDTO) {
        // Stub for demonstration purposes
        LocationDTO stub = new LocationDTO(123.45f, 200, "Sample Location");
        return ResponseEntity.status(HttpStatus.CREATED).body(stub);
    }

    @GetMapping
    public ResponseEntity<LocationDTO> getLocation(@RequestParam int id) {
        // Stub for demonstration purposes
        LocationDTO stub = new LocationDTO(123.45f, 200, "Sample Location");
        return ResponseEntity.status(HttpStatus.OK).body(stub);
    }
}
