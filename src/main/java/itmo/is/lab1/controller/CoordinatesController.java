package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.CoordinatesDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.service.CoordinatesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {

    @Autowired
    private CoordinatesService coordinatesService;

    @PostMapping
    @Operation(description = "Добавляет координаты")
    public ResponseEntity<CoordinatesDTO> createCoordinates(@Valid @RequestBody CoordinatesDTO coordinatesDTO) {
        coordinatesDTO = coordinatesService.createCoordinates(coordinatesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(coordinatesDTO);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получает координаты по id")
    public ResponseEntity<CoordinatesDTO> getCoordinates(@PathVariable Integer id) throws DbException, NotEnoughAccessLevelToData {
        CoordinatesDTO coordinatesDTO = coordinatesService.findCoordinatesById(id);
        return ResponseEntity.status(HttpStatus.OK).body(coordinatesDTO);
    }

    @PutMapping
    @Operation(description = "Обновляет координаты")
    public ResponseEntity<String> updateCoordinates(@Valid @RequestBody CoordinatesDTO coordinatesDTO) throws NotEnoughAccessLevelToData, DbException {
        coordinatesService.updateCoordinates(coordinatesDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Successful updating of coordinates");
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаляет координаты по id")
    public ResponseEntity<String> deleteCoordinates(@PathVariable Integer id) throws NotEnoughAccessLevelToData, DbException {
        coordinatesService.deleteCoordinatesById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successful deleting");
    }
}
