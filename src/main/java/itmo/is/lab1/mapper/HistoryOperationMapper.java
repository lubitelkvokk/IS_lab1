package itmo.is.lab1.mapper;

import itmo.is.lab1.DTO.model.additional.HistoryOperationDTO;
import itmo.is.lab1.model.additional.HistoryOperation;
import itmo.is.lab1.model.auth.User;
import org.springframework.stereotype.Component;

@Component
public class HistoryOperationMapper implements GenericMapper<HistoryOperation, HistoryOperationDTO> {


    @Override
    public HistoryOperationDTO toDTO(HistoryOperation entity) {
        HistoryOperationDTO dto = new HistoryOperationDTO();
        dto.setOperationId(entity.getId());
        dto.setStatus(entity.isStatus());
        dto.setObjCount(entity.getObjCount());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Override
    public HistoryOperation toEntity(HistoryOperationDTO dto) {
        HistoryOperation entity = new HistoryOperation();
        User user = new User();
        user.setId(dto.getUserId());
        entity.setUser(user);

        entity.setObjCount(dto.getObjCount());
        entity.setStatus(dto.isStatus());
        return entity;
    }
}
