package itmo.is.lab1.controller;

import io.swagger.v3.oas.annotations.Operation;
import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping()
    @Operation(description = "Получает информацию о рабочем по id")
    public ResponseEntity<WorkerDTO> getWorker(Integer id, @AuthenticationPrincipal User user) throws DbException, NotEnoughAccessLevelToData {
        WorkerDTO workerDTO = workerService.getWorkerById(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(workerDTO);
    }

    @PutMapping
    @Operation(description = "Обновляет информацию о рабочем")
    public ResponseEntity<String> updateWorker(@AuthenticationPrincipal User user, @Valid @RequestBody WorkerDTO workerDTO) throws NotEnoughAccessLevelToData, DbException {
        workerService.updateWorker(workerDTO, user);
        return ResponseEntity.status(HttpStatus.OK).body("Worker updated successfully");
    }

    @DeleteMapping()
    @Operation(description = "Удаляет запись о рабочем по id")
    public ResponseEntity<String> deleteWorker(Integer id, @AuthenticationPrincipal User user) throws NotEnoughAccessLevelToData, DbException {
        workerService.deleteWorker(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("Worker deleted successfully");
    }
}
