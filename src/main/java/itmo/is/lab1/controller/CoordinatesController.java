package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/coordinates")
public class CoordinatesController {
    @PostMapping
    public ResponseEntity<CoordinatesDTO> addCoordinates(@RequestBody CoordinatesDTO coordinatesDTO){
        CoordinatesDTO stub = new CoordinatesDTO(123d,123);
        return ResponseEntity.status(HttpStatus.CREATED).body(stub);
    }
    @GetMapping
    public ResponseEntity<CoordinatesDTO> getCoordinates(Integer id){
        CoordinatesDTO stub = new CoordinatesDTO(123d,123);
        return ResponseEntity.status(HttpStatus.CREATED).body(stub);
    }

}
