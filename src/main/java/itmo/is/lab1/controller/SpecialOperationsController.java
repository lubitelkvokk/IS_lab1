package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.service.SpecialOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/special")
public class SpecialOperationsController {
    @Autowired
    private SpecialOperationsService specialOperationsService;

    @GetMapping("/avg_raiting")
    public ResponseEntity<Double> avgRaiting() {
        Double result = specialOperationsService.getAvgAllWorkersRaiting();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/workers_by_personid/{id}")
    public ResponseEntity<List<WorkerDTO>> workersByPersonid(@PathVariable int id) {
        return ResponseEntity
                .ok()
                .body(specialOperationsService.getAllWorkersByPersonId(id));
    }

    @GetMapping("/workers_starting_by_name/{name}")
    public ResponseEntity<List<WorkerDTO>> workersStartingByName(@PathVariable String name) {
        return ResponseEntity
                .ok()
                .body(specialOperationsService.getWorkersHaveNameStartsWith(name));
    }
}
