package itmo.is.lab1.DTO.model.data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AdminRequestsDTO {
    private Integer id;

    private Integer senderId;

    private Date date;

    private Boolean accepted;
}
