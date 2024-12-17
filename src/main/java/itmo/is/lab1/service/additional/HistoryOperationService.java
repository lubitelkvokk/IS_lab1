package itmo.is.lab1.service.additional;

import itmo.is.lab1.DTO.model.additional.HistoryOperationDTO;
import itmo.is.lab1.dao.HistoryOperationDAO;
import itmo.is.lab1.mapper.HistoryOperationMapper;
import itmo.is.lab1.model.auth.Role;
import itmo.is.lab1.model.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoryOperationService {

    private final HistoryOperationDAO historyOperationDAO;
    private final HistoryOperationMapper historyOperationMapper;

    public HistoryOperationService(HistoryOperationDAO historyOperationDAO, HistoryOperationMapper historyOperationMapper) {
        this.historyOperationDAO = historyOperationDAO;
        this.historyOperationMapper = historyOperationMapper;
    }

    public Page<HistoryOperationDTO> getHistoryOperations(Pageable pageable, User user) {
        if (user.getRole().equals(Role.ROLE_ADMIN))
            return historyOperationDAO.findAll(pageable).map(historyOperationMapper::toDTO);
        return historyOperationDAO.findAllByUser(user, pageable).map(historyOperationMapper::toDTO);
    }

    public void createHistoryOperation(HistoryOperationDTO historyOperationDTO, User user) {
        historyOperationDTO.setUserId(user.getId());
        historyOperationDAO.save(historyOperationMapper.toEntity(historyOperationDTO));
    }
}
