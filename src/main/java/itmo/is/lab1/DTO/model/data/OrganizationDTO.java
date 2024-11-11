package itmo.is.lab1.DTO.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.model.data.OrganizationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Объект передачи данных, представляющий информацию об организации")
public class OrganizationDTO {

    @Schema(description = "id выдается после добавления в бд")
    private Integer id;

    @Schema(description = "id владельца объекта")
    private Integer userId;
    @NotNull(message = "Официальный адрес не может быть null")
    @Schema(description = "Официальный адрес организации", requiredMode = Schema.RequiredMode.REQUIRED)
    private Address officialAddress; // Поле не может быть null

    @Min(value = 1, message = "Годовой оборот должен быть больше 0")
    @Schema(description = "Сумма денег, которую бизнес получает от продажи своих продуктов или услуг в течение года", requiredMode = Schema.RequiredMode.REQUIRED, example = "5000000")
    private Integer annualTurnover; // Поле может быть null, Значение поля должно быть больше 0

    @Min(value = 1, message = "Количество сотрудников должно быть больше 0")
    @Schema(description = "Число работников организации", example = "1000")
    private long employeesCount; // Значение поля должно быть больше 0

    @Size(max = 1272, message = "Полное название не должно превышать 1272 символа")
    @NotBlank(message = "Полное название не может быть пустым")
    @Schema(description = "Название организации", requiredMode = Schema.RequiredMode.REQUIRED, example = "LETUAL")
    private String fullName; // Длина строки не должна быть больше 1272, Строка не может быть пустой, Поле может быть null

    @Min(value = 0, message = "Рейтинг должен быть больше 0")
    @Max(value = 100, message = "Рейтинг должен быть меньше или равен 100")
    @Schema(description = "Рейтинг организации по 100-балльной шкале", example = "84.5")
    private double rating; // Значение поля должно быть больше 0

    @NotNull(message = "Тип организации не может быть null")
    @Schema(description = "Тип организации", requiredMode = Schema.RequiredMode.REQUIRED, example = "GOVERNMENT")
    private OrganizationType type; // Поле может быть null
}
