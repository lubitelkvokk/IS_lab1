package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.OrganizationDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.OrganizationService;
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
@RequestMapping("/organization")
@Validated
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    @Operation(description = "Создает новую организацию")
    public ResponseEntity<OrganizationDTO> createOrganization(@AuthenticationPrincipal User user, @Valid @RequestBody OrganizationDTO organizationDTO) throws DbException {
        OrganizationDTO createdOrganization = organizationService.createOrganization(organizationDTO, user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrganization);
    }


    @GetMapping
    @Operation(description = "Принимает параметры page, size для выполнения пагинации")
    public ResponseEntity<Page<OrganizationDTO>> getAllOrganizations(Pageable pageable) {
        Page<OrganizationDTO> organizations = organizationService.
                getNOrganizationStartFromPage(pageable);
        return ResponseEntity.ok(organizations);
    }

    @GetMapping("/{id}")
    @Operation(description = "Получает информацию об организации по id")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Integer id) throws DbException, NotEnoughAccessLevelToData {
        OrganizationDTO organizationDTO = organizationService.getOrganizationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(organizationDTO);
    }

    @PutMapping
    @Operation(description = "Обновляет информацию об организации")
    public ResponseEntity<String> updateOrganization(@AuthenticationPrincipal User user, @Valid @RequestBody OrganizationDTO organizationDTO) throws NotEnoughAccessLevelToData, DbException {
        organizationService.updateOrganization(organizationDTO, user);
        return ResponseEntity.status(HttpStatus.OK).body("Organization updated successfully");
    }

    @DeleteMapping()
    @Operation(description = "Удаляет организацию по id")
    public ResponseEntity<String> deleteOrganization(Integer id, @AuthenticationPrincipal User user) throws NotEnoughAccessLevelToData, DbException {
        organizationService.deleteOrganization(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Organization deleted successfully");
    }
}
