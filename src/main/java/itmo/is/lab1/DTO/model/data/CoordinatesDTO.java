package itmo.is.lab1.DTO.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Объект передачи данных, представляющий координаты")
public class CoordinatesDTO {

    @NotNull(message = "X координата не может быть null")
    @Schema(description = "X координата", requiredMode = Schema.RequiredMode.REQUIRED, example = "46.84")
    private Double x; // Поле не может быть null

    @Max(value = 574, message = "Y координата должна быть меньше 575")
    @NotNull(message = "Y координата не может быть null")
    @Schema(description = "Y координата", requiredMode = Schema.RequiredMode.REQUIRED, example = "35")
    private Integer y; // Максимальное значение поля: 574, Поле не может быть null
}
