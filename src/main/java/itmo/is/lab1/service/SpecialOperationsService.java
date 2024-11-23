package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.dao.WorkerDAO;
import itmo.is.lab1.exceptionHandler.DbException;
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

    public void changeWorkerOrganization(Integer worker_id, Integer organization_id) throws DbException {
        try {
            workerDAO.changeWorkerOrganization(worker_id, organization_id);
        } catch (Exception e) {
            if (e.getMessage().contains("Nonexistent organization ID")) {
                throw new DbException("Nonexistent organization ID --> " + organization_id);
            } else if (e.getMessage().contains("Nonexistent worker ID")) {
                throw new DbException("Nonexistent worker ID --> " + worker_id);
            }
        }
    }

    public Double indexWorkerSalary(Integer worker_id, Double coefficient) throws DbException {
        try {
            return workerDAO.indexSalaryToWorkerByCoefficient(worker_id, coefficient);
        } catch (Exception e) {
            if (e.getMessage().contains("Nonexistent worker ID")) {
                throw new DbException("Nonexistent worker ID --> " + worker_id);
            }
            throw new DbException("Unknown db error");
        }
    }
}