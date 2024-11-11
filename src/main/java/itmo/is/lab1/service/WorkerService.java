package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.dao.WorkerDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Worker;
import itmo.is.lab1.objMapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Date;

@Service
public class WorkerService {

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private WorkerMapper workerMapper;

    public WorkerDTO createWorker(WorkerDTO workerDTO, User user) {
        workerDTO.setUserId(user.getId()); // Установка userId в DTO
        Worker worker = workerMapper.toEntity(workerDTO);
        worker.setUser(user); // Привязываем пользователя к сущности Worker
        worker.setCreationDate(new Date()); // Устанавливаем текущую дату как дату создания
        Worker savedWorker = workerDAO.save(worker);
        return workerMapper.toDTO(savedWorker);
    }

    public WorkerDTO getWorkerById(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Worker worker = workerDAO.findById(id).orElseThrow(() ->
                new DbException("Worker not found with id = %d".formatted(id)));

        // Проверка прав доступа
        if (!Objects.equals(worker.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to access someone else's data");
        }

        return workerMapper.toDTO(worker);
    }

    public void updateWorker(WorkerDTO workerDTO, User user) throws NotEnoughAccessLevelToData, DbException {
        Worker worker = workerDAO.findById(workerDTO.getId()).orElseThrow(() ->
                new DbException("Worker not found with id = %d".formatted(workerDTO.getId())));

        // Проверка прав доступа
        if (!Objects.equals(worker.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to modify someone else's data");
        }

        // Обновление полей
        worker.setName(workerDTO.getName());
        worker.setCoordinates(workerDTO.getCoordinates());
        worker.setOrganization(workerDTO.getOrganization());
        worker.setSalary(workerDTO.getSalary());
        worker.setRating(workerDTO.getRating());
        worker.setEndDate(workerDTO.getEndDate());
        worker.setPosition(workerDTO.getPosition());
        worker.setStatus(workerDTO.getStatus());
        worker.setPerson(workerDTO.getPerson());

        workerDAO.save(worker);
    }

    public void deleteWorker(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Worker worker = workerDAO.findById(id).orElseThrow(() ->
                new DbException("Worker not found with id = %d".formatted(id)));

        // Проверка прав доступа
        if (!Objects.equals(worker.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to delete someone else's data");
        }

        workerDAO.delete(worker);
    }
}
