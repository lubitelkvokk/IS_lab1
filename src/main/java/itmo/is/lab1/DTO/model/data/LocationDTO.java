package itmo.is.lab1.DTO.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Schema(description = "Объект передачи данных, представляющий местоположение")
public class LocationDTO {

    @NotNull(message = "X координата не может быть null")
    @Schema(description = "X координата", requiredMode = Schema.RequiredMode.REQUIRED, example = "45.3")
    private Float x; // Поле не может быть null

    @Schema(description = "Y координата", example = "35")
    private Integer y; // Поле может быть null

    @NotNull(message = "Название не может быть null")
    @Schema(description = "Название объекта", requiredMode = Schema.RequiredMode.REQUIRED, example = "Школа №4 им. С.П. Королева")
    private String name; // Поле не может быть null
}
