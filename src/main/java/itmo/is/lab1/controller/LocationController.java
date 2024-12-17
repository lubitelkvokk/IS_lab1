package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.LocationDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.model.LocationService;
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
@RequestMapping("/location")
@Validated
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    @Operation(description = "Создает новое местоположение")
    public ResponseEntity<LocationDTO> createLocation(@AuthenticationPrincipal User user, @Valid @RequestBody LocationDTO locationDTO) {
        LocationDTO createdLocation = locationService.createLocation(locationDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @GetMapping
    @Operation(description = "Принимает параметры page, size для выполнения пагинации")
    public ResponseEntity<Page<LocationDTO>> getAllLocations(Pageable pageable) {
        Page<LocationDTO> locations = locationService.
                getNLocationStartFromPage(pageable);
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получает информацию о местоположении по id")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Integer id, @AuthenticationPrincipal User user) throws DbException, NotEnoughAccessLevelToData {
        LocationDTO locationDTO = locationService.getLocationById(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(locationDTO);
    }

    @PutMapping
    @Operation(description = "Обновляет информацию о местоположении")
    public ResponseEntity<String> updateLocation(@AuthenticationPrincipal User user, @Valid @RequestBody LocationDTO locationDTO) throws NotEnoughAccessLevelToData, DbException {
        locationService.updateLocation(locationDTO, user);
        return ResponseEntity.status(HttpStatus.OK).body("Location updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаляет местоположение по id")
    public ResponseEntity<String> deleteLocation(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotEnoughAccessLevelToData, DbException {
        locationService.deleteLocation(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Location deleted successfully");
    }
}
