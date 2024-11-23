package itmo.is.lab1.controller;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.service.SpecialOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/change_worker_organization")
    public void changeWorkerOrganiation(Integer worker_id, Integer organization_id) throws DbException {
        specialOperationsService.changeWorkerOrganization(worker_id, organization_id);
    }

    @PostMapping("/index_worker_salary")
    public ResponseEntity<Double> indexWorkerSalary(Integer worker_id, Double coefficient) throws DbException {
        return ResponseEntity.ok(
                specialOperationsService.indexWorkerSalary(worker_id, coefficient)
        );
    }
}
