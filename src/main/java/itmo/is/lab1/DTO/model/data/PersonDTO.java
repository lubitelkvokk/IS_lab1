package itmo.is.lab1.DTO.model.data;

import itmo.is.lab1.model.data.Color;
import itmo.is.lab1.model.data.Country;
import itmo.is.lab1.model.data.Location;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Объект передачи данных, представляющий информацию о человеке")
public class PersonDTO {

    @Schema(description = "id выдается после добавления в бд")
    private Integer id;

    @Schema(description = "id владельца объекта")
    private Integer userId;
    @NotNull(message = "Цвет глаз не может быть null")
    @Schema(description = "Цвет глаз человека", requiredMode = Schema.RequiredMode.REQUIRED, example = "BROWN")
    private Color eyeColor; // Поле не может быть null

    @NotNull(message = "Цвет волос не может быть null")
    @Schema(description = "Цвет волос человека", requiredMode = Schema.RequiredMode.REQUIRED, example = "BLACK")
    private Color hairColor; // Поле не может быть null

    @Schema(description = "Местоположение человека, может быть null", requiredMode = Schema.RequiredMode.REQUIRED)
    private Location location; // Поле может быть null

    @NotNull(message = "Дата рождения не может быть null")
    @Schema(description = "Дата рождения человека в формате ISO 8601", requiredMode = Schema.RequiredMode.REQUIRED, example = "1990-05-20T15:30:00Z")
    private java.time.ZonedDateTime birthday; // Поле не может быть null

    @NotNull(message = "Национальность не может быть null")
    @Schema(description = "Национальность человека", requiredMode = Schema.RequiredMode.REQUIRED, example = "RUSSIA")
    private Country nationality; // Поле не может быть null

}
