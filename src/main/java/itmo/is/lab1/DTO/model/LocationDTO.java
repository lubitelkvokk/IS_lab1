package itmo.is.lab1.DTO.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LocationDTO {

    private Float x; // Поле не может быть null

    private Integer y; // Поле может быть null, если не задано

    private String name; // Поле не может быть null, ограничение по длине
}
