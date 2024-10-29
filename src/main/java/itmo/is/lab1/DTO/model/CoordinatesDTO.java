package itmo.is.lab1.DTO.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesDTO {
    private Double x; //Поле не может быть null
    private Integer y; //Максимальное значение поля: 574, Поле не может быть null
}
