package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.WorkerDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Worker;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper implements GenericMapper<Worker, WorkerDTO> {

    @Override
    public WorkerDTO toDTO(Worker worker) {
        if (worker == null) {
            return null;
        }
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setId(worker.getId());
        workerDTO.setUserId(worker.getUser().getId());
        workerDTO.setName(worker.getName());
        workerDTO.setCoordinates(worker.getCoordinates());
        workerDTO.setCreationDate(worker.getCreationDate());
        workerDTO.setOrganization(worker.getOrganization());
        workerDTO.setSalary(worker.getSalary());
        workerDTO.setRating(worker.getRating());
        workerDTO.setEndDate(worker.getEndDate());
        workerDTO.setPosition(worker.getPosition());
        workerDTO.setStatus(worker.getStatus());
        workerDTO.setPerson(worker.getPerson());
        return workerDTO;
    }

    @Override
    public Worker toEntity(WorkerDTO workerDTO) {
        if (workerDTO == null) {
            return null;
        }
        Worker worker = new Worker();
        worker.setId(workerDTO.getId());

        // Устанавливаем пользователя, если userId задан
        if (workerDTO.getUserId() != null) {
            User user = new User();
            user.setId(workerDTO.getUserId());
            worker.setUser(user);
        }

        worker.setName(workerDTO.getName());
        worker.setCoordinates(workerDTO.getCoordinates());
        worker.setCreationDate(workerDTO.getCreationDate());
        worker.setOrganization(workerDTO.getOrganization());
        worker.setSalary(workerDTO.getSalary());
        worker.setRating(workerDTO.getRating());
        worker.setEndDate(workerDTO.getEndDate());
        worker.setPosition(workerDTO.getPosition());
        worker.setStatus(workerDTO.getStatus());
        worker.setPerson(workerDTO.getPerson());

        return worker;
    }
}
