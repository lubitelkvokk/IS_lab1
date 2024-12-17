package itmo.is.lab1.DTO.model.additional;

import lombok.Data;

@Data
public class HistoryOperationDTO {
    private Integer operationId;
    private boolean status;
    private Integer userId;
    private Integer objCount;
}
