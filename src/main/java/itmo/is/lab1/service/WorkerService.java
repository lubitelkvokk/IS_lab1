package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.dao.CoordinatesDAO;
import itmo.is.lab1.dao.OrganizationDAO;
import itmo.is.lab1.dao.PersonDAO;
import itmo.is.lab1.dao.WorkerDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Coordinates;
import itmo.is.lab1.model.data.Organization;
import itmo.is.lab1.model.data.Person;
import itmo.is.lab1.model.data.Worker;
import itmo.is.lab1.objMapper.WorkerMapper;
import itmo.is.lab1.permission.PermissionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class WorkerService {

    @Autowired
    private WorkerDAO workerDAO;
    @Autowired
    private OrganizationDAO organizationDAO;
    @Autowired
    private CoordinatesDAO coordinatesDAO;
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private PermissionChecker permissionChecker;

    public WorkerDTO createWorker(WorkerDTO workerDTO, User user) throws DbException {
        Worker worker = workerMapper.toEntity(workerDTO);
        worker.setUser(user); // Привязываем пользователя к сущности Worker
        worker.setCreationDate(new Date()); // Устанавливаем текущую дату как дату создания

        Organization existingOrganization = organizationDAO.findById(workerDTO.getOrganizationId())
                .orElseThrow(() -> new DbException("Organization not found with id: " + workerDTO.getOrganizationId()));
        worker.setOrganization(existingOrganization);

        Coordinates existingCoordinates = coordinatesDAO.findById(workerDTO.getCoordinatesId())
                .orElseThrow(() -> new DbException("Location not found with id: " + workerDTO.getCoordinatesId()));
        worker.setCoordinates(existingCoordinates);

        Person existingPerson = personDAO.findById(workerDTO.getPersonId())
                .orElseThrow(() -> new DbException("Person not found with id: " + workerDTO.getPersonId()));
        worker.setPerson(existingPerson);

        Worker savedWorker = workerDAO.save(worker);
        return workerMapper.toDTO(savedWorker);
    }

    public WorkerDTO getWorkerById(Integer id) throws DbException, NotEnoughAccessLevelToData {
        Worker worker = workerDAO.findById(id).orElseThrow(() ->
                new DbException("Worker not found with id = %d".formatted(id)));

        return workerMapper.toDTO(worker);
    }

    public void updateWorker(WorkerDTO workerDTO, User user) throws NotEnoughAccessLevelToData, DbException {
        Worker worker = workerDAO.findById(workerDTO.getId()).orElseThrow(() ->
                new DbException("Worker not found with id = %d".formatted(workerDTO.getId())));

        // Проверка прав доступа
        permissionChecker.checkRUDPermission(worker);
        // Обновление полей
        worker.setName(workerDTO.getName());

        Organization existingOrganization = organizationDAO.findById(workerDTO.getOrganizationId())
                .orElseThrow(() -> new DbException("Organization not found with id: " + workerDTO.getOrganizationId()));
        worker.setOrganization(existingOrganization);

        Coordinates existingCoordinates = coordinatesDAO.findById(workerDTO.getCoordinatesId())
                .orElseThrow(() -> new DbException("Location not found with id: " + workerDTO.getCoordinatesId()));

        worker.setCoordinates(existingCoordinates);
        worker.setSalary(workerDTO.getSalary());
        worker.setRating(workerDTO.getRating());
        worker.setEndDate(workerDTO.getEndDate());
        worker.setPosition(workerDTO.getPosition());
        worker.setStatus(workerDTO.getStatus());

        Person existingPerson = personDAO.findById(workerDTO.getPersonId())
                .orElseThrow(() -> new DbException("Person not found with id: " + workerDTO.getPersonId()));
        worker.setPerson(existingPerson);

        workerDAO.save(worker);
    }

    public void deleteWorker(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Worker worker = workerDAO.findById(id).orElseThrow(() ->
                new DbException("Worker not found with id = %d".formatted(id)));

        // Проверка прав доступа
        permissionChecker.checkRUDPermission(worker);

        workerDAO.delete(worker);
    }

    public Page<WorkerDTO> getNWorkersFromPage(Pageable pageable) {
        return workerDAO.findAll(pageable).map(workerMapper::toDTO);
    }
}
