package itmo.is.lab1.DTO.model.data;

import itmo.is.lab1.model.data.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Объект передачи данных, представляющий информацию о рабочем")
public class WorkerDTO {

    @Schema(description = "Идентификатор рабочего. Значение должно быть больше 0, уникальным и генерироваться автоматически", example = "1")
    private int id; // Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Schema(description = "id владельца объекта")
    private Integer userId;
    @NotBlank(message = "Имя не может быть null или пустым")
    @Schema(description = "Имя рабочего. Поле не может быть null или пустым", required = true, example = "Иван Иванов")
    private String name; // Поле не может быть null, Строка не может быть пустой

    @NotNull(message = "Координаты не могут быть null")
    @Schema(description = "Координаты рабочего. Поле не может быть null", required = true)
    private Coordinates coordinates; // Поле не может быть null

    @NotNull(message = "Дата создания не может быть null")
    @Schema(description = "Дата создания рабочего. Поле не может быть null, значение должно генерироваться автоматически", required = true, example = "2023-11-04T15:30:00Z")
    private java.util.Date creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @NotNull(message = "Организация не может быть null")
    @Schema(description = "Организация рабочего. Поле не может быть null", required = true)
    private Organization organization; // Поле не может быть null

    @Min(value = 1, message = "Зарплата должна быть больше 0")
    @Schema(description = "Зарплата рабочего. Значение должно быть больше 0", example = "50000")
    private long salary; // Значение поля должно быть больше 0

    @Min(value = 1, message = "Рейтинг должен быть больше 0")
    @Schema(description = "Рейтинг рабочего. Поле может быть null, значение должно быть больше 0", example = "10")
    private Integer rating; // Поле может быть null, Значение поля должно быть больше 0

    @Schema(description = "Дата завершения работы. Поле может быть null", example = "2024-05-20T10:15:30")
    private java.time.LocalDateTime endDate; // Поле может быть null

    @NotNull(message = "Должность не может быть null")
    @Schema(description = "Должность рабочего. Поле не может быть null", required = true, example = "ENGINEER")
    private Position position; // Поле не может быть null

    @NotNull(message = "Статус не может быть null")
    @Schema(description = "Статус рабочего. Поле не может быть null", required = true, example = "HIRED")
    private Status status; // Поле не может быть null

    @NotNull(message = "Персональные данные не могут быть null")
    @Schema(description = "Персональные данные рабочего. Поле не может быть null", required = true)
    private Person person; // Поле не может быть null
}
