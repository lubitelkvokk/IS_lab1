package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.dao.WorkerDAO;
import itmo.is.lab1.model.data.Worker;
import itmo.is.lab1.objMapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialOperationsService {
    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private WorkerMapper workerMapper;

    public Double getAvgAllWorkersRaiting() {
        return workerDAO.getAvgRaiting();
    }

    public List<WorkerDTO> getAllWorkersByPersonId(Integer personId) {
        return workerDAO.getALlWorkersByPersonId(personId).stream().map(workerMapper::toDTO).toList();
    }

    public List<WorkerDTO> getWorkersHaveNameStartsWith(String name) {
        return workerDAO.getWorkersHaveNameStartsWith(name)
                .stream()
                .map(workerMapper::toDTO)
                .toList();
    }
}
