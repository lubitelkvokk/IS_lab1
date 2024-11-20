package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.AdminRequestsDTO;
import itmo.is.lab1.model.auth.AdminRequests;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdminRequestsMapper implements GenericMapper<AdminRequests, AdminRequestsDTO> {
    @Override
    public AdminRequestsDTO toDTO(AdminRequests entity) {
        return AdminRequestsDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .accepted(entity.getAccepted())
                .senderId(entity.getSender().getId())
                .build();
    }

    @Override
    public AdminRequests toEntity(AdminRequestsDTO dto) {
        AdminRequests entity = new AdminRequests();
        entity.setId(dto.getId());
        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        } else {
            entity.setDate(new Date());
        }
        entity.setAccepted(dto.getAccepted());
        return null;
    }
}
