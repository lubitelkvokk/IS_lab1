package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.model.WorkerService;
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
@RequestMapping("/worker")
@Validated
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    @Operation(description = "Создает нового рабочего")
    public ResponseEntity<WorkerDTO> createWorker(@AuthenticationPrincipal User user, @RequestBody WorkerDTO workerDTO) throws DbException {
        System.out.println(workerDTO.getId());
        WorkerDTO createdWorker = workerService.createWorker(workerDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorker);
    }

    @GetMapping
    @Operation(description = "Принимает параметры page, size для выполнения пагинации")
    public ResponseEntity<Page<WorkerDTO>> getAllWorker(Pageable pageable) {
        Page<WorkerDTO> workers = workerService.
                getNWorkersFromPage(pageable);
        return ResponseEntity.ok(workers);
    }
    @GetMapping("/{id}")
    @Operation(description = "Получает информацию о рабочем по id")
    public ResponseEntity<WorkerDTO> getWorker(@PathVariable Integer id) throws DbException, NotEnoughAccessLevelToData {
        WorkerDTO workerDTO = workerService.getWorkerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(workerDTO);
    }

    @PutMapping
    @Operation(description = "Обновляет информацию о рабочем")
    public ResponseEntity<String> updateWorker(@AuthenticationPrincipal User user, @Valid @RequestBody WorkerDTO workerDTO) throws NotEnoughAccessLevelToData, DbException {
        System.out.println(workerDTO.getOrganizationId());
        workerService.updateWorker(workerDTO, user);
        return ResponseEntity.status(HttpStatus.OK).body("Worker updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаляет запись о рабочем по id")
    public ResponseEntity<String> deleteWorker(@PathVariable Integer id, @AuthenticationPrincipal User user) throws NotEnoughAccessLevelToData, DbException {
        workerService.deleteWorker(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Worker deleted successfully");
    }
}
